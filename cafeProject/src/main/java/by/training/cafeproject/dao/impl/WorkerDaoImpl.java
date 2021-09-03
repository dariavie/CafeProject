package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.ConnectorDB;
import by.training.cafeproject.dao.WorkerDao;
import by.training.cafeproject.entity.Ingredient;
import by.training.cafeproject.entity.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerDaoImpl implements WorkerDao {
    private Connection connection = ConnectorDB.getConnection();

    @Override
    public Integer create(Worker entity) {
        try {
            String sql = "INSERT INTO workers (id, start_of_work, end_of_work, specialization) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setDate(2, entity.getStartOfWork());
            statement.setDate(3, entity.getEndOfWork());
            statement.setString(4, entity.getSpecialization());
            statement.executeUpdate();
            statement.close();
            return 1;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public Worker read(Integer id) {
        try {
            String sql = "SELECT start_of_work, end_of_work, specialization FROM workers WHERE id = ?";
            Worker worker = new Worker();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                worker.setId(id);
                worker.setStartOfWork(rs.getDate("start_of_work"));
                worker.setEndOfWork(rs.getDate("end_of_work"));
                worker.setSpecialization(rs.getString("specialization"));
            }
            ps.close();
            return worker;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void update(Worker entity) {
        try {
            String sql = "UPDATE workers SET start_of_work = ?, end_of_work = ?, specialization = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(4, entity.getId());
            statement.setDate(1, entity.getStartOfWork());
            statement.setDate(2, entity.getEndOfWork());
            statement.setString(3, entity.getSpecialization());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public List<Worker> read() {
        try {
            String sql = "SELECT id, start_of_work, end_of_work, specialization FROM workers";
            Statement statement = connection.createStatement();
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
            e.getMessage();
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM workers WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void delete(Worker entity) {
        try {
            String sql = "DELETE FROM workers WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public List<Worker> readByStartDate(Date date) {
        try {
            List<Worker> workers = new ArrayList<>();
            String sql = "SELECT id, end_of_work, specialization FROM workers WHERE start_of_work = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setStartOfWork(date);
                worker.setId(rs.getInt("id"));
                worker.setEndOfWork(rs.getDate("end_of_work"));
                worker.setSpecialization(rs.getString("specialization"));
                workers.add(worker);
            }
            ps.close();
            return workers;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        } //TODO doesn't work with data that has already been in the table
    }

    @Override
    public List<Worker> readByEndDate(Date date) {
        try {
            List<Worker> workers = new ArrayList<>();
            String sql = "SELECT id, start_of_work, specialization FROM workers WHERE end_of_work = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setEndOfWork(date);
                worker.setId(rs.getInt("id"));
                worker.setStartOfWork(rs.getDate("start_of_work"));
                worker.setSpecialization(rs.getString("specialization"));
                workers.add(worker);
            }
            ps.close();
            return workers;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        } //TODO doesn't work with data that has already been in the table and with null
    }

    @Override
    public List<Worker> readBySpecialization(String search) {
        try {
            List<Worker> workers = new ArrayList<>();
            String sql = "SELECT id, start_of_work, end_of_work FROM workers WHERE specialization = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, search);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setSpecialization(search);
                worker.setId(rs.getInt("id"));
                worker.setStartOfWork(rs.getDate("start_of_work"));
                worker.setEndOfWork(rs.getDate("end_of_work"));
                workers.add(worker);
            }
            ps.close();
            return workers;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void deleteBySpecialization(String specialization) {
        try {
            String sql = "DELETE FROM workers WHERE specialization = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, specialization);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
