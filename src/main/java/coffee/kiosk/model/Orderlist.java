package coffee.kiosk.model;

import java.util.ArrayList;
import java.util.List;

public class Orderlist {
    private static Orderlist instance;
    private List<OrderItem> orderItems;

    private Orderlist() {
        this.orderItems = new ArrayList<>();
    }

    public static Orderlist getInstance() {
        if (instance == null) {
            instance = new Orderlist();
        }
        return instance;
    }

    public void clearOrderItems(){
        orderItems.clear();
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public static class OrderItem {
        private String foodname;
        private int count;
        private int foodprice;
        private String optionname;
        private int optionprice;

        public OrderItem(String foodname, int count, int foodprice , String optionname , int optionprice) {
            this.foodname = foodname;
            this.count = count;
            this.foodprice = foodprice;
            this.optionname = optionname;
            this.optionprice = optionprice;
        }

        public String getFoodname() {
            return foodname;
        }

        public int getCount() {
            return count;
        }

        public int getFoodprice() {
            return foodprice;
        }

        public String getOptionname() {
            return optionname;
        }

        public int getOptionprice() {
            return optionprice;
        }
    }
}
