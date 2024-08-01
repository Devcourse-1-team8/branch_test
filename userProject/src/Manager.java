import java.sql.SQLException;
import java.util.List;

public interface Manager {
    int addUser(int id, UserDTO user) throws SQLException;
    int updateUser(String name, int cmd, String tmpStr) throws SQLException;
    int deleteUser(String name) throws SQLException;
    List<UserDTO> findAll() throws SQLException;
    List<UserDTO> findByName(String name) throws SQLException;
    List<UserDTO> findByAddresses(String address) throws SQLException;
}
