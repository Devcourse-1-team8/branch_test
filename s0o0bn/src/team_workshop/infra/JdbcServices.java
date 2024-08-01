package team_workshop.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcServices {
    private static final String DB_URL = "jdbc:mysql://localhost/workshop";
    private static final String USER = "root";
    private static final String PASSWORD = "####";

    private static JdbcServices instance;

    private Connection conn;

    private JdbcServices() {}

    public static JdbcServices getInstance() {
        if (instance == null) {
            instance = new JdbcServices();
        }
        return instance;
    }

    public void connectDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("DB 연결에 실패했습니다.");
        }
    }

    public PreparedStatement getStatement(String SQL) {
        try {
            return conn.prepareStatement(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(AutoCloseable... closeables) {
        for (AutoCloseable closeable : closeables) {
            if (closeable == null) continue;
            try {
                closeable.close();
            } catch (Exception e) {
                throw new RuntimeException("DB 해제 중 오류가 발생했습니다.");
            }
        }
    }

    public void terminateConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB 해제 중 오류가 발생했습니다.");
        }
    }
}
