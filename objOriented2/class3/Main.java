package class3;

import class3.role.Lion;
import class3.role.Member;
import class3.role.Staff;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Member> members = new ArrayList<>();

        // 1. 아기사자 정보 입력
        System.out.println("=== 아기사자 정보 입력 ===");
        System.out.print("이름: "); String lName = sc.nextLine();
        System.out.print("전공: "); String lMajor = sc.nextLine();
        System.out.print("기수: "); int lGen = sc.nextInt(); sc.nextLine();
        System.out.print("파트: "); String lPart = sc.nextLine();
        System.out.print("학번: "); String lId = sc.nextLine();
        members.add(new Lion(lName, lMajor, lGen, lPart, lId));

        System.out.println();

        // 2. 운영진 정보 입력
        System.out.println("=== 운영진 정보 입력 ===");
        System.out.print("이름: "); String sName = sc.nextLine();
        System.out.print("전공: "); String sMajor = sc.nextLine();
        System.out.print("기수: "); int sGen = sc.nextInt(); sc.nextLine();
        System.out.print("파트: "); String sPart = sc.nextLine();
        System.out.print("직책: "); String sPos = sc.nextLine();
        members.add(new Staff(sName, sMajor, sGen, sPart, sPos));

        // 3. 다형성을 활용한 정보 출력 및 판단
        // 조건문(if/else)이나 instanceof 없이 객체에게 스스로를 증명하도록 요청합니다.
        System.out.println("\n=== 처리 결과 ===");
        for (Member m : members) {
            m.printInfo();
            System.out.println();
        }
    }
}