package Day0731.NewTask;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    String name;
    int age;
    String phoneNumber;
    String address;
    String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public static UserDTO setUser(String name, int age, String phoneNumber, String address, String gender) {
        UserDTO user = new UserDTO();

        user.setName(name);
        user.setAge(age);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setGender(gender);

        return user;
    }
}
