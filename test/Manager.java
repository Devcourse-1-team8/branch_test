package Day0731.NewTask;

import java.sql.SQLException;
import java.util.List;

public interface Manager {
    UserDTO addUser(UserDTO user) throws SQLException;
    UserDTO updateUser(int userId, UserDTO user) throws SQLException;
    void deleteUser(int userId) throws SQLException;
    List<UserDTO> findAll() throws SQLException;
    List<UserDTO> findByName(String name) throws SQLException;
    List<UserDTO> findByAddresses(String address) throws SQLException;
}