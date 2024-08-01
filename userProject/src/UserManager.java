import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//여기서 데이터베이스
public class UserManager implements Manager {

    Connection conn = null;
    String sql = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //생성자
    public UserManager(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userinfo","root","5497");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<UserDTO> users = new ArrayList<>();
    }
    @Override
    public int addUser(int id, UserDTO user) throws SQLException {
        sql = "insert into Users(userid, name, age, phoneNumber, address, gender) values(?,?,?,?,?,?)";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(2, user.getName());
        pstmt.setInt(3, user.getAge());
        pstmt.setString(4, user.getPhoneNumber());
        pstmt.setString(5, user.getAddress());
        pstmt.setString(6, user.getGender());

        return pstmt.executeUpdate();
    }

    @Override
    public int updateUser(String name, int cmd, String tmpStr) throws SQLException {
        String attribute ="";
        switch (cmd){
            case 1:
                attribute = "phoneNumber";
                break;
            case 2:
                attribute = "address";
                break;
            default:
                break;
        }
        sql = "UPDATE Users SET " + attribute + " = ? WHERE name LIKE ?";
        pstmt = conn.prepareStatement(sql);
        // PreparedStatement에 name 매개변수를 설정

        pstmt.setString(1, tmpStr);
        pstmt.setString(2, "%" + name + "%");

        return pstmt.executeUpdate();
    }

    @Override
    public int deleteUser(String name) throws SQLException {
        sql = "delete from Users where name like ?";
        pstmt = conn.prepareStatement(sql);
        // PreparedStatement에 name 매개변수를 설정

        pstmt.setString(1, "%" + name + "%");

        return pstmt.executeUpdate();
    }

    @Override
    public List<UserDTO> findAll() throws SQLException {
        List<UserDTO> list = new ArrayList<>();

        sql = "select name, age, phoneNumber, address, gender from Users";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while(rs.next()){
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String phoneNumber = rs.getString("phoneNumber");
            String address = rs.getString("address");
            String gender = rs.getString("gender");
            UserDTO user = new UserDTO(name, age,phoneNumber,address,gender);
            list.add(user);
        }
        return list;
    }

    @Override
    public List<UserDTO> findByName(String name) throws SQLException {
        List<UserDTO> list = new ArrayList<>();

        sql = "select name, age, phoneNumber, address, gender from Users where name like ?";
        pstmt = conn.prepareStatement(sql);
        // PreparedStatement에 name 매개변수를 설정
        pstmt.setString(1, "%" + name + "%");
        rs = pstmt.executeQuery();

        while(rs.next()){
            String na = rs.getString("name");
            int age = rs.getInt("age");
            String phoneNumber = rs.getString("phoneNumber");
            String address = rs.getString("address");
            String gender = rs.getString("gender");
            UserDTO user = new UserDTO(na, age,phoneNumber,address,gender);
            list.add(user);
        }
        return list;
    }

    @Override
    public List<UserDTO> findByAddresses(String address) throws SQLException {
        List<UserDTO> list = new ArrayList<>();

        sql = "select name, age, phoneNumber, address, gender from Users where address like ?";
        pstmt = conn.prepareStatement(sql);
        // PreparedStatement에 name 매개변수를 설정
        pstmt.setString(1, "%" + address + "%");
        rs = pstmt.executeQuery();

        while(rs.next()){
            String na = rs.getString("name");
            int age = rs.getInt("age");
            String phoneNumber = rs.getString("phoneNumber");
            String addr = rs.getString("address");
            String gender = rs.getString("gender");
            UserDTO user = new UserDTO(na, age,phoneNumber,addr,gender);
            list.add(user);
        }
        return list;
    }
}
