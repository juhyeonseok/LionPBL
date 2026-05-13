package obj1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== 아기사자 정보 입력 ===");

        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine();

        System.out.print("전공을 입력하세요: ");
        String major = sc.nextLine();

        System.out.print("기수를 입력하세요: ");
        int generation = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기 (습관적인 클린 코드 작성)

        // 1. 객체 생성 및 메서드 호출 (Main의 역할)
        Lion lion = new Lion(name, major, generation);

        // 2. 프로그램 실행 흐름 제어 (Main의 역할)
        if (lion.isValid()) {
            System.out.println("\n[시스템] 검증에 성공하였습니다.");
            lion.printInfo();
        } else {
            System.out.println("\n[오류] 입력된 값이 유효하지 않아 정보를 출력할 수 없습니다.");
            System.out.println("(이름과 전공은 필수이며, 기수는 1 이상이어야 합니다.)");
        }
    }
}
