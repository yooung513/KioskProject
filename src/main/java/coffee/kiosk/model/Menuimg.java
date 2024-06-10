package coffee.kiosk.model;

public class Menuimg {
    private String food_img;
    private String food_name;
    private int food_price;

    public Menuimg(){}

    public Menuimg(String food_name , int food_price , String food_img){
        this.food_name = food_name;
        this.food_price = food_price;
        this.food_img = food_img;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_img() {
        return food_img;
    }

    public void setFood_img(String food_img) {
        this.food_img = food_img;
    }

    public int getFood_price() {
        return food_price;
    }

    public void setFood_price(int food_price) {
        this.food_price = food_price;
    }

    @Override
    public String toString() {
        return "Menu Name: " + this.food_name + ", Price: " + this.food_price + ", Image: " + this.food_img;
    }

}
