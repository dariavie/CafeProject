package by.training.cafeproject.entity;

import java.util.Objects;

public class Order extends Entity {
    private Integer workerId;
    private Integer clientId;
    private Integer foodId;
    private String clientName;

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public String getClientName() {
        return clientName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(workerId, order.workerId) && Objects.equals(clientId, order.clientId) && Objects.equals(foodId, order.foodId) && Objects.equals(clientName, order.clientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), workerId, clientId, foodId, clientName);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + getId() + '\'' +
                ", workerId=" + workerId +
                ", clientId=" + clientId +
                ", foodId=" + foodId +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
