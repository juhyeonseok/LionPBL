package class4.package1;
import java.util.Scanner;
import java.util.ArrayList;
import class4.role.Lion;
import class4.role.Member;
import class4.role.Staff;

public class Main {
    public static void main(String[] args) {
        Scanner choice = new Scanner(System.in);
        ArrayList<Member> members = new ArrayList<>();

        while (true) {
            System.out.println("=== 멤버 관리 시스템 ==="); 
            System.out.println("1. 멤버 등록"); 
            System.out.println("2. 전체 멤버 조회"); 
            System.out.println("3. 이름으로 검색"); 
            System.out.println("4. 종료"); 
            System.out.print("선택: ");
            int num;
            try {
                num = Integer.parseInt(choice.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("잘못입력하셨습니다 다시 입력해주세요.");
                continue;
            }
            
            if (num == 1) {
                System.out.print("역할 선택 (1: 아기사자, 2: 운영진): ");
                int rolenum;
                try {
                    rolenum = Integer.parseInt(choice.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("잘못입력하셨습니다 다시 입력해주세요.");
                    continue;
                }
                if (rolenum == 1) {
                    System.out.println("아기사자 역할이 선택되었습니다.");
                    System.out.print("이름: "); String lName = choice.nextLine();
                    if (isDuplicateName(members, lName)) {
                        System.out.println("이미 등록된 이름입니다. 메인 메뉴로 돌아갑니다.");
                        continue;
                    }
                    System.out.print("전공: "); String lMajor = choice.nextLine();
                    System.out.print("기수: "); String lGen = choice.nextLine();
                    int lGenInt;
                    try { 
                        lGenInt = Integer.parseInt(lGen); // 기수가 숫자로만 이루어져 있는지 검증
                    } catch (NumberFormatException e) {
                        System.out.println("잘못입력하셨습니다 다시 입력해주세요.");
                        continue;
                    }
                    System.out.print("파트(백엔드/프론트엔드/기획/디자인): "); String lPart = choice.nextLine();
                    System.out.print("학번: "); String lId = choice.nextLine();
                    try {
                        Long.parseLong(lId); // 학번이 숫자로만 이루어져 있는지 검증
                    } catch (NumberFormatException e) {
                        System.out.println("잘못입력하셨습니다 다시 입력해주세요.");
                        continue;
                    }
                    members.add(new Lion(lName, lMajor, lGenInt, lPart, lId));
                    System.out.println("멤버 등록이 완료되었습니다.");
                } else if (rolenum == 2) {
                    System.out.println("운영진 역할이 선택되었습니다.");
                    System.out.print("이름: "); String sName = choice.nextLine();
                    if (isDuplicateName(members, sName)) {
                        System.out.println("이미 등록된 이름입니다. 메인 메뉴로 돌아갑니다.");
                        continue;
                    }
                    System.out.print("전공: "); String sMajor = choice.nextLine();
                    System.out.print("기수: "); String sGen = choice.nextLine();
                    int sGenInt;
                    try {
                        sGenInt = Integer.parseInt(sGen);
                    } catch (NumberFormatException e) {
                        System.out.println("잘못입력하셨습니다 다시 입력해주세요.");
                        continue;
                    }
                    System.out.print("파트(백엔드/프론트엔드/기획/디자인): "); String sPart = choice.nextLine();
                    System.out.print("학번: "); String sId = choice.nextLine();
                    try {
                        Long.parseLong(sId); // 학번이 숫자로만 이루어져 있는지 검증
                    } catch (NumberFormatException e) {
                        System.out.println("잘못입력하셨습니다 다시 입력해주세요.");
                        continue;
                    }
                    members.add(new Staff(sName, sMajor, sGenInt, sPart, sId));
                    System.out.println("멤버 등록이 완료되었습니다.");
                } else {
                    System.out.println("잘못된 역할 번호입니다.");
                }
            } else if (num == 2) {
                System.out.println("=== 전체 멤버 조회 ===");
                if (members.isEmpty()) {
                    System.out.println("등록된 멤버가 없습니다.");
                } else {
                    for (Member m : members) {
                        m.printInfo();
                        System.out.println("총 " + members.size() + "명");
                    }
                }
            } else if (num == 3) {
                System.out.print("검색할 이름 입력: "); String searchName = choice.nextLine();
                boolean found = false;
                for (Member m : members) {
                    if (m.getName().equals(searchName)) {
                        m.printInfo();
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("해당 이름의 멤버가 없습니다.");
                }
            } else if (num == 4) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("메뉴에 없는 번호입니다. 다시 선택해주세요.");
            }
        }
    }

    // 리스트 내에 동일한 이름이 있는지 확인하는 헬퍼 메서드
    private static boolean isDuplicateName(ArrayList<Member> members, String name) {
        for (Member m : members) {
            if (m.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
