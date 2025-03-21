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

    // todo
    public List<MoneyMovement> filterByCategory(String category){
        return null;
    }

    // todo
    public List<MoneyMovement> filterByDateRange(Date startDate, Date endDate){
        return null;
    }

    // todo
    public List<MoneyMovement> filterByAmount(double min, double max){
        return null;
    }

    // todo
    public List<Expense> getAllExpenses(){
        return null;
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