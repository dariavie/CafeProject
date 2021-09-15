package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.IngredientDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDaoImpl extends BaseDaoImpl implements IngredientDao {

    @Override
    public void create(Ingredient entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO ingredients (id, title) VALUES (?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public Ingredient read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT title FROM ingredients WHERE id = ?";
            Ingredient ingredient = new Ingredient();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ingredient.setId(id);
                ingredient.setTitle(rs.getString("title"));
            }
            return ingredient;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void update(Ingredient entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE ingredients SET title = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(2, entity.getId());
            statement.setString(1, entity.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }

    }

    @Override
    public List<Ingredient> read() throws DaoException {
        Statement statement = null;
        try {
            String sql = "SELECT id, title FROM ingredients";
            statement = connection.createStatement();
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
            throw new DaoException(e);
        } finally {
            this.closeStatement(statement);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM ingredients WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void delete(Ingredient entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM ingredients WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<Ingredient> readByTitle(String search) throws DaoException {
        PreparedStatement statement = null;
        try {
            List<Ingredient> ingredients = new ArrayList<>();
            String sql = "SELECT id FROM ingredients WHERE title = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, search);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setTitle(search);
                ingredient.setId(rs.getInt("id"));
                ingredients.add(ingredient);
            }
            return ingredients;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteByTitle(String title) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM ingredients WHERE title = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
