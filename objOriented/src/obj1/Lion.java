package obj1;
public class Lion {
    public String name;          // 어디서든 접근 가능한 public
    String major;               // 같은 패키지 내에서만 접근 가능한 default
    private int generation;     // 클래스 내부에서만 접근 가능한 private

    // 세 필드를 모두 초기화하는 생성자
    public Lion(String name, String major, int generation) {
        this.name = name;
        this.major = major;
        this.generation = generation;
    }

    // [책임 이동] 객체 스스로가 자신의 상태가 유효한지 검증하는 메서드
    public boolean isValid() {
        if (name == null || name.isEmpty() || 
            major == null || major.isEmpty() || 
            generation < 1) {
            return false;
        }
        return true;
    }

    // [책임] 자신의 상태를 정해진 형식으로 출력하는 로직 담당
    public void printInfo() {
        System.out.println("\n--- 생성된 아기사자 정보 ---");
        System.out.println("이름: " + name);
        System.out.println("전공: " + major);
        System.out.println("기수: " + generation + "기");
        System.out.println("--------------------------");
    }
}
