package javaApp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ExpenseTracker {

    private static Set<String> validMenuSet = Set.of("A", "B", "C", "D", "E", "F", "G", "H", "I");
    public static void main(String[] args) {
        System.out.println("Hello, Welcome to your expense Tracker!");
        Scanner scanner = new Scanner(System.in);

        Account userAccount = new Account();

        while(true){
            String option = "";
            printMenu();
            System.out.println("Select action: ");
            option = scanner.nextLine();

            if(!validMenuSet.contains(option)){
                System.out.println("please enter a valid menu option");
                continue;
            }

            //Kscanner.close();

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
                    searchByCategory(userAccount, scanner);
                    break;
                case "D":
                    searchByPriceRange(userAccount, scanner);
                    break;
                case "E":
                    searchByDateRange(userAccount, scanner);
                    break;
                case "F":
                    showAllExpenses(userAccount);
                    break;
                case "G":
                    showAllIncome(userAccount);
                    break;
                case "H":
                    showTotalBalance(userAccount);
                    break;
                default:
                    break;
            }

            if("I".equals(option)){
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

    public static void searchByDateRange(Account userAccount, Scanner scanner){

        System.out.println("Start Date: ");
        String start = scanner.nextLine();
        System.out.println("End Date: ");
        String end = scanner.nextLine();

        List<MoneyMovement> transacitons = userAccount.filterByDateRange(convertToDateFormat(start), convertToDateFormat(end));

        for(MoneyMovement transaciton : transacitons){
            System.out.println(transaciton.showMovement());
        }

    }

    public static void searchByPriceRange(Account userAccount, Scanner scanner){

        System.out.println("Min Price: ");
        double min = scanner.nextDouble();
        System.out.println("Max Price: ");
        double max = scanner.nextDouble();

        List<MoneyMovement> transacitons = userAccount.filterByAmount(min, max);

        for(MoneyMovement transaciton : transacitons){
            System.out.println(transaciton.showMovement());
        }

    }

    public static void searchByCategory(Account userAccount, Scanner scanner){

        System.out.println("Catgeory Name: ");
        String category = scanner.nextLine();
        List<MoneyMovement> transacitons = userAccount.filterByCategory(category);

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

        System.out.println("INCOME SAVED SUCCESSFULLY");
        System.out.println("======================================================");

        return expense;
    }

    public static void printMenu(){
        System.out.println("Main Menu: \n");
        System.out.println("A | Log Expense");
        System.out.println("B | Log Income");
        System.out.println("C | Search by Category");
        System.out.println("D | Search by Price Range");
        System.out.println("E | Search by Date");
        System.out.println("F | Show all Expenses");
        System.out.println("G | Show all Income Transactions");
        System.out.println("H | Show total balance");
        System.out.println("I | EXIT");
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
}
