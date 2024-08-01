package team_workshop.infra;

import team_workshop.domain.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UserMapper {
    public static List<UserDTO> getCollection(ResultSet resultSet) {
         try {
            List<UserDTO> result = new ArrayList<>();
            while (resultSet.next()) {
                UserDTO user = parseFrom(resultSet);
                result.add(user);
            }

            return result;
        } catch (SQLException e) {
             throw new RuntimeException(e);
         }
    }

    public static UserDTO parseFrom(ResultSet resultSet) {
        try {
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String phone = resultSet.getString("phoneNumber");
            String address = resultSet.getString("address");
            String gender = resultSet.getString("gender");

            return new UserDTO(name, age, phone, address, gender);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
