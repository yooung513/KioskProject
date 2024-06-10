package coffee.kiosk.DTO;

public class FoodOptions {
    int optionId;
    String optionName;
    int optionPrice;


    public int getOptionId() {
        return this.optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return this.optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getOptionPrice() {
        return this.optionPrice;
    }

    public void setOptionPrice(int optionPrice) {
        this.optionPrice = optionPrice;
    }
}
