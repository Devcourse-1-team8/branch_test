package team_workshop.core;

import team_workshop.domain.UserDTO;
import team_workshop.infra.JdbcServices;
import team_workshop.infra.UserMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserManager implements Manager {
    private final JdbcServices jdbcService;

    public UserManager(JdbcServices jdbcService) {
        this.jdbcService = jdbcService;
    }

    @Override
    public void addUser(UserDTO user) {
        String sql = """
                insert into Users(
                    name,
                    age,
                    phoneNumber,
                    address,
                    gender
                ) values (?,?,?,?,?)""";
        executeUpdateWithParameter(
                sql,
                user.getName(),
                user.getAge(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getGender()
        );
    }

    @Override
    public void updateUser(int userId, UserDTO user) {
        String sql = buildUpdateQuery(user) + " where userId = ?";
        executeUpdateWithParameter(sql, userId);
    }

    @Override
    public void deleteUser(int userId) {
        String sql = "delete from Users where userId = ?";
        executeUpdateWithParameter(sql, userId);
    }

    @Override
    public List<UserDTO> findAll() {
        String sql = "select name, age, phoneNumber, address, gender from Users";
        return executeSelect(sql);
    }

    @Override
    public List<UserDTO> findByName(String name) {
        String sql = "select name, age, phoneNumber, address, gender from Users where name like ?";
        return executeSelectWithCondition(sql, "%" + name + "%");
    }

    @Override
    public List<UserDTO> findByAddresses(String address) {
        String sql = "select name, age, phoneNumber, address, gender from Users where address like ?";
        return executeSelectWithCondition(sql, "%" + address + "%");
    }

    private String buildUpdateQuery(UserDTO user) {
        // JPA의 소중함,,,
        StringBuilder sqlBuilder = new StringBuilder("update Users set");
        boolean isMultipleColumns = false;
        if (!user.getName().isEmpty()) {
            sqlBuilder.append(" name = '").append(user.getName()).append("'");
            isMultipleColumns = true;
        }
        if (!user.getPhoneNumber().isEmpty()) {
            if (isMultipleColumns) {
                sqlBuilder.append(",");
            }
            sqlBuilder.append(" phoneNumber = '").append(user.getPhoneNumber()).append("'");
            isMultipleColumns = true;
        }
        if (!user.getGender().isEmpty()) {
            if (isMultipleColumns) {
                sqlBuilder.append(",");
            }
            sqlBuilder.append(" gender = '").append(user.getGender()).append("'");
            isMultipleColumns = true;
        }
        if (!user.getAddress().isEmpty()) {
            if (isMultipleColumns) {
                sqlBuilder.append(",");
            }
            sqlBuilder.append(" address = '").append(user.getAddress()).append("'");
            isMultipleColumns = true;
        }
        if (user.getAge() != -1) {
            if (isMultipleColumns) {
                sqlBuilder.append(",");
            }
            sqlBuilder.append(" age = ").append(user.getAge());
        }
        return sqlBuilder.toString();
    }

    private void executeUpdateWithParameter(String sql, Object... parameters) {
        PreparedStatement statement = null;
        try {
            statement = jdbcService.getStatement(sql);
            for (int i = 1; i <= parameters.length; i++) {
                Object parameter = parameters[i + 1];
                if (parameter instanceof String) {
                    statement.setString(i, (String) parameter);
                }
                if (parameter instanceof Integer) {
                    statement.setInt(i, (Integer) parameter);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            jdbcService.close(statement);
        }
    }

    private List<UserDTO> executeSelect(String sql) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = jdbcService.getStatement(sql);
            resultSet = statement.executeQuery();

            return UserMapper.getCollection(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            jdbcService.close(statement, resultSet);
        }
    }

    private List<UserDTO> executeSelectWithCondition(String sql, String... conditions) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = jdbcService.getStatement(sql);
            for (int i = 1; i <= conditions.length; i++) {
                String condition = conditions[i - 1];
                statement.setString(i, condition);
            }
            resultSet = statement.executeQuery();

            return UserMapper.getCollection(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            jdbcService.close(statement, resultSet);
        }
    }
}
