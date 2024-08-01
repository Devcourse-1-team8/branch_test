package team_workshop.core;

import team_workshop.domain.UserDTO;

import java.util.List;

public interface Manager {
    void addUser(UserDTO user);

    void updateUser(int userId, UserDTO user);

    void deleteUser(int userId);

    List<UserDTO> findAll();

    List<UserDTO> findByName(String name);

    List<UserDTO> findByAddresses(String address);
}
