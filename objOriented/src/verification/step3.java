package verification;

import obj1.Lion;

public class step3 {
    public static void main(String[] args) {
        // 1. Lion 객체 생성 (obj1 패키지의 클래스를 가져옴)
        Lion lion = new Lion("홍길동", "컴퓨터공학", 12);

        System.out.println("=== (Step 3) 접근 제어자 및 패키지 경계 확인 ===");

        // 2. 필드 접근 시도 (객체 생성 이후 직접 값 대입)

        // [결과: 성공] 
        // name은 public이므로 패키지가 달라도 어디서든 자유롭게 접근하여 수정할 수 있습니다.
        lion.name = "이순신";
        System.out.println("1. [public] name 변경 성공: " + lion.name);

        // [결과: 컴파일 에러] 
        // major는 접근 제어자를 명시하지 않은 'default'입니다. 
        // 같은 obj1 패키지가 아닌 verification 패키지에서는 보이지 않습니다.
        // 아래 주석을 해제하면 컴파일 에러가 발생합니다.
        // lion.major = "산업디자인"; 
        System.out.println("2. [default] major: 다른 패키지에서 접근 불가 (컴파일 에러 확인 가능)");

        // [결과: 컴파일 에러] 
        // generation은 'private'입니다. 
        // 패키지와 상관없이 Lion 클래스 내부가 아니면 절대 직접 접근할 수 없습니다.
        // 아래 주석을 해제하면 컴파일 에러가 발생합니다.
        // lion.generation = 13;
        System.out.println("3. [private] generation: 클래스 외부에서 접근 불가 (컴파일 에러 확인 가능)");

        System.out.println("\n[최종 확인] 접근 제어자에 따라 외부에서 수정 가능한 범위가 엄격히 제한됨을 확인했습니다.");
    }
}
