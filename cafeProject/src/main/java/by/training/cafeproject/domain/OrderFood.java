package by.training.cafeproject.domain;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderFood orderFood = (OrderFood) o;
        return Objects.equals(orderId, orderFood.orderId) && Objects.equals(foodId, orderFood.foodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, foodId);
    }

    @Override
    public String toString() {
        return "OrderFood{" +
                "orderId=" + orderId +
                ", foodId=" + foodId +
                "} " + super.toString();
    }
}
