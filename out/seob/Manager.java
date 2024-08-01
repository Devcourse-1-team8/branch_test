package seob;

import java.util.List;

public interface Manager {

    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(int userId, UserDTO userDTO);


    boolean deleteUser(int userId);

    List<UserDTO> findAll();

    List<UserDTO> findByName(String name);

    List<UserDTO> findByAddress(String address);

    void close();

}
