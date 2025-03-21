# Importing Libraries

import datetime

# Initializing class to hold expenses and income
class Account:
    
    def __init__(self, initial_income=0):
        self.expenses = []
        self.income = initial_income

    # Function to log expenses
    def log_expense(self, date, amount, category, description=""):
        try:
            date = datetime.datetime.strptime(date, "%Y-%m-%d").date()
            self.expenses.append(
                {
                    "date": date,
                    "amount": amount,
                    "category": category,
                    "description": description
                })
            self.income -= amount
            return True
        except ValueError:
            print("Incorrect date format, use YYYY-MM-DD")
            return False

    # Function to filter expenses by start date, end date, category, and amount TO DO


    # Function to calculate expenses per category and overall total. TO DO

        
    # Function to display a list of expenses.TO DO

        
    # Function to display a summary of expenses per category, overall total, and remaining income.TO DO

## User Prompts

# Prompt the user for expenses. TO DO


# Prompt user for initial income and function selection TO DO


