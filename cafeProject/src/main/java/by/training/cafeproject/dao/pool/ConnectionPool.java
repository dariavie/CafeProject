package by.training.cafeproject.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

final public class ConnectionPool {
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);

    private String url;
    private String user;
    private String password;
    private int maxSize;
    private int checkConnectionTimeout;

    private BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
    private Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

    private ConnectionPool() {}

    public synchronized Connection getConnection() throws Exception {
        PooledConnection connection = null;
        while(connection == null) {
            try {
                if(!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if(!connection.isValid(checkConnectionTimeout)) {
                        try {
                            connection.getConnection().close();
                        } catch(SQLException e) {}
                        connection = null;
                    }
                } else if(usedConnections.size() < maxSize) {
                    connection = createConnection();
                } else {
                    logger.error("The limit of number of database connections is exceeded");
                    throw new Exception();
                }
            } catch(InterruptedException | SQLException e) {
                logger.error("It is impossible to connect to a database", e);
                throw new Exception(e);
            }
        }
        usedConnections.add(connection);
        logger.debug(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
        return connection;
    }

    synchronized void freeConnection(PooledConnection connection) {
        try {
            if(connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                logger.debug(String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
            }
        } catch(SQLException | InterruptedException e1) {
            logger.warn("It is impossible to return database connection into pool", e1);
            try {
                connection.getConnection().close();
            } catch(SQLException e2) {}
        }
    }

    public synchronized void init() throws Exception {
        try {
            destroy();
            ResourceBundle resource = ResourceBundle.getBundle("db");
            Class.forName(resource.getString("db.driver"));
            this.url = resource.getString("db.url");
            this.user = resource.getString("db.user");
            this.password = resource.getString("db.password");
            this.maxSize = Integer.parseInt(resource.getString("db.maxSize"));
            this.checkConnectionTimeout = Integer.parseInt(resource.getString("db.connectionTimeout"));
            int startSize = Integer.parseInt(resource.getString("db.startSize"));
            for(int counter = 0; counter < startSize; counter++) {
                freeConnections.put(createConnection());
            }
        } catch(SQLException | InterruptedException e) {
            logger.fatal("It is impossible to initialize connection pool", e);
            throw new Exception(e);
        }
    }

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    private PooledConnection createConnection() throws SQLException {
        return new PooledConnection(DriverManager.getConnection(url, user, password));
    }

    public synchronized void destroy() {
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for(PooledConnection connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch(SQLException e) {}
        }
        usedConnections.clear();
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }
}
