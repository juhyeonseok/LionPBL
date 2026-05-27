package class5.package2;

import class5.role.Lion;
import class5.role.Member;
import class5.role.Staff;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemberRepository repository = null;

        while (repository == null) {
            System.out.println("🔧 저장소를 선택하세요:");
            System.out.println("1. MemoryMemberRepository (실제 저장)");
            System.out.println("2. MockMemberRepository (더미 데이터)");
            System.out.print("선택: ");
            
            String repoChoice = scanner.nextLine().trim();
            if (repoChoice.equals("1")) {
                repository = new MemoryMemberRepository();
            } else if (repoChoice.equals("2")) {
                repository = new MockMemberRepository();
            } else {
                System.out.println("❌ 잘못된 선택입니다. 다시 선택해 주세요.\n");
            }
        }

        MemberService service = new MemberService(repository);
        System.out.println();

        while (true) {
            System.out.println("🦁 ===== 멋사 멤버 관리 시스템 (Step 2: DI 적용) ===== 🦁");
            System.out.println("1. ➕ 멤버 등록");
            System.out.println("2. 📋 전체 멤버 조회");
            System.out.println("3. 🔍 이름으로 검색");
            System.out.println("4. 🚪 종료");
            System.out.print("선택: ");

            String menuChoice = scanner.nextLine().trim();

            if (menuChoice.equals("1")) {
                System.out.println("\n👤 역할 선택 (1: 아기사자, 2: 운영진):");
                String roleChoice = scanner.nextLine().trim();

                System.out.println("\n📝 정보 입력");
                System.out.print("이름: ");
                String name = scanner.nextLine();
                System.out.print("전공: ");
                String major = scanner.nextLine();
                System.out.print("기수: ");
                int generation;
                try {
                    generation = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("❌ 기수는 숫자로 입력해 주세요.");
                    continue;
                }
                System.out.print("파트: ");
                String part = scanner.nextLine();

                try {
                    if (roleChoice.equals("1")) {
                        System.out.print("학번: ");
                        String studentId = scanner.nextLine();
                        Member babyLion = new Lion(name, major, generation, part, studentId);
                        service.join(babyLion);
                    } else if (roleChoice.equals("2")) {
                        System.out.print("직책: ");
                        String roleTitle = scanner.nextLine();
                        Member staff = new Staff(name, major, generation, part, roleTitle);
                        service.join(staff);
                    } else {
                        System.out.println("❌ 잘못된 역할 선택입니다.");
                        continue;
                    }
                    System.out.println("\n✅ 등록 완료: " + name);
                } catch (IllegalArgumentException e) {
                    System.out.println("\n❌ 등록 실패: " + e.getMessage());
                }
                System.out.println();

            } else if (menuChoice.equals("2")) {
                List<Member> members = service.findAllMembers();
                System.out.println("\n📋 ===== 전체 멤버 조회 =====");
                if (members.isEmpty()) {
                    System.out.println("등록된 멤버가 없습니다.");
                } else {
                    for (int i = 0; i < members.size(); i++) {
                        System.out.printf("[%d] %s\n", i + 1, members.get(i).getSummaryString());
                    }
                }
                System.out.println();

            } else if (menuChoice.equals("3")) {
                System.out.print("\n🔍 검색할 이름: ");
                String searchName = scanner.nextLine();
                Member member = service.findMember(searchName);

                if (member == null) {
                    System.out.println("\n❌ 존재하지 않는 회원입니다.");
                } else {
                    System.out.println("\n🎯 ===== 검색 결과 =====");
                    System.out.println(member.getDetailString());
                }
                System.out.println();

            } else if (menuChoice.equals("4")) {
                System.out.println("\n🚪 프로그램을 종료합니다. 감사합니다!");
                break;
            } else {
                System.out.println("❌ 잘못된 번호 선택입니다. 다시 선택해 주세요.\n");
            }
        }
        scanner.close();
    }
}
