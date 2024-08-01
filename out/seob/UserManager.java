package seob;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements Manager{



    private static final String URL = "jdbc:mysql://127.0.0.1:3306/prac";
    private static final String ID = "root";
    private static final String PW = "1038";

    private Connection conn;

    String SQL;
    PreparedStatement pstmt;
    ResultSet rs;

    public UserManager() throws SQLException {
        conn = DriverManager.getConnection(URL, ID, PW);
    }


    @Override
    public UserDTO addUser(UserDTO userDTO) {


        try{
            SQL = "insert into Users (name,age,phoneNumber,address,gender) VALUES (?,?,?,?,?) ";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,userDTO.getName());
            pstmt.setInt(2, userDTO.getAge());
            pstmt.setString(3, userDTO.getPhoneNumber());
            pstmt.setString(4, userDTO.getAddress());
            pstmt.setString(5, userDTO.getGender());
            pstmt.executeUpdate();







        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return userDTO;

    }

    @Override
    public UserDTO updateUser(int userId, UserDTO userDTO) {
        SQL = "update Users set name = ?, age = ?, phoneNumber = ?, address = ?, gender = ? WHERE userId = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userDTO.getName());
            pstmt.setInt(2, userDTO.getAge());
            pstmt.setString(3, userDTO.getPhoneNumber());
            pstmt.setString(4, userDTO.getAddress());
            pstmt.setString(5, userDTO.getGender());
            pstmt.setInt(6,userId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return userDTO;


    }


    @Override
    public boolean deleteUser(int userId) {


        SQL = "delete from Users where userId = ?";
        try{
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1,userId);
            int result = pstmt.executeUpdate();
            return result > 0; // 실행된 값이 1 이상이면 true 아니면 false로 취급
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> users = new ArrayList<>();
        SQL = "select * from Users";
        try{

            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("gender")
                );
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch (SQLException e){
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
        }
        return users;
    }

    @Override
    public List<UserDTO> findByName(String name) {
        List<UserDTO> users = new ArrayList<>();
        SQL = "select * from Users where name = ?";

        try{
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            while(rs.next()){
                UserDTO user = new UserDTO(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("gender")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch (SQLException e){
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
        }

        return users;

    }

    @Override
    public List<UserDTO> findByAddress(String address) {
        List<UserDTO> users = new ArrayList<>();
        SQL = "select * from Users where address = ?";

        try{
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, address);
            rs = pstmt.executeQuery();

            while(rs.next()){
                UserDTO user = new UserDTO(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("gender")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch (SQLException e){
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
        }

        return users;
    }

    public void close(){
        if(conn != null){
            try{
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
