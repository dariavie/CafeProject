package by.training.cafeproject.domain;

import java.util.Objects;

public class Order extends Entity {
    private Worker workerId;
    private User clientId;
    private Food foodId;
    private String clientName;

    public void setWorkerId(Worker worker) {
        this.workerId = worker;
    }

    public void setClientId(User client) {
        this.clientId = client;
    }

    public void setFoodId(Food food) {
        this.foodId = food;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Worker getWorkerId() {
        return workerId;
    }

    public User getClientId() {
        return clientId;
    }

    public Food getFoodId() {
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
                ", worker=" + workerId +
                ", client=" + clientId +
                ", food=" + foodId +
                ", clientName='" + clientName + '\'' +
                "} " + super.toString();
    }
}
