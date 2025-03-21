package java;
import java.util.Date;

public class Expense extends MoneyMovement{

    private String storeName;
    
    public Expense(String description, double amount, String category, Date date){
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public Expense(){

    }

    public String getStoreName(){
        return this.storeName;
    }

    public void setStoreName(String storeName){
        this.storeName = storeName;
    }

    @Override
    public String showMovement() {
        return null;
    }

}
