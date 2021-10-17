package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.IngredientDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDaoImpl extends BaseDaoImpl implements IngredientDao {
    private static final String SQL_CREATE = "INSERT INTO ingredients (title) VALUES (?)";
    private static final String SQL_READ_BY_ID = "SELECT title FROM ingredients WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE ingredients SET title = ? WHERE id = ?";
    private static final String SQL_READ_ALL = "SELECT id, title FROM ingredients";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM ingredients WHERE id = ?";
    private static final String SQL_READ_BY_TITLE = "SELECT id FROM ingredients WHERE title = ?";
    private static final String SQL_DELETE_BY_TITLE = "DELETE FROM ingredients WHERE title = ?";

    @Override
    public Integer create(Ingredient entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setString(1, entity.getTitle());
            statement.executeUpdate();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                entity.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
            return entity.getId();
        }
    }

    @Override
    public Ingredient read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            Ingredient ingredient = new Ingredient();
            statement = connection.prepareStatement(SQL_READ_BY_ID);
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
            statement = connection.prepareStatement(SQL_UPDATE);
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
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_READ_ALL);
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
            statement = connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public Ingredient readByTitle(String search) throws DaoException {
        PreparedStatement statement = null;
        try {
            Ingredient ingredient = new Ingredient();
            statement = connection.prepareStatement(SQL_READ_BY_TITLE);
            statement.setString(1, search);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ingredient.setTitle(search);
                ingredient.setId(rs.getInt("id"));
            }
            return ingredient;
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
            statement = connection.prepareStatement(SQL_DELETE_BY_TITLE);
            statement.setString(1, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
