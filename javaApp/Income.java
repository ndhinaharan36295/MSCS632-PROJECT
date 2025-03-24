package javaApp;

import java.util.Date;

public class Income extends MoneyMovement{

    private String source;
    
    public Income(String description, double amount, String category, Date date, String source){
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.source = source;
    }

    public Income(){

    }

    public String getSource(){
        return this.source;
    }

    public void setSource(String source){
        this.source = source;
    }

    @Override
    public String showMovement() {
        return "Amount: " + amount + " | Description: " + description + " | Category: " + category + " | Date: " + date + " | Source: " + source ;
    }

}
