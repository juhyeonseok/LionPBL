package obj1;

import java.util.Scanner;

public class step2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== (Step 2) 객체 내부에서 유효성 검증 ===");

        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine();

        System.out.print("전공을 입력하세요: ");
        String major = sc.nextLine();

        System.out.print("기수를 입력하세요: ");
        int generation = sc.nextInt();

        // [변화] 일단 객체를 생성합니다.
        Lion lion = new Lion(name, major, generation);

        // [책임 위임] main은 더 이상 구체적인 규칙(empty 확인 등)을 알 필요가 없습니다.
        // 단지 Lion 객체에게 "너 유효하니?"라고 물어볼 뿐입니다.
        System.out.println("\n[시스템] 객체 내부 상태 검증을 요청합니다...");
        
        if (lion.isValid()) {
            System.out.println("[시스템] 검증 통과: Lion 객체가 올바른 상태입니다.");
        } else {
            System.out.println("[오류] 객체 내부 검증 결과, 유효하지 않은 데이터가 포함되어 있습니다.");
        }
    }
}
