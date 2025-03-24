package javaApp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    private String name;
    private double totalBalance;
    private List<Expense> expenses;
    private List<Income> credits;

    public Account(){
        initializeTotalBalance();
        this.expenses = new ArrayList<>();
        this.credits = new ArrayList<>();
    }

    public void setName(String name){
        // set the user name
        this.name = name;
    }

    public String getName(){
        // get the user name
        return this.name;
    }

    public double getTotalBalance(){
        // return the current total balance (should be the total of credits - expenses)
        return this.totalBalance;
    }
    
    protected void initializeTotalBalance(){
        // method to set the total balance, to be used initially only
        this.totalBalance = 0;
    }

    public void addToTotalBalance(double incomeAmonut){
        this.totalBalance += incomeAmonut;
    }

    public void substractFromTotalBalance(double expenseAmount){
        this.totalBalance -= expenseAmount;
    }

    public void logExpense(Expense expense){
        if(this.expenses == null){
            this.expenses = new ArrayList<>();
        }
        this.expenses.add(expense);
        substractFromTotalBalance(expense.getAmount());
    }

    public void logIncome(Income income){
        if(this.credits == null){
            this.credits = new ArrayList<>();
        }
        this.credits.add(income);
        addToTotalBalance(income.getAmount());
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
        return expenses;
    }


    public List<Income> getAllIncome(){
        // retrun the credits array list
        return credits;
    }

    // todo
    public List<MoneyMovement> getAllMoneyMovements(){
        // method to return all transactions
        List<MoneyMovement> allTransactions = new ArrayList<>();
        allTransactions.addAll(this.expenses);
        allTransactions.addAll(this.credits);
        return allTransactions;
    }



}
