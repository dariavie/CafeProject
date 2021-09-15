package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.RatingDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.Rating;
import by.training.cafeproject.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDaoImpl extends BaseDaoImpl implements RatingDao {

    @Override
    public void create(Rating entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO ratings (id, client_id, client_name, food_id, rating) VALUES (?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setInt(2, entity.getClientId().getId());
            statement.setString(3, entity.getClientName());
            statement.setInt(4, entity.getFoodId().getId());
            statement.setInt(5, entity.getRating());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public Rating read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT client_id, client_name, food_id, rating FROM ratings WHERE id = ?";
            Rating rating = new Rating();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                rating.setId(id);
                rating.setClientId(new User(rs.getInt("client_id")));
                rating.setClientName(rs.getString("client_name"));
                rating.setFoodId(new Food(rs.getInt("food_id")));
                rating.setRating(rs.getInt("rating"));
            }
            return rating;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void update(Rating entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE ratings SET client_id = ?, client_name = ?, food_id = ?, rating = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(5, entity.getId());
            statement.setInt(1, entity.getClientId().getId());
            statement.setString(2, entity.getClientName());
            statement.setInt(3, entity.getFoodId().getId());
            statement.setInt(4, entity.getRating());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<Rating> read() throws DaoException {
        Statement statement = null;
        try {
            String sql = "SELECT id, client_id, client_name, food_id, rating FROM ratings";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Rating> ratings = new ArrayList<>();
            while (rs.next()) {
                Rating rating = new Rating();
                rating.setId(rs.getInt("id"));
                rating.setClientId(new User(rs.getInt("client_id")));
                rating.setClientName(rs.getString("client_name"));
                rating.setFoodId(new Food(rs.getInt("food_id")));
                rating.setRating(rs.getInt("rating"));
                ratings.add(rating);
            }
            return ratings;
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
            String sql = "DELETE FROM ratings WHERE id = ?";
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
    public void delete(Rating entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM ratings WHERE id = ?";
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
    public List<Rating> readByClientId(Integer clientId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, client_name, food_id, rating FROM ratings WHERE client_id = ?";
            List<Rating> ratings = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating();
                rating.setClientId(new User(clientId));
                rating.setId(rs.getInt("id"));
                rating.setClientName(rs.getString("client_name"));
                rating.setFoodId(new Food(rs.getInt("food_id")));
                rating.setRating(rs.getInt("rating"));
                ratings.add(rating);
            }
            return ratings;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<Rating> readByClientName(String clientName) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, client_id, food_id, rating FROM ratings WHERE client_name = ?";
            List<Rating> ratings = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setString(1, clientName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating();
                rating.setClientName(clientName);
                rating.setId(rs.getInt("id"));
                rating.setClientId(new User(rs.getInt("client_id")));
                rating.setFoodId(new Food(rs.getInt("food_id")));
                rating.setRating(rs.getInt("rating"));
                ratings.add(rating);
            }
            return ratings;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<Rating> readByFoodId(Integer foodId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, client_id, client_name, rating FROM ratings WHERE food_id = ?";
            List<Rating> ratings = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, foodId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating();
                rating.setFoodId(new Food(foodId));
                rating.setId(rs.getInt("id"));
                rating.setClientId(new User(rs.getInt("client_id")));
                rating.setClientName(rs.getString("client_name"));
                rating.setRating(rs.getInt("rating"));
                ratings.add(rating);
            }
            return ratings;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<Rating> readByRating(Integer ratingInt) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, client_id, client_name, food_id FROM ratings WHERE rating = ?";
            List<Rating> ratings = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, ratingInt);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating();
                rating.setRating(ratingInt);
                rating.setId(rs.getInt("id"));
                rating.setClientId(new User(rs.getInt("client_id")));
                rating.setClientName(rs.getString("client_name"));
                rating.setFoodId(new Food(rs.getInt("food_id")));
                ratings.add(rating);
            }
            return ratings;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteByClientId(Integer clientId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM ratings WHERE client_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteByFoodId(Integer foodId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM ratings WHERE food_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, foodId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
