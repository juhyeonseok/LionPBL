package obj1;

import java.util.Scanner;

public class step1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== (Step 1) Main 메서드에서 유효성 검증 ===");

        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine();

        System.out.print("전공을 입력하세요: ");
        String major = sc.nextLine();

        System.out.print("기수를 입력하세요: ");
        int generation = sc.nextInt();

        // 1. main 메서드 내부에서 입력값에 대한 유효성 검증 수행
        System.out.println("\n[시스템] 입력값 검증을 시작합니다...");

        if (name.isEmpty() || major.isEmpty() || generation < 1) {
            // 2. 유효하지 않은 경우, 오류 메시지 출력 및 객체 생성 안 함
            System.out.println("[오류] 입력값이 유효하지 않습니다.");
            System.out.println("-> 이름과 전공은 필수이며, 기수는 1 이상이어야 합니다.");
            System.out.println("[시스템] Lion 객체를 생성하지 않고 프로그램을 종료합니다.");
        } else {
            // 3. 모든 검증을 통과한 경우에만 Lion 객체 생성
            Lion lion = new Lion(name, major, generation);
            System.out.println("[시스템] 모든 검증을 통과하여 Lion 객체가 성공적으로 생성되었습니다.");
        }
    }
}
