package javaApp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    private String name;
    private double totalBalance;
    private List<Expense> expenses;
    private List<Income> credits;

    // storage for optimized search
    private AVLTree<Expense> amonutExpenseSearchTree;
    private AVLTree<Income> amountIncomeSearchTree;
    private AVLTree<Expense> dateExpenseSearchTree;
    private AVLTree<Income> dateIncomeSearchTree;
    private CategoryDoublyLinkedList expenseLinkedList;
    private CategoryDoublyLinkedList incomeLinkedList;

    public Account(){
        initializeTotalBalance();
        this.expenses = new ArrayList<>();
        this.credits = new ArrayList<>();

        this.expenseLinkedList = new CategoryDoublyLinkedList();
        this.incomeLinkedList = new CategoryDoublyLinkedList();

        this.amonutExpenseSearchTree = new AVLTree<>((expense1, expense2) -> Double.compare(expense1.getAmount(), expense2.getAmount()));
        this.amountIncomeSearchTree = new AVLTree<>((income1, income2) -> Double.compare(income1.getAmount(), income2.getAmount()));

        this.dateExpenseSearchTree = new AVLTree<>((expense1, expense2) -> expense1.getDate().compareTo(expense2.getDate()));
        this.dateIncomeSearchTree = new AVLTree<>((income1, income2) -> income1.getDate().compareTo(income2.getDate()));

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
        this.expenseLinkedList.add(expense);
        this.amonutExpenseSearchTree.insert(expense);
        this.dateExpenseSearchTree.insert(expense);
        substractFromTotalBalance(expense.getAmount());
    }

    public void logIncome(Income income){
        if(this.credits == null){
            this.credits = new ArrayList<>();
        }
        this.credits.add(income);
        this.incomeLinkedList.add(income);
        this.amountIncomeSearchTree.insert(income);
        this.dateIncomeSearchTree.insert(income);
        addToTotalBalance(income.getAmount());
    }

    public List<MoneyMovement> filterByCategory(String category, MovementTypeEnum type){
        if(MovementTypeEnum.EXPENSE.equals(type)){
            return expenseLinkedList.searchByCategory(category);
        } else if (MovementTypeEnum.INCOME.equals(type)){
            return incomeLinkedList.searchByCategory(category);
        }
        return null;
    }

    public List<Expense> filterExpenseByDateRange(Date startDate, Date endDate){
        return dateExpenseSearchTree.rangeSearch(dateExpenseSearchTree.initializeMinDateExpense(startDate), dateExpenseSearchTree.initializeMaxDateExpense(endDate));
    }

    public List<Expense> filterExpenseByAmount(double minAmount, double maxAmount){
        return dateExpenseSearchTree.rangeSearch(dateExpenseSearchTree.initializeMinAmountExpense(minAmount), dateExpenseSearchTree.initializeMaxAmountExpense(maxAmount));
    }

    public List<Income> filterIncomeByDateRange(Date startDate, Date endDate){
        return dateIncomeSearchTree.rangeSearch(dateIncomeSearchTree.initializeMinDateIncome(startDate), dateIncomeSearchTree.initializeMaxDateIncome(endDate));
    }

    public List<Income> filterIncomeByAmount(double minAmount, double maxAmount){
        return dateIncomeSearchTree.rangeSearch(dateIncomeSearchTree.initializeMinAmountIncome(minAmount), dateIncomeSearchTree.initializeMaxAmountIncome(maxAmount));
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
