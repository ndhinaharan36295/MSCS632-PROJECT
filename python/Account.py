import datetime


class Account:

    def __init__(self):
        """Initialize account with a list of transactions"""
        self.transactions = []

    #  Log Expense
    def logExpense(self, date, amount, category, description=""):
        """Log an expense"""
        try:
            date = datetime.datetime.strptime(date, "%Y-%m-%d").date()
            self.transactions.append(
                {
                    "date": date,
                    "type": "expense",
                    "amount": amount,
                    "category": category,
                    "description": description
                })
            return True
        except ValueError:
            print("Error: Incorrect date format. Use YYYY-MM-DD.")
            return False

    #  Log Income
    def logIncome(self, date, amount, category, description=""):
        """Log an income"""
        try:
            date = datetime.datetime.strptime(date, "%Y-%m-%d").date()
            self.transactions.append(
                {
                    "date": date,
                    "type": "income",
                    "amount": amount,
                    "category": category,
                    "description": description
                })
            return True
        except ValueError:
            print("Error: Incorrect date format. Use YYYY-MM-DD.")
            return False

    #  Delete Expense by date, amount, and description
    def deleteExpense(self, date, amount, description):
        """Delete an expense by date, amount, and description"""
        try:
            date = datetime.datetime.strptime(date, "%Y-%m-%d").date()

            for transaction in self.transactions:
                if (
                        transaction["type"] == "expense" and
                        transaction["date"] == date and
                        transaction["amount"] == amount and
                        transaction["description"].lower() == description.lower()
                ):
                    self.transactions.remove(transaction)
                    print(f" Expense deleted: {transaction}")
                    return
            print(" No matching expense found.")
        except ValueError:
            print("Invalid date format. Use YYYY-MM-DD.")
        except Exception as e:
            print(f"Error deleting expense: {e}")

    #  Delete Income by date, amount, and description
    def deleteIncome(self, date, amount, description):
        """Delete an income by date, amount, and description"""
        try:
            date = datetime.datetime.strptime(date, "%Y-%m-%d").date()

            for transaction in self.transactions:
                if (
                        transaction["type"] == "income" and
                        transaction["date"] == date and
                        transaction["amount"] == amount and
                        transaction["description"].lower() == description.lower()
                ):
                    self.transactions.remove(transaction)
                    print(f" Income deleted: {transaction}")
                    return
            print(" No matching income found.")
        except ValueError:
            print("Invalid date format. Use YYYY-MM-DD.")
        except Exception as e:
            print(f"Error deleting income: {e}")

    #  Show All Expenses
    def getAllExpenses(self):
        """Display all expenses"""
        expenses = [t for t in self.transactions if t["type"] == "expense"]

        if not expenses:
            print("\nNo expenses recorded.")
            return

        print("\n All Expenses:")
        for expense in expenses:
            print(f"Date: {expense['date']}, "
                  f"Amount: ${expense['amount']:.2f}, "
                  f"Category: {expense['category']}, "
                  f"Description: {expense['description']}")

    #  Show All Income
    def getAllIncome(self):
        """Display all income"""
        income = [t for t in self.transactions if t["type"] == "income"]

        if not income:
            print("\nNo income recorded.")
            return

        print("\n All Income:")
        for inc in income:
            print(f"Date: {inc['date']}, "
                  f"Amount: ${inc['amount']:.2f}, "
                  f"Category: {inc['category']}, "
                  f"Description: {inc['description']}")

    #  Show Money Movements
    def getAllMoneyMovements(self):
        """Display all income and expense movements"""
        if not self.transactions:
            print("\nNo transactions recorded.")
            return

        print("\n Money Movements (Income and Expenses):")
        for transaction in self.transactions:
            print(f"Date: {transaction['date']}, "
                  f"Type: {transaction['type'].capitalize()}, "
                  f"Amount: ${transaction['amount']:.2f}, "
                  f"Category: {transaction['category']}, "
                  f"Description: {transaction['description']}")

    #  Filter by Category
    def filterByCategory(self, category):
        """Filter transactions by category"""
        filtered = [t for t in self.transactions if t["category"].lower() == category.lower()]

        if not filtered:
            print(f"\n No transactions found for category: {category}")
            return []

        print(f"\n Transactions in Category: {category}")
        for t in filtered:
            print(f"Date: {t['date']}, Amount: ${t['amount']:.2f}, Type: {t['type']}, Description: {t['description']}")

        return filtered

    #  Filter by Date Range
    def filterByDateRange(self, start_date, end_date):
        """Filter transactions by date range"""
        try:
            start_date = datetime.datetime.strptime(start_date, "%Y-%m-%d").date()
            end_date = datetime.datetime.strptime(end_date, "%Y-%m-%d").date()

            filtered = [
                t for t in self.transactions
                if start_date <= t["date"] <= end_date
            ]

            if not filtered:
                print("\n No transactions found in the given date range.")
                return []

            print(f"\n Transactions from {start_date} to {end_date}:")
            for t in filtered:
                print(f"Date: {t['date']}, Amount: ${t['amount']:.2f}, Type: {t['type']}, Category: {t['category']}, Description: {t['description']}")

            return filtered

        except ValueError:
            print("Error: Invalid date format. Use YYYY-MM-DD.")
            return []

    #  Filter by Amount Range
    def filterByAmount(self, min_amount, max_amount):
        """Filter transactions by amount range"""
        filtered = [
            t for t in self.transactions
            if min_amount <= t["amount"] <= max_amount
        ]

        if not filtered:
            print(f"\n No transactions found in amount range: ${min_amount:.2f} - ${max_amount:.2f}")
            return []

        print(f"\n Transactions in Amount Range: ${min_amount:.2f} - ${max_amount:.2f}")
        for t in filtered:
            print(f"Date: {t['date']}, Amount: ${t['amount']:.2f}, Type: {t['type']}, Category: {t['category']}, Description: {t['description']}")

        return filtered
