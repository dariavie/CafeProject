package by.training.cafeproject.domain;

import java.util.ArrayList;
import java.util.Objects;

public class Order extends Entity {
    private UserInfo clientId;
    private Double price;
    private OrderStatus orderStatus;
    private ArrayList<Food> foods;

    public Order() {}

    public Order(Integer id) {
        setId(id);
    }
    public void setClientId(UserInfo client) {
        this.clientId = client;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public UserInfo getClientId() {
        return clientId;
    }

    public Double getPrice() {
        return price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientId=" + clientId +
                ", price=" + price +
                ", orderStatus=" + orderStatus +
                "} " + super.toString();
    }
}
