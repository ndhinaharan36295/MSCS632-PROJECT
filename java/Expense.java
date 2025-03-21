package java;
import java.util.Date;

public class Expense extends MoneyMovement{
    
    public Expense(String description, double amount, String category, Date date){
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public Expense(){

    }

    @Override
    public String showMovement() {
        return null;
    }

}
