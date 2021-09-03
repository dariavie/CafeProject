package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.ConnectorDB;
import by.training.cafeproject.dao.IngredientDao;
import by.training.cafeproject.entity.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDaoImpl implements IngredientDao {
    private Connection connection = ConnectorDB.getConnection();

    @Override
    public Integer create(Ingredient entity) {
        try {
            String sql = "INSERT INTO ingredients (id, title) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getTitle());
            statement.executeUpdate();
            statement.close();
            return 1;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public Ingredient read(Integer id) {
        try {
            String sql = "SELECT title FROM ingredients WHERE id = ?";
            Ingredient ingredient = new Ingredient();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ingredient.setId(id);
                ingredient.setTitle(rs.getString("title"));
            }
            ps.close();
            return ingredient;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void update(Ingredient entity) {
        try {
            String sql = "UPDATE ingredients SET title = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(2, entity.getId());
            statement.setString(1, entity.getTitle());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.getMessage();
        }

    }

    @Override
    public List<Ingredient> read() {
        try {
            String sql = "SELECT id, title FROM ingredients";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Ingredient> ingredients = new ArrayList<>();
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(rs.getInt(1));
                ingredient.setTitle(rs.getString(2));
                ingredients.add(ingredient);
            }
            return ingredients;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM ingredients WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void delete(Ingredient entity) {
        try {
            String sql = "DELETE FROM ingredients WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public List<Ingredient> readByTitle(String search) {
        try {
            List<Ingredient> ingredients = new ArrayList<>();
            String sql = "SELECT id FROM ingredients WHERE title = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, search);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setTitle(search);
                ingredient.setId(rs.getInt("id"));
                ingredients.add(ingredient);
            }
            ps.close();
            return ingredients;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void deleteByTitle(String title) {
        try {
            String sql = "DELETE FROM ingredients WHERE title = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
