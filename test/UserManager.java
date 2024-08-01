package Day0731.NewTask;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements Manager {
    Connection conn = null;
    String SQL = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public UserDTO addUser(UserDTO user) throws SQLException {
        try{
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/workshop2", "root", "1234");

            SQL = "insert into users(name, age, phoneNumber, address, gender) value(?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getAge());
            pstmt.setString(3, user.getPhoneNumber());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getGender());

            int result = pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return user;
    }

    @Override
    public UserDTO updateUser(int userId, UserDTO user) throws SQLException {
        try{
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/workshop2", "root", "1234");

            SQL = "update users set name = ?, age = ?, phoneNumber = ?, address = ?, gender = ? where userId = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getAge());
            pstmt.setString(3, user.getPhoneNumber());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getGender());
            pstmt.setInt(6, userId);


            int result = pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return user;
    }

    @Override
    public void deleteUser(int userId) throws SQLException {
        try{
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/workshop2", "root", "1234");

            SQL = "delete from users where userId = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, userId);

            int result = pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public List<UserDTO> findAll() throws SQLException {
        List<UserDTO> users = new ArrayList<>();

        try{
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/workshop2", "root", "1234");
            SQL = "select * from users";
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString(2);
                int age = rs.getInt(3);
                String phoneNumber = rs.getString(4);
                String address = rs.getString(5);
                String gender = rs.getString(6);
                //System.out.println(name+age+phoneNumber+address+gender);
                UserDTO user = UserDTO.setUser(name, age, phoneNumber, address, gender);
                users.add(user);
            }

        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return users;
    }

    @Override
    public List<UserDTO> findByName(String name) throws SQLException {
        List<UserDTO> users = new ArrayList<>();

        try{
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/workshop2", "root", "1234");
            SQL = "select * from users where name = " + name;
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                name = rs.getString(2);
                int age = rs.getInt(3);
                String phoneNumber = rs.getString(4);
                String address = rs.getString(5);
                String gender = rs.getString(6);

                users.add(UserDTO.setUser(name, age, phoneNumber, address, gender));
            }

        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return users;
    }

    @Override
    public List<UserDTO> findByAddresses(String address) throws SQLException {
        List<UserDTO> users = new ArrayList<>();

        try{
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/workshop2", "root", "1234");
            SQL = "select * from users where address = " + address;
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString(2);
                int age = rs.getInt(3);
                String phoneNumber = rs.getString(4);
                address = rs.getString(5);
                String gender = rs.getString(6);

                users.add(UserDTO.setUser(name, age, phoneNumber, address, gender));
            }

        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return users;
    }
}
