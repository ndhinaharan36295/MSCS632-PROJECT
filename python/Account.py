import datetime
from typing import List, Dict, Union, Optional
from collections import defaultdict
import threading

Transaction = Dict[str, Union[str, datetime.date, float]]

class Account:
    def __init__(self) -> None:
        self.transactions: List[Transaction] = []
        self.transactions_by_type = defaultdict(list)  # Caches transactions by type
        self.lock = threading.Lock()  # Ensures thread-safe operations

    def _parse_date(self, date_str: str) -> Optional[datetime.date]:
        try:
            return datetime.datetime.strptime(date_str, "%Y-%m-%d").date()
        except ValueError:
            print("Error: Incorrect date format. Use YYYY-MM-DD.")
            return None

    def _log_transaction(self, date: str, amount: float, category: str, description: str, t_type: str) -> bool:
        parsed_date = self._parse_date(date)
        if parsed_date is None:
            return False

        transaction = {
            "date": parsed_date,
            "type": t_type,
            "amount": amount,
            "category": category,
            "description": description
        }

        with self.lock:
            self.transactions.append(transaction)
            self.transactions_by_type[t_type].append(transaction)

        return True

    def logExpense(self, date: str, amount: float, category: str, description: str = "") -> bool:
        return self._log_transaction(date, amount, category, description, "expense")

    def logIncome(self, date: str, amount: float, category: str, description: str = "") -> bool:
        return self._log_transaction(date, amount, category, description, "income")

    def _delete_transaction(self, date: str, amount: float, description: str, t_type: str) -> None:
        parsed_date = self._parse_date(date)
        if parsed_date is None:
            return

        with self.lock:
            to_remove = next((t for t in self.transactions_by_type[t_type]
                              if t["date"] == parsed_date and
                              t["amount"] == amount and
                              t["description"].lower() == description.lower()), None)
            if to_remove:
                self.transactions.remove(to_remove)
                self.transactions_by_type[t_type].remove(to_remove)
                print(f"{t_type.capitalize()} deleted: {to_remove}")
            else:
                print(f"No matching {t_type} found.")

    def deleteExpense(self, date: str, amount: float, description: str) -> None:
        self._delete_transaction(date, amount, description, "expense")

    def deleteIncome(self, date: str, amount: float, description: str) -> None:
        self._delete_transaction(date, amount, description, "income")

    def _display_transactions(self, transactions: List[Transaction], header: str) -> None:
        if not transactions:
            print(f"\nNo {header.lower()} recorded.")
            return
        print(f"\n{header}:")
        for t in transactions:
            print(f"Date: {t['date']}, Amount: ${t['amount']:.2f}, Category: {t['category']}, Description: {t['description']}")

    def getAllExpenses(self) -> None:
        self._display_transactions(self.transactions_by_type["expense"], "All Expenses")

    def getAllIncome(self) -> None:
        self._display_transactions(self.transactions_by_type["income"], "All Income")

    def getAllMoneyMovements(self) -> None:
        self._display_transactions(self.transactions, "Money Movements (Income and Expenses)")

    def filterByCategory(self, category: str) -> List[Transaction]:
        result = [t for t in self.transactions if t["category"].lower() == category.lower()]
        self._display_transactions(result, f"Transactions in Category: {category}")
        return result

    def filterByDateRange(self, start_date: str, end_date: str) -> List[Transaction]:
        start = self._parse_date(start_date)
        end = self._parse_date(end_date)
        if not start or not end:
            return []

        result = [t for t in self.transactions if start <= t["date"] <= end]
        self._display_transactions(result, f"Transactions from {start} to {end}")
        return result

    def filterByAmount(self, min_amount: float, max_amount: float) -> List[Transaction]:
        result = [t for t in self.transactions if min_amount <= t["amount"] <= max_amount]
        self._display_transactions(result, f"Transactions in Amount Range: ${min_amount:.2f} - ${max_amount:.2f}")
        return result