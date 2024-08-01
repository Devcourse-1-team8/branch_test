package seob;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class Main {

    private static UserManager userManager;
    private static BufferedReader br;

    public static void main(String[] args) throws IOException, SQLException {
        userManager = new UserManager();
        br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            view();
            int menu = Integer.parseInt(br.readLine());

            switch (menu) {
                case 1:
                    System.out.println("User 추가");
                    // User 추가 로직
                    addUser();

                    break;
                case 2:
                    System.out.println("User 전부 조회");
                    // User 전부 조회 로직
                    findAllUsers();
                    break;
                case 3:
                    System.out.println("이름으로 조회");
                    // 이름으로 조회 로직
                    findByName();
                    break;
                case 4:
                    System.out.println("주소로 검색");
                    // 주소로 검색 로직
                    findByAddress();
                    break;
                case 5:
                    System.out.println("User 수정");
                    // User 수정 로직
                    updateUser();
                    break;
                case 6:
                    System.out.println("User 삭제");
                    // User 삭제 로직
                    deleteUser();
                    break;
                case 7:
                    System.out.println("종료");
                    // 종료 로직
                    userManager.close();
                    System.exit(0);
                    //프로그램 종료
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
                    break;
            }
        }
    }


    public static void view(){
        /*
    1번 User 추가
    2번 User 전부 조회
    3번 이름으로 조회
    4번 주소로 검색
    5번 User 수정
    6번 User 삭제
    7번 종료
    */
        System.out.println("------------------------------");
        System.out.println("1번 User 추가");
        System.out.println("2번 User 전부 조회");
        System.out.println("3번 이름으로 조회");
        System.out.println("4번 주소로 검색");
        System.out.println("5번 User 수정");
        System.out.println("6번 User 삭제");
        System.out.println("7번 종료");
        System.out.println("------------------------------");
        System.out.println("메뉴 입력 ->");
    }

    private static void addUser() throws IOException, SQLException {
        System.out.print("이름: ");
        String name = br.readLine();
        System.out.print("나이: ");
        int age = Integer.parseInt(br.readLine());
        System.out.print("전화번호: ");
        String phoneNumber = br.readLine();
        System.out.print("주소: ");
        String address = br.readLine();
        System.out.print("성별: ");
        String gender = br.readLine();

        UserDTO user = new UserDTO(name, age, phoneNumber, address, gender);
        userManager.addUser(user);
        System.out.println("--- User 추가 완료 --- ");
        System.out.println(user);
    }

    private static void updateUser() throws IOException, SQLException {
        System.out.print("수정할 UserId: ");
        int userId =Integer.parseInt(br.readLine());
        System.out.print("이름: ");
        String name = br.readLine();
        System.out.print("나이: ");
        int age = Integer.parseInt(br.readLine());
        System.out.print("전화번호: ");
        String phoneNumber = br.readLine();
        System.out.print("주소: ");
        String address = br.readLine();
        System.out.print("성별: ");
        String gender = br.readLine();

        UserDTO user = new UserDTO(name, age, phoneNumber, address, gender);
        userManager.updateUser(userId, user);
        System.out.println("User 수정 완료: " + user);
    }

    private static void deleteUser() throws IOException, SQLException {
        System.out.print("삭제할 User ID: ");
        int userId = Integer.parseInt(br.readLine());
        boolean deletedUser = userManager.deleteUser(userId);
        if (deletedUser) {
            System.out.println("User 삭제 완료");
        } else {
            System.out.println("User 삭제 실패..");
        }
    }

    private static void findAllUsers() throws SQLException {
        List<UserDTO> users = userManager.findAll();
        for (UserDTO user : users) {
            System.out.println(user);
            System.out.println("-----------------------");
        }
    }

    private static void findByName() throws IOException, SQLException {
        System.out.print("이름: ");
        String name = br.readLine();
        List<UserDTO> users = userManager.findByName(name);
        for (UserDTO user : users) {
            System.out.println(user);
        }
    }

    private static void findByAddress() throws IOException, SQLException {
        System.out.print("주소: ");
        String address = br.readLine();
        List<UserDTO> users = userManager.findByAddress(address);
        for (UserDTO user : users) {
            System.out.println(user);
        }
    }



}
