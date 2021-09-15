package by.training.cafeproject.domain;

import java.util.Objects;

public class Rating extends Entity {
    private User clientId;
    private Food foodId;
    private String clientName;
    private Integer rating;

    public Rating() {}

    public Rating(Integer id, User client, Food food, String clientName, Integer rating) {
        this.setId(id);
        this.clientId = client;
        this.foodId = food;
        this.clientName = clientName;
        this.rating = rating;
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

    public void setRating(Integer rating) {
        this.rating = rating;
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

    public Integer getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rating rating1 = (Rating) o;
        return Objects.equals(clientId, rating1.clientId) && Objects.equals(foodId, rating1.foodId) && Objects.equals(clientName, rating1.clientName) && Objects.equals(rating, rating1.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientId, foodId, clientName, rating);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id='" + '\'' +
                ", client=" + clientId +
                ", food=" + foodId +
                ", clientName='" + clientName + '\'' +
                ", rating=" + rating +
                "} " + super.toString();
    }
}
