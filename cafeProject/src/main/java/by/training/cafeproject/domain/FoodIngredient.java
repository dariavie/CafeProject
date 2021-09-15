package by.training.cafeproject.domain;

import java.util.Objects;

public class FoodIngredient extends Entity{
    private Food foodId;
    private Ingredient ingredientId;
    private String ingredientAmount;

    public void setFoodId(Food food) {
        this.foodId = food;
    }

    public void setIngredientId(Ingredient ingredient) {
        this.ingredientId = ingredient;
    }

    public void setIngredientAmount(String ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public Food getFoodId() {
        return foodId;
    }

    public Ingredient getIngredientId() {
        return ingredientId;
    }

    public String getIngredientAmount() {
        return ingredientAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoodIngredient that = (FoodIngredient) o;
        return Objects.equals(foodId, that.foodId) && Objects.equals(ingredientId, that.ingredientId) && Objects.equals(ingredientAmount, that.ingredientAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), foodId, ingredientId, ingredientAmount);
    }

    @Override
    public String toString() {
        return "FoodIngredient{" +
                "id='" + getId() + '\'' +
                ", food=" + foodId +
                ", ingredient=" + ingredientId +
                ", ingredientAmount='" + ingredientAmount + '\'' +
                "} ";
    }
}
