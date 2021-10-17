package by.training.cafeproject.domain;

public class OrderFood extends Entity {
    private Order orderId;
    private Food foodId;

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public Food getFoodId() {
        return foodId;
    }

    @Override
    public String toString() {
        return "OrderFood{" +
                "orderId=" + orderId +
                ", foodId=" + foodId +
                "} " + super.toString();
    }
}
