package coffee.kiosk.model;

import java.io.PipedReader;

public class TotalOrderAmount {
    private static TotalOrderAmount instance ;
    private int totalorderamount;
    private int selectedorderamount;
    private  int selectedcount;
    private int optionamount;

    public TotalOrderAmount(){}

    public static TotalOrderAmount getInstance(){
        if (instance == null){
            instance = new TotalOrderAmount();
        }
        return  instance;
    }

    public void clearAmount(){
        this.totalorderamount =0;
        this.selectedorderamount =0;
        this.optionamount =0;
    }

    public int getTotalOrderAmount() {
        return totalorderamount;
    }

    public void setTotalOrderAmount(int totalorderamount) {
        this.totalorderamount = totalorderamount;
    }

    public int getSelectedOrderAmount(){
        return selectedorderamount;
    }

    public void setSelectedorderamount(int selectedorderamount){
        this.selectedorderamount =0;
        this.selectedorderamount = selectedorderamount;
    }

    public int getOptionamount(){
        return optionamount;
    }

    public void setOptionamount(int optionamount){
        this.optionamount =0;
        this.optionamount = optionamount;
    }

    public void addSelectedOrderAmount(int selectedOrderAmount) {
        this.selectedorderamount += selectedOrderAmount;
    }

    public void addOptionOrderAmount(int optionamount){
        this.optionamount =0;
        this.optionamount += optionamount;
    }

    public int getCount(){
        return selectedorderamount;
    }

    public void setCount(int selectedcount){
        selectedcount = 1;
        this.selectedcount = selectedcount;
    }
    @Override
    public String toString() {
        return "Menu totalorderamount: " + this.totalorderamount;
    }

}
