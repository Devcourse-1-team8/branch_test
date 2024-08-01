import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int idCnt = 0;
    static int result = 0;
    static boolean flag = true;
    public static void main(String[] args) throws IOException, SQLException {

        UserManager manager = new UserManager();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        while(flag){
            view();
            int menu = Integer.parseInt(br.readLine());
            switch (menu) {
                case 1:
                    System.out.println("User 추가");
                    // User 추가 로직
                    //추가할 데이터 입력
                    int id = ++idCnt;
                    System.out.println("idCnt = " + idCnt);
                    System.out.print("이름 입력: ");
                    String name = br.readLine();

                    System.out.print("나이 입력: ");
                    int age = Integer.parseInt(br.readLine());

                    System.out.print("전화번호 입력: ");
                    String phone = br.readLine();

                    System.out.print("주소 입력: ");
                    String address = br.readLine();

                    System.out.print("성별 입력: ");
                    String gender = br.readLine();

                    result =
                            manager.addUser(id,new UserDTO(name, age, phone, address, gender));
                    if(result == 1)
                        System.out.println("User 등록이 완료되었습니다");
                    else
                        System.out.println("User 등록에 실패하였습니다");
                    break;
                case 2:
                    System.out.println("User 전부 조회");
                    // User 전부 조회 로직
                    //그냥 조회
                    for(UserDTO u : manager.findAll()){
                        System.out.println(u);
                    }

                    break;
                case 3:
                    System.out.println("이름으로 조회");
                    // 이름으로 조회 로직
                    //찾을 이름 입력
                    System.out.print("조회할 이름: ");
                    String findName = sc.nextLine();

                    for(UserDTO u : manager.findByName(findName)){
                        System.out.println(u);
                    }
                    break;
                case 4:
                    System.out.println("주소로 검색");
                    // 주소로 검색 로직
                    //찾을 주소 입력
                    System.out.print("조회할 주소: ");
                    String findAddress = sc.nextLine();
                    for(UserDTO u : manager.findByAddresses(findAddress)){
                        System.out.println(u);
                    }
                    break;
                case 5:
                    System.out.println("User 수정");
                    //id 받는걸로
                    System.out.print("수정할 user이름:");
                    String updateUser = sc.nextLine();

                    System.out.println("1. 전화번호");
                    System.out.println("2. 주소");

                    int cmd = sc.nextInt();
                    // User 수정 로직
                    //수정할 값 입력
                    System.out.print("변경할 값을 입력하세요:");
                    String tmpStr = br.readLine();

                    result = manager.updateUser(updateUser, cmd, tmpStr);
                    if(result == 1)
                        System.out.println("User 수정이 완료되었습니다");
                    else
                        System.out.println("User 수정에 실패하였습니다");
                    break;
                case 6:
                    System.out.println("User 삭제");
                    // User 삭제 로직 -->name
                    System.out.print("삭제할 user이름:");
                    String deleteUser = sc.nextLine();
                    //삭제할 값 입력
                    result = manager.deleteUser(deleteUser);
                    if(result == 1)
                        System.out.println("User 삭제이 완료되었습니다");
                    else
                        System.out.println("User 삭제에 실패하였습니다");
                    break;
                case 7:
                    System.out.println("종료");
                    // 종료 로직
                    flag = false;
                    break;
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
        System.out.print("메뉴 입력 -> ");
    }
}
