package by.training.cafeproject.entity;

import java.util.Objects;

public class FoodIngredient extends Entity{
    private Integer foodId;
    private Integer ingredientId;
    private String ingredientAmount;

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredientAmount(String ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public Integer getIngredientId() {
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
                "id=" + getId() +
                ", foodId=" + foodId +
                ", ingredientId=" + ingredientId +
                ", ingredientAmount='" + ingredientAmount + '\'' +
                "}";
    }
}
