import unittest
from datetime import datetime
from Account import Account  # Assuming the Account class is in a file named account.py

class TestAccount(unittest.TestCase):

    def setUp(self):
        """Create a new Account instance before each test"""
        self.account = Account()

    def test_log_expense(self):
        """Test logging an expense"""
        result = self.account.logExpense("2025-03-23", 100.0, "Food", "Lunch")
        self.assertTrue(result)
        self.assertEqual(len(self.account.transactions), 1)
        self.assertEqual(self.account.transactions[0]["type"], "expense")
        self.assertEqual(self.account.transactions[0]["amount"], 100.0)
        self.assertEqual(self.account.transactions[0]["category"], "Food")
        self.assertEqual(self.account.transactions[0]["description"], "Lunch")

    def test_log_income(self):
        """Test logging an income"""
        result = self.account.logIncome("2025-03-23", 500.0, "Salary", "March Salary")
        self.assertTrue(result)
        self.assertEqual(len(self.account.transactions), 1)
        self.assertEqual(self.account.transactions[0]["type"], "income")
        self.assertEqual(self.account.transactions[0]["amount"], 500.0)
        self.assertEqual(self.account.transactions[0]["category"], "Salary")
        self.assertEqual(self.account.transactions[0]["description"], "March Salary")

    def test_delete_expense(self):
        """Test deleting an expense"""
        self.account.logExpense("2025-03-23", 100.0, "Food", "Lunch")
        self.account.deleteExpense("2025-03-23", 100.0, "Lunch")
        self.assertEqual(len(self.account.transactions), 0)

    def test_delete_income(self):
        """Test deleting an income"""
        self.account.logIncome("2025-03-23", 500.0, "Salary", "March Salary")
        self.account.deleteIncome("2025-03-23", 500.0, "March Salary")
        self.assertEqual(len(self.account.transactions), 0)

    def test_get_all_expenses(self):
        """Test retrieving all expenses"""
        self.account.logExpense("2025-03-23", 100.0, "Food", "Lunch")
        self.account.logExpense("2025-03-24", 50.0, "Transport", "Bus fare")
        expenses = self.account.getAllExpenses()


    def test_get_all_income(self):
        """Test retrieving all income"""
        self.account.logIncome("2025-03-23", 500.0, "Salary", "March Salary")
        income = self.account.getAllIncome()


    def test_get_all_money_movements(self):
        """Test retrieving all money movements (expenses and income)"""
        self.account.logExpense("2025-03-23", 100.0, "Food", "Lunch")
        self.account.logIncome("2025-03-23", 500.0, "Salary", "March Salary")
        movements = self.account.getAllMoneyMovements()


    def test_filter_by_category(self):
        """Test filtering transactions by category"""
        self.account.logExpense("2025-03-23", 100.0, "Food", "Lunch")
        self.account.logIncome("2025-03-23", 500.0, "Salary", "March Salary")
        filtered = self.account.filterByCategory("Food")
        self.assertEqual(len(filtered), 1)
        self.assertEqual(filtered[0]["category"], "Food")

    def test_filter_by_date_range(self):
        """Test filtering transactions by date range"""
        self.account.logExpense("2025-03-23", 100.0, "Food", "Lunch")
        self.account.logIncome("2025-03-24", 500.0, "Salary", "March Salary")
        filtered = self.account.filterByDateRange("2025-03-23", "2025-03-23")
        self.assertEqual(len(filtered), 1)

    def test_filter_by_amount(self):
        """Test filtering transactions by amount range"""
        self.account.logExpense("2025-03-23", 100.0, "Food", "Lunch")
        self.account.logIncome("2025-03-23", 500.0, "Salary", "March Salary")
        filtered = self.account.filterByAmount(50.0, 200.0)
        self.assertEqual(len(filtered), 1)
        self.assertEqual(filtered[0]["amount"], 100.0)

    def test_invalid_date_format_in_log_expense(self):
        """Test logging expense with an invalid date format"""
        result = self.account.logExpense("2025-03-32", 100.0, "Food", "Lunch")
        self.assertFalse(result)

    def test_invalid_date_format_in_log_income(self):
        """Test logging income with an invalid date format"""
        result = self.account.logIncome("2025-03-32", 500.0, "Salary", "March Salary")
        self.assertFalse(result)

    def test_delete_non_existent_expense(self):
        """Test deleting a non-existent expense"""
        self.account.logExpense("2025-03-23", 100.0, "Food", "Lunch")
        self.account.deleteExpense("2025-03-24", 200.0, "Dinner")
        self.assertEqual(len(self.account.transactions), 1)

    def test_delete_non_existent_income(self):
        """Test deleting a non-existent income"""
        self.account.logIncome("2025-03-23", 500.0, "Salary", "March Salary")
        self.account.deleteIncome("2025-03-24", 600.0, "Bonus")
        self.assertEqual(len(self.account.transactions), 1)

if __name__ == "__main__":
    unittest.main()
