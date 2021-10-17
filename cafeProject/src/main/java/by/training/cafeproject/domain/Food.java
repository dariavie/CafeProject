package by.training.cafeproject.domain;

import java.util.List;
import java.util.Objects;

public class Food extends Entity {
    private String title;
    private String description;
    private Double price;
    private FoodType type;
    private String recipe;
    private List<Ingredient> ingredients;

    public Food() {}

    public Food(Integer id) {
        setId(id);
    }

    public Food(Integer id, String title, String description,
                Double price, FoodType type, String recipe) {
        this.setId(id);
        this.title = title;
        this.description = description;
        this.price = price;
        this.type = type;
        this.recipe = recipe;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    public String getRecipe() {
        return recipe;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Food food = (Food) o;
        return Objects.equals(title, food.title) && Objects.equals(description, food.description) && Objects.equals(price, food.price) && type == food.type && Objects.equals(recipe, food.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, price, type, recipe);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + getId() + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", recipe='" + recipe + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
