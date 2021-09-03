package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.ConnectorDB;
import by.training.cafeproject.dao.FoodDao;
import by.training.cafeproject.entity.Food;
import by.training.cafeproject.entity.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    private Connection connection = ConnectorDB.getConnection();

    @Override
    public Integer create(Food entity) {
        try {
            String sql = "INSERT INTO foods (id, title, description, price, type) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getTitle());
            statement.setString(3, entity.getDescription());
            statement.setDouble(4, entity.getPrice());
            statement.setInt(5, entity.getTypeNumber());
            statement.executeUpdate();
            statement.close();
            return 1;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public Food read(Integer id) {
        try {
            String sql = "SELECT title, description, price, type FROM foods WHERE id = ?";
            Food food = new Food();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                food.setId(id);
                food.setTitle(rs.getString("title"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getDouble("price"));
                food.setType(rs.getInt("type"));
            }
            ps.close();
            return food;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void update(Food entity) {
        try {
            String sql = "UPDATE foods SET title = ?, description = ?, price = ?, type = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(5, entity.getId());
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getDescription());
            statement.setDouble(3, entity.getPrice());
            statement.setInt(4, entity.getTypeNumber());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public List<Food> read() {
        try {
            String sql = "SELECT id, title, description, price, type FROM foods";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Food> foods = new ArrayList<>();
            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getInt("id"));
                food.setTitle(rs.getString("title"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getDouble("price"));
                food.setType(rs.getInt("type"));
                foods.add(food);
            }
            return foods;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM foods WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void delete(Food entity) {
        try {
            String sql = "DELETE FROM foods WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public List<Food> readByTitle(String search) {
        try {
            String sql = "SELECT id, description, price, type FROM foods WHERE title = ?";
            List<Food> foods = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, search);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setTitle(search);
                food.setId(rs.getInt("id"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getDouble("price"));
                food.setType(rs.getInt("type"));
                foods.add(food);
            }
            ps.close();
            return foods;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Food> readByPrice(Double search) {
        try {
            String sql = "SELECT id, title, description, type FROM foods WHERE price = ?";
            List<Food> foods = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, search);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setPrice(search);
                food.setTitle(rs.getString("title"));
                food.setId(rs.getInt("id"));
                food.setDescription(rs.getString("description"));
                food.setType(rs.getInt("type"));
                foods.add(food);
            }
            ps.close();
            return foods;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Food> readByType(Integer typeNumber) {
        try {
            String sql = "SELECT id, title, description, price FROM foods WHERE type = ?";
            List<Food> foods = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, typeNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setType(typeNumber);
                food.setTitle(rs.getString("title"));
                food.setId(rs.getInt("id"));
                food.setDescription(rs.getString("description"));
                food.setPrice(typeNumber);
                foods.add(food);
            }
            ps.close();
            return foods;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void deleteByTitle(String title) {
        try {
            String sql = "DELETE FROM foods WHERE title = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void deleteByType(Integer typeNumber) {
        try {
            String sql = "DELETE FROM foods WHERE type = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, typeNumber);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
