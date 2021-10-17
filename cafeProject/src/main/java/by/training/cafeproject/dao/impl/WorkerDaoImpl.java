package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.WorkerDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.domain.Worker;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WorkerDaoImpl extends BaseDaoImpl implements WorkerDao {
    private static final Logger logger = Logger.getLogger(WorkerDaoImpl.class);
    private static final String SQL_CREATE = "INSERT INTO workers (id, start_of_work, end_of_work, specialization) VALUES (?,?,?,?)";
    private static final String SQL_READ_BY_ID = "SELECT start_of_work, end_of_work, specialization FROM workers WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE workers SET start_of_work = ?, end_of_work = ?, specialization = ? WHERE id = ?";
    private static final String SQL_READ_ALL = "SELECT id, start_of_work, end_of_work, specialization FROM workers";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM workers WHERE id = ?";
    private static final String SQL_READ_BY_SPECIALIZATION = "SELECT id, start_of_work, end_of_work FROM workers WHERE specialization = ?";
    private static final String SQL_DELETE_BY_SPECIALIZATION = "DELETE FROM workers WHERE specialization = ?";

    @Override
    public Integer create(Worker entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            logger.info("start of worker dao");
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setInt(1, entity.getUserInfoId().getId());
            statement.setDate(2, new Date(entity.getStartOfWork().getTime()));
            if (entity.getEndOfWork() != null) {
                statement.setDate(3, new Date(entity.getEndOfWork().getTime()));
            } else {
                statement.setDate(3, null);
            }
            statement.setString(4, entity.getSpecialization());
            statement.executeUpdate();
            ResultSet rs = statement.executeQuery();
            logger.info("worker created");
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
    public Worker read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            Worker worker = new Worker();
            statement = connection.prepareStatement(SQL_READ_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                worker.setId(id);
                worker.setUserInfoId(new UserInfo(id));
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
            statement = connection.prepareStatement(SQL_UPDATE);
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
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_READ_ALL);
            List<Worker> workers = new ArrayList<>();
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setId(rs.getInt("id"));
                worker.setUserInfoId(new UserInfo(rs.getInt("id")));
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
    public List<Worker> readBySpecialization(String search) throws DaoException {
        PreparedStatement statement = null;
        try {
            List<Worker> workers = new ArrayList<>();
            statement = connection.prepareStatement(SQL_READ_BY_SPECIALIZATION);
            statement.setString(1, search);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Worker worker = new Worker();
                worker.setSpecialization(search);
                worker.setId(rs.getInt("id"));
                worker.setUserInfoId(new UserInfo(rs.getInt("id")));
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
            statement = connection.prepareStatement(SQL_DELETE_BY_SPECIALIZATION);
            statement.setString(1, specialization);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }  finally {
            this.closePreparedStatement(statement);
        }
    }
}
