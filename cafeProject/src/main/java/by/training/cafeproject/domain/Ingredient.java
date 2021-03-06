package by.training.cafeproject.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ingredient extends Entity {
    private String title;

    public Ingredient() {}

    public Ingredient(Integer id) {
        setId(id);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "title='" + title + '\'' +
                "} " + super.toString();
    }
}