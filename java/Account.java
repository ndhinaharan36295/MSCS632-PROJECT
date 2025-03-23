package java;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    public List<Expense> expenses;
    public List<Income> credits;

    public Account(){
        this.expenses = new ArrayList<>();
        this.credits = new ArrayList<>();
    }

    public void logExpense(Expense expense){
        if(this.expenses == null){
            this.expenses = new ArrayList<>();
        }
        this.expenses.add(expense);
    }

    public void logIncome(Income income){
        if(this.credits == null){
            this.credits = new ArrayList<>();
        }
        this.credits.add(income);
    }

    public List<MoneyMovement> filterByCategory(String category){
        // final List containing all the filtered expenses and incomes (both represented as MoneyMovement objects)
        // that match the given category
        List<MoneyMovement> filteredMoneyMovements = new ArrayList<>();

        // Iterating over all the expenses and add the ones that match the category to the filteredMoneyMovements list
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category)) {
                filteredMoneyMovements.add(expense);
            }
        }

        // Iterating over all the incomes and add the ones that match the category to the filteredMoneyMovements list
        for (Income income : credits) {
            if (income.getCategory().equals(category)) {
                filteredMoneyMovements.add(income);
            }
        }

        return filteredMoneyMovements;
    }

    public List<MoneyMovement> filterByDateRange(Date startDate, Date endDate){
        // final List containing all the filtered expenses and incomes that match the given date range (>= startDate and <= endDate)
        List<MoneyMovement> filteredMoneyMovements = new ArrayList<>();

        // Iterating over all the expenses and add the ones that match the date range to the filteredMoneyMovements list
        for (Expense expense : expenses) {
            if (!expense.getDate().before(startDate) && !expense.getDate().after(endDate)) {
                filteredMoneyMovements.add(expense);
            }
        }

        // Iterating over all the incomes and add the ones that match the date range to the filteredMoneyMovements list
        for (Income income : credits) {
            if (!income.getDate().before(startDate) && !income.getDate().after(endDate)) {
                filteredMoneyMovements.add(income);
            }
        }
        return filteredMoneyMovements;
    }

    public List<MoneyMovement> filterByAmount(double minAmount, double maxAmount){
        // final List containing all the filtered expenses and incomes that match the given amount (>= minAmount and <= maxAmount)
        List<MoneyMovement> filteredMoneyMovements = new ArrayList<>();

        // Iterating over all the expenses and add the ones that match the amount range to the filteredMoneyMovements list
        for (Expense expense : expenses) {
            if (expense.getAmount() >= minAmount && expense.getAmount() <= maxAmount) {
                filteredMoneyMovements.add(expense);
            }
        }

        // Iterating over all the incomes and add the ones that match the amount range to the filteredMoneyMovements list
        for (Income income : credits) {
            if (income.getAmount() >= minAmount && income.getAmount() <= maxAmount) {
                filteredMoneyMovements.add(income);
            }
        }
        return filteredMoneyMovements;
    }

    public List<Expense> getAllExpenses(){
        // returning a new ArrayList containing all the expenses
        return new ArrayList<>(expenses);
    }

    // todo
    public List<Income> getAllIncome(){
        return null;
    }

    // todo
    public List<MoneyMovement> getAllMoneyMovements(){
        return null;
    }



}
