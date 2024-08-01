package team_workshop;

import team_workshop.core.Manager;
import team_workshop.core.UserManager;
import team_workshop.domain.UserDTO;
import team_workshop.infra.JdbcServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private final Manager manager;

    public Main(Manager manager) {
        this.manager = manager;
    }

    public static void main(String[] args) throws IOException {
        JdbcServices jdbcService = JdbcServices.getInstance();
        jdbcService.connectDatabase();
        Manager manager = new UserManager(jdbcService);
        Main app = new Main(manager);

        while (true){
            view();
            int menu = Integer.parseInt(br.readLine());
            switch (menu) {
                case 1:
                    System.out.println("User 추가");
                    app.addUser();
                    break;
                case 2:
                    System.out.println("User 전부 조회");
                    app.findAll();
                    break;
                case 3:
                    System.out.println("이름으로 조회");
                    app.findByName();
                    break;
                case 4:
                    System.out.println("주소로 검색");
                    app.findByAddress();
                    break;
                case 5:
                    System.out.println("User 수정");
                    app.updateUser();
                    break;
                case 6:
                    System.out.println("User 삭제");
                    app.deleteUser();
                    break;
                case 7:
                    System.out.println("종료");
                    // 종료 로직
                    jdbcService.terminateConnection();
                    System.exit(0);
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

    public void addUser() throws IOException {
        System.out.print("회원명 >> ");
        String name = br.readLine();
        System.out.print("나이 >> ");
        int age = Integer.parseInt(br.readLine());
        System.out.print("연락처 >> ");
        String phone = br.readLine();
        System.out.print("주소 >> ");
        String address = br.readLine();
        System.out.print("성별 >> ");
        String gender = br.readLine();

        try {
            UserDTO newUser = new UserDTO(name, age, phone, address, gender);
            manager.addUser(newUser);
            System.out.println("새로운 회원 추가되었습니다.");
            System.out.println(newUser);
        } catch (RuntimeException e) {
            System.out.println("회원 추가에 실패했습니다.");
        }
    }

    public void updateUser() throws IOException {
        System.out.print("수정할 회원 번호 >> ");
        int userId = Integer.parseInt(br.readLine());
        System.out.print("회원명(수정 안 하면 패스) >> ");
        String name = br.readLine();
        System.out.print("나이(수정 안 하면 -1 입력) >> ");
        int age = Integer.parseInt(br.readLine());
        System.out.print("연락처(수정 안 하면 패스) >> ");
        String phone = br.readLine();
        System.out.print("주소(수정 안 하면 패스) >> ");
        String address = br.readLine();
        System.out.print("성별(수정 안 하면 패스) >> ");
        String gender = br.readLine();

        try {
            UserDTO newUser = new UserDTO(name, age, phone, address, gender);
            manager.updateUser(userId, newUser);
            System.out.println("회원 정보 수정되었습니다.");
        } catch (RuntimeException e) {
            System.out.println("회원 수정에 실패했습니다.");
        }
    }

    public void deleteUser() throws IOException {
        System.out.print("삭제할 회원 번호 >> ");
        int userId = Integer.parseInt(br.readLine());
        try {
            manager.deleteUser(userId);
            System.out.println("성공적으로 삭제 되었습니다.");
        } catch (RuntimeException e) {
            System.out.println("회원 삭제에 실패했습니다.");
        }
    }

    public void findAll() {
        System.out.println("------ 전체 회원 목록 ------");
        try {
            List<UserDTO> result = manager.findAll();
            for (UserDTO user : result) {
                System.out.println(user);
            }
        } catch (RuntimeException e) {
            System.out.println("전체 회원 조회에 실패했습니다.");
        }
    }

    public void findByName() throws IOException {
        System.out.print("검색할 회원명 >> ");
        String name = br.readLine();

        try {
            System.out.println("------ 검색된 회원 목록 ------");
            List<UserDTO> result = manager.findByName(name);
            for (UserDTO user : result) {
                System.out.println(user);
            }
        } catch (RuntimeException e) {
            System.out.println("회원 검색에 실패했습니다.");
        }
    }

    public void findByAddress() throws IOException {
        System.out.println("검색할 회원 주소 >> ");
        String address = br.readLine();

        try {
            System.out.println("------ 검색된 회원 목록 ------");
            List<UserDTO> result = manager.findByAddresses(address);
            for (UserDTO user : result) {
                System.out.println(user);
            }
        } catch (RuntimeException e) {
            System.out.println("회원 검색에 실패했습니다.");
        }
    }
}
