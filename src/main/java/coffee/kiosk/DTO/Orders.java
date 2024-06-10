package coffee.kiosk.DTO;

import java.util.Date;

public class Orders {

    int orderIdx;           // 주문 인덱스 (PK)
    int paymentId;          // 결제 수단
    int possibleIdx;        // 선택된 음료 및 옵션
    int orderId;            // 주문 ID (같은 주문)
    String foodName;        // 음료 이름
    int foodPrice;          // 음료 가격
    String optionName;      // 옵션 이름
    int optionPrice;        // 옵션 가격
    int foodCount;          // 음료 수량
    boolean completeFlag;   // 완료 여부
    Date orderDate;         // 주문 일자
    int receiptWay;         // 수령 방법
    int foodKey;            // 음료 Key (같은 음료)



    public int getOrderIdx() {
        return orderIdx;
    }

    public void setOrderIdx(int orderIdx) {
        this.orderIdx = orderIdx;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPossibleIdx() {
        return possibleIdx;
    }

    public void setPossibleIdx(int possibleIdx) {
        this.possibleIdx = possibleIdx;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
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

    public int getOptionPrice() {
        return optionPrice;
    }

    public void setOptionPrice(int optionPrice) {
        this.optionPrice = optionPrice;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public boolean isCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(boolean completeFlag) {
        this.completeFlag = completeFlag;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getReceiptWay() {
        return receiptWay;
    }

    public void setReceiptWay(int receiptWay) {
        this.receiptWay = receiptWay;
    }

    public int getFoodKey() {
        return foodKey;
    }

    public void setFoodKey(int foodKey) {
        this.foodKey = foodKey;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
