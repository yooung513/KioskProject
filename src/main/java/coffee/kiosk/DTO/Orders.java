package coffee.kiosk.DTO;

import java.util.Date;

public class Orders {

    int orderIdx;
    String foodId;
    int optionId;
    int paymentId;
    String orderId;
    String foodName;
    int foodPrice;
    int foodCount;
    int hot;                 // 1: 선택 / 0: 선택 안함 (Default = 0)
    int ice;                 // 얼음양 1: 기본 / 2: 적게 / 3: 많이
    int shot;                // 1: 기본 / 2: 연하게 / 3: 2샷
    boolean steviaFlag;      // Default = F
    boolean syrupFlag;       // Default = F
    boolean decaffeineFlag;  // Default = F
    boolean completeFlag;
    Date order_date;
    int receipt_way;


    public int getOrderIdx() {
        return orderIdx;
    }

    public void setOrderIdx(int orderIdx) {
        this.orderIdx = orderIdx;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() { return orderId; }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodCount() { return foodCount; }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getIce() {
        return ice;
    }

    public void setIce(int ice) {
        this.ice = ice;
    }

    public int getShot() {
        return shot;
    }

    public void setShot(int shot) {
        this.shot = shot;
    }

    public boolean isSteviaFlag() { return steviaFlag; }

    public void setSteviaFlag(boolean steviaFlag) {
        this.steviaFlag = steviaFlag;
    }

    public boolean isSyrupFlag() { return syrupFlag; }

    public void setSyrupFlag(boolean syrupFlag) { this.syrupFlag = syrupFlag; }

    public boolean isDecaffeineFlag() { return decaffeineFlag; }

    public void setDecaffeineFlag(boolean decaffeineFlag) {
        this.decaffeineFlag = decaffeineFlag;
    }

    public boolean isCompleteFlag() { return completeFlag; }

    public void setCompleteFlag(boolean completeFlag) {
        this.completeFlag = completeFlag;
    }

    public Date getOrder_date() { return order_date; }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public int getReceipt_way() {
        return receipt_way;
    }

    public void setReceipt_way(int receipt_way) { this.receipt_way = receipt_way;
    }
}
