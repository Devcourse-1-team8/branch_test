package Day0731.NewTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        UserManager manager = new UserManager();
        UserDTO user = new UserDTO();
        List<UserDTO> result = new ArrayList<>();

        int userId;
        String address;
        String name;

        while(true){
            view();
            int menu = Integer.parseInt(br.readLine());
            switch (menu) {
                case 1:
                    //User 추가
                    user = inputInfo(br);
                    manager.addUser(user);
                    System.out.println(user);
                    System.out.println("데이터를 성공적으로 추가했습니다");
                    // User 추가 로직
                    break;
                case 2:
                    System.out.println("User 전부 조회");
                    result = manager.findAll();
                    for (UserDTO userDTO : result) {
                        System.out.println(userDTO);
                    }
                    System.out.println("모든 데이터를 성공적으로 조회했습니다.");
                    // User 전부 조회 로직
                    break;
                case 3:
                    System.out.println("이름으로 조회");
                    // 이름으로 조회 로직
                    System.out.println("조회하고 싶은 이름을 입력해주세요. ");
                    name = br.readLine();
                    result = manager.findByName(name);
                    for (UserDTO userDTO : result) {
                        System.out.println(userDTO);
                    }
                    System.out.println("검색을 성공적으로 마졌습니다.");
                    break;
                case 4:
                    //주소로 검색
                    System.out.println("조회하고 싶은 주소를 입력해주세요. ");
                    address = br.readLine();
                    result = manager.findByAddresses(address);
                    for (UserDTO userDTO : result) {
                        System.out.println(userDTO);
                    }
                    System.out.println("검색을 성공적으로 마졌습니다.");
                    break;
                case 5:
                    //User 수정
                    System.out.println("수정하고 싶은 행의 userId를 입력해주세요. ");
                    userId = Integer.parseInt(br.readLine());
                    System.out.println("변경하고 싶은 사용자의 모든 인적사항을 기재해주세요. ");
                    user = inputInfo(br);
                    manager.updateUser(userId, user);
                    System.out.println(user);
                    System.out.println("데이터를 성공적으로 수정했습니다");
                    break;
                case 6:
                    //User 삭제
                    System.out.println("삭제하고 싶은 행의 userId를 입력해주세요. ");
                    userId = Integer.parseInt(br.readLine());
                    manager.deleteUser(userId);
                    System.out.println("데이터를 성공적으로 삭제했습니다.");
                    break;
                case 7:
                    System.out.println("종료");
                    return;
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
        System.out.println("------------------------------------");
        System.out.println("1번 User 추가");
        System.out.println("2번 User 전부 조회");
        System.out.println("3번 이름으로 조회");
        System.out.println("4번 주소로 검색");
        System.out.println("5번 User 수정");
        System.out.println("6번 User 삭제");
        System.out.println("7번 종료");
        System.out.println("------------------------------------");
        System.out.println("메뉴 입력 ->");
    }

    private static UserDTO inputInfo(BufferedReader br) throws IOException {
        System.out.print("이름 : ");
        String name = br.readLine();
        System.out.print("나이 : ");
        int age = Integer.parseInt(br.readLine());
        System.out.print("전화번호 : ");
        String phoneNumber = br.readLine();
        System.out.print("주소 : ");
        String address = br.readLine();
        System.out.print("성별 : ");
        String gender = br.readLine();

        return UserDTO.setUser(name, age, phoneNumber, address, gender);
    }
}
