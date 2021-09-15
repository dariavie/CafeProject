package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.WorkerDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerDaoImpl extends BaseDaoImpl implements WorkerDao {

    @Override
    public void create(Worker entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO workers (id, start_of_work, end_of_work, specialization) VALUES (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setDate(2, entity.getStartOfWork());
            statement.setDate(3, entity.getEndOfWork());
            statement.setString(4, entity.getSpecialization());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public Worker read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT start_of_work, end_of_work, specialization FROM workers WHERE id = ?";
            Worker worker = new Worker();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                worker.setId(id);
                worker.setStartOfWork(rs.getDate("start_of_work"));
                worker.setEndOfWork(rs.getDate("end_of_work"));
                worker.setSpecialization(rs.getString("specialization"));
            }
            return worker;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void update(Worker entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE workers SET start_of_work = ?, end_of_work = ?, specialization = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(4, entity.getId());
            statement.setDate(1, entity.getStartOfWork());
            statement.setDate(2, entity.getEndOfWork());
            statement.setString(3, entity.getSpecialization());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<Worker> read() throws DaoException {
        Statement statement = null;
        try {
            String sql = "SELECT id, start_of_work, end_of_work, specialization FROM workers";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Worker> workers = new ArrayList<>();
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setId(rs.getInt("id"));
                worker.setStartOfWork(rs.getDate("start_of_work"));
                worker.setEndOfWork(rs.getDate("end_of_work"));
                worker.setSpecialization(rs.getString("specialization"));
                workers.add(worker);
            }
            return workers;
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
            String sql = "DELETE FROM workers WHERE id = ?";
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
    public void delete(Worker entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM workers WHERE id = ?";
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
    public List<Worker> readByStartDate(Date date) throws DaoException {
        PreparedStatement statement = null;
        try {
            List<Worker> workers = new ArrayList<>();
            String sql = "SELECT id, end_of_work, specialization FROM workers WHERE start_of_work = ?";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, date);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setStartOfWork(date);
                worker.setId(rs.getInt("id"));
                worker.setEndOfWork(rs.getDate("end_of_work"));
                worker.setSpecialization(rs.getString("specialization"));
                workers.add(worker);
            }
            return workers;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
        //TODO doesn't work with data that has already been in the table
    }

    @Override
    public List<Worker> readByEndDate(Date date) throws DaoException {
        PreparedStatement statement = null;
        try {
            List<Worker> workers = new ArrayList<>();
            String sql = "SELECT id, start_of_work, specialization FROM workers WHERE end_of_work = ?";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, date);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setEndOfWork(date);
                worker.setId(rs.getInt("id"));
                worker.setStartOfWork(rs.getDate("start_of_work"));
                worker.setSpecialization(rs.getString("specialization"));
                workers.add(worker);
            }
            return workers;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
        //TODO doesn't work with data that has already been in the table and with null
    }

    @Override
    public List<Worker> readBySpecialization(String search) throws DaoException {
        PreparedStatement statement = null;
        try {
            List<Worker> workers = new ArrayList<>();
            String sql = "SELECT id, start_of_work, end_of_work FROM workers WHERE specialization = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, search);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setSpecialization(search);
                worker.setId(rs.getInt("id"));
                worker.setStartOfWork(rs.getDate("start_of_work"));
                worker.setEndOfWork(rs.getDate("end_of_work"));
                workers.add(worker);
            }
            return workers;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteBySpecialization(String specialization) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM workers WHERE specialization = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, specialization);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }  finally {
            this.closePreparedStatement(statement);
        }
    }
}
