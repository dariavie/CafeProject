package by.training.cafeproject.entity;

import java.util.Objects;

public class Food extends Entity {
    private String title;
    private String description;
    private Double price;
    private FoodType type;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(int type) {
        if (type==1) {
            this.type = FoodType.DISH;
        } else if (type==2) {
            this.type = FoodType.DRINK;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public FoodType getType() {
        return type;
    }

    public int getTypeNumber() {
        if (type.equals(FoodType.DISH)) {
            return 1;
        } else if (type.equals(FoodType.DRINK)) {
            return 2;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0 && Objects.equals(title, food.title) && Objects.equals(description, food.description) && type == food.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, price, type);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + getId() + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
