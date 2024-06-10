package coffee.kiosk.model;

public class TotalOrderAmount {
    private static TotalOrderAmount instance ;
    private int totalorderamount;
    private int selectedorderamount;
    private  int selectedcount;

    private TotalOrderAmount(){}

    public static TotalOrderAmount getInstance(){
        if (instance == null){
            instance = new TotalOrderAmount();
        }
        return  instance;
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

    public void addSelectedOrderAmount(int selectedOrderAmount) {
        this.selectedorderamount += selectedOrderAmount;
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
