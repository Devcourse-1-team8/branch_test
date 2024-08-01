package seob;

public class UserDTO {

    private String name;

    private int age;

    private String phoneNumber;

    private String address;

    private String gender;

    public UserDTO(String name, int age, String phoneNumber, String address, String gender) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
    }

    public UserDTO(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }







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
        return "이름: " + name + "\n" +
                "나이: " + age + "\n" +
                "전화번호: " + phoneNumber + "\n" +
                "주소: " + address + "\n" +
                "성별: " + gender;
    }
}
