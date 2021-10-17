package by.training.cafeproject.domain;

public enum FoodType {
    DISH("блюдо"),
    DRINK("напиток");

    private String name;

    private FoodType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return ordinal();
    }

    public static FoodType getById(Integer id) {
        return FoodType.values()[id-1];
    }
}
