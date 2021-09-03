package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.ConnectorDB;
import by.training.cafeproject.dao.FoodIngredientDao;
import by.training.cafeproject.entity.Food;
import by.training.cafeproject.entity.FoodIngredient;
import by.training.cafeproject.entity.Ingredient;
import by.training.cafeproject.entity.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodIngredientImpl implements FoodIngredientDao {
    private Connection connection = ConnectorDB.getConnection();

    @Override
    public Integer create(FoodIngredient entity) {
        try {
            String sql = "INSERT INTO foods_ingredients (id, food_id, ingredient_id, ingredient_amount) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setInt(2, entity.getFoodId());
            statement.setInt(3, entity.getIngredientId());
            statement.setString(4, entity.getIngredientAmount());
            statement.executeUpdate();
            statement.close();
            return 1;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public FoodIngredient read(Integer id) {
        try {
            String sql = "SELECT food_id, ingredient_id, ingredient_amount FROM foods_ingredients WHERE id = ?";
            FoodIngredient foodIngredient = new FoodIngredient();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                foodIngredient.setId(id);
                foodIngredient.setFoodId(rs.getInt("food_id"));
                foodIngredient.setIngredientId(rs.getInt("ingredient_id"));
                foodIngredient.setIngredientAmount(rs.getString("ingredient_amount"));
            }
            ps.close();
            return foodIngredient;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void update(FoodIngredient entity) {
        try {
            String sql = "UPDATE foods_ingredients SET food_id = ?, ingredient_id = ?, ingredient_amount = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(4, entity.getId());
            statement.setInt(1, entity.getFoodId());
            statement.setInt(2, entity.getIngredientId());
            statement.setString(3, entity.getIngredientAmount());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public List<FoodIngredient> read() {
        try {
            String sql = "SELECT id, food_id, ingredient_id, ingredient_amount FROM foods_ingredients";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<FoodIngredient> foodsIngredients = new ArrayList<>();
            while (rs.next()) {
                FoodIngredient foodIngredient = new FoodIngredient();
                foodIngredient.setId(rs.getInt("id"));
                foodIngredient.setFoodId(rs.getInt("food_id"));
                foodIngredient.setIngredientId(rs.getInt("ingredient_id"));
                foodIngredient.setIngredientAmount(rs.getString("ingredient_amount"));
                foodsIngredients.add(foodIngredient);
            }
            return foodsIngredients;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM foods_ingredients WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void delete(FoodIngredient entity) {
        try {
            String sql = "DELETE FROM foods_ingredients WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void deleteByFoodId(Integer foodId) {
        try {
            String sql = "DELETE FROM foods_ingredients WHERE food_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, foodId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void deleteByIngredientId(Integer ingredientId) {
        try {
            String sql = "DELETE FROM foods_ingredients WHERE ingredient_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ingredientId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public List<FoodIngredient> readByFoodId(Integer foodId) {
        try {
            String sql = "SELECT id, ingredient_id, ingredient_amount FROM foods_ingredients WHERE food_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, foodId);
            ResultSet rs = ps.executeQuery();
            List<FoodIngredient> foodsIngredients = new ArrayList<>();
            while (rs.next()) {
                FoodIngredient foodIngredient = new FoodIngredient();
                foodIngredient.setFoodId(foodId);
                foodIngredient.setId(rs.getInt("id"));
                foodIngredient.setIngredientId(rs.getInt("ingredient_id"));
                foodIngredient.setIngredientAmount(rs.getString("ingredient_amount"));
                foodsIngredients.add(foodIngredient);
            }
            return foodsIngredients;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<FoodIngredient> readByIngredientId(Integer ingredientId) {
        try {
            String sql = "SELECT id, food_id, ingredient_amount FROM foods_ingredients WHERE ingredient_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ingredientId);
            ResultSet rs = ps.executeQuery();
            List<FoodIngredient> foodsIngredients = new ArrayList<>();
            while (rs.next()) {
                FoodIngredient foodIngredient = new FoodIngredient();
                foodIngredient.setIngredientId(ingredientId);
                foodIngredient.setId(rs.getInt("id"));
                foodIngredient.setFoodId(rs.getInt("food_id"));
                foodIngredient.setIngredientAmount(rs.getString("ingredient_amount"));
                foodsIngredients.add(foodIngredient);
            }
            return foodsIngredients;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }
}
