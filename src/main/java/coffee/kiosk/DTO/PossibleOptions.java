package coffee.kiosk.DTO;

public class PossibleOptions {
    int possibleIdx;
    int optionId;
    int foodId;
    String foodName;
    String optionName;


    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getPossibleIdx() {
        return possibleIdx;
    }

    public void setPossibleIdx(int possibleIdx) {
        this.possibleIdx = possibleIdx;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
