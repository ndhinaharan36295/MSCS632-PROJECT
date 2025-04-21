package javaApp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ExpenseTracker {

    private static Set<String> validMenuSet = Set.of("A", "B", "C", "D", "E", "F", "G", "H", "I");
    public static void main(String[] args) {
        System.out.println("================================MSCS 632 EXPENSE TRACKER 2.0=======================================");
        System.out.println("============================Hello, Welcome to your expense Tracker!================================");
        Scanner scanner = new Scanner(System.in);

        Account userAccount = new Account();

        while(true){
            String option = "";
            printMenu();
            System.out.println("What can I help you with today?: ");
            option = scanner.nextLine();

            if(!validMenuSet.contains(option)){
                System.out.println("please enter a valid menu option");
                continue;
            }

            //scanner.close();

            switch (option) {
                case "A":
                    Expense expense = logExpense(scanner);
                    userAccount.logExpense(expense);
                    break;
                case "B":
                    Income income = logIncome(scanner);
                    userAccount.logIncome(income);
                    break;
                case "C":
                    searchByCategory(userAccount, scanner, MovementTypeEnum.EXPENSE);
                    break;
                case "D":
                    searchByCategory(userAccount, scanner, MovementTypeEnum.INCOME);
                    break;
                case "E":
                    searchByPriceRange(userAccount, scanner, MovementTypeEnum.EXPENSE);
                    break;
                case "F":
                    searchByPriceRange(userAccount, scanner, MovementTypeEnum.INCOME);
                    break;
                case "G":
                    searchByDateRange(userAccount, scanner, MovementTypeEnum.EXPENSE);
                    break;
                case "H":
                    searchByDateRange(userAccount, scanner, MovementTypeEnum.INCOME);
                    break;
                case "I":
                    showAllExpenses(userAccount);
                    break;
                case "J":
                    showAllIncome(userAccount);
                    break;
                case "K":
                    showTotalBalance(userAccount);
                    break;
                default:
                    break;
            }

            if("L".equals(option)){
                break;
            }

        }

    }

    public static void showTotalBalance(Account userAccount){
        System.out.println("Your Total Balance as of now is: " + userAccount.getTotalBalance());
    }

    public static void showAllIncome(Account userAccount){
        System.out.println("=======================================================");
        System.out.println("Here are all your Income Transactions:");
        List<Income> credits = userAccount.getAllIncome();

        for(Income income : credits){
            System.out.println(income.showMovement());
        }
    }

    public static void showAllExpenses(Account userAccount){
        System.out.println("=======================================================");
        System.out.println("Here are all your Expenses:");
        List<Expense> expenses = userAccount.getAllExpenses();

        for(Expense expense : expenses){
            System.out.println(expense.showMovement());
        }
    }

    public static void searchByDateRange(Account userAccount, Scanner scanner, MovementTypeEnum type){

        System.out.println("Start Date: ");
        String start = scanner.nextLine();
        System.out.println("End Date: ");
        String end = scanner.nextLine();

        if(MovementTypeEnum.EXPENSE.equals(type)){
            List<Expense> movements = userAccount.filterExpenseByDateRange(convertToDateFormat(start), convertToDateFormat(end));
            printExpenses(movements);
        } else if (MovementTypeEnum.INCOME.equals(type)){
            List<Income> movements = userAccount.filterIncomeByDateRange(convertToDateFormat(start), convertToDateFormat(end));
            printIncome(movements);
        }

    }

    public static void searchByPriceRange(Account userAccount, Scanner scanner, MovementTypeEnum type){

        System.out.println("Min Price: ");
        double min = scanner.nextDouble();
        System.out.println("Max Price: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        if(MovementTypeEnum.EXPENSE.equals(type)){
            List<Expense> movements = userAccount.filterExpenseByAmount(min, max);
            printExpenses(movements);
        } else if (MovementTypeEnum.INCOME.equals(type)){
            List<Income> movements = userAccount.filterIncomeByAmount(min, max);
            printIncome(movements);
        }

    }

    public static void searchByCategory(Account userAccount, Scanner scanner, MovementTypeEnum type){

        System.out.println("Catgeory Name: ");
        String category = scanner.nextLine();
        List<MoneyMovement> transacitons = userAccount.filterByCategory(category, type);

        for(MoneyMovement transaciton : transacitons){
            System.out.println(transaciton.showMovement());
        }

    }

    public static Income logIncome(Scanner scanner){
        Income income = new Income();

        System.out.println("===================================================");
        System.out.println("Income Amount: ");
        double amount = scanner.nextDouble();
        income.setAmount(amount);
        scanner.nextLine();

        System.out.println("Source name: ");
        String source = scanner.nextLine();
        income.setSource(source);

        System.out.println("Income Description: ");
        String description = scanner.nextLine();
        income.setDescription(description);

        System.out.println("Income Category: ");
        String category = scanner.nextLine();
        income.setCategory(category);

        System.out.println("Income Date: ");
        String date = scanner.nextLine();
        income.setDate(convertToDateFormat(date));

        System.out.println("INCOME SAVED SUCCESSFULLY");
        System.out.println("======================================================");

        return income;
    }

    public static Expense logExpense(Scanner scanner){
        Expense expense = new Expense();

        System.out.println("===================================================");

        System.out.println("Expense Amount: ");
        double amount = scanner.nextDouble();
        expense.setAmount(amount);
        scanner.nextLine();

        System.out.println("Store name: ");
        String storeName = scanner.nextLine();
        expense.setStoreName(storeName);

        System.out.println("Expense Description: ");
        String description = scanner.nextLine();
        expense.setDescription(description);

        System.out.println("Expense Category: ");
        String category = scanner.nextLine();
        expense.setCategory(category);

        System.out.println("Expense Date: ");
        String date = scanner.nextLine();
        expense.setDate(convertToDateFormat(date));

        System.out.println("EXPENSE SAVED SUCCESSFULLY");
        System.out.println("======================================================");

        return expense;
    }

    public static void printMenu(){
        System.out.println("Main Menu: \n");
        System.out.println("A | Log Expense");
        System.out.println("B | Log Income");
        System.out.println("C | Search Expense by Category");
        System.out.println("D | Search Income by Category");
        System.out.println("E | Search Expense by Price Range");
        System.out.println("F | Search Income by Price Range");
        System.out.println("G | Search Expense by Date");
        System.out.println("H | Search Income by Date");
        System.out.println("I | Show all Expenses");
        System.out.println("J | Show all Income Transactions");
        System.out.println("K | Show total balance");
        System.out.println("L | EXIT");
    }

    private static Date convertToDateFormat(String dateString){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = formatter.parse(dateString);
            return date;
        } catch (Exception e){
            System.out.println("Incorrect date format");
            return null;
        }
    }

    private static void printExpenses(List<Expense> expenses) {
        for(Expense expense : expenses){
            System.out.println(expense.showMovement());
        }
    }

    private static void printIncome(List<Income> incomeList) {
        for(Income income: incomeList){
            System.out.println(income.showMovement());
        }
    }
}
