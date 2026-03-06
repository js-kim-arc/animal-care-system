package domain.animal;

public abstract class Animal {

    private final String name;
    private final int age;
    // 디폴트 먹이값: 50으로 산정
    // 추가 규칙에 대해서 생각 필요
    private int hunger;
    private int happiness;

    // 생성 시에 바로 검증

    public Animal(String name, int age) {
        validateName(name);
        validateAge(age);

        this.name = name;
        this.age = age;
        this.hunger = 50;
        this.happiness = 50;
    }

    //기본 검증 로직 - 이름
    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("동물 이름은 비어 있을 수 없습니다.");
        }
    }
    //기본 검증 로직 - 나이
    private void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("동물 나이는 0 이상이어야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract String getType();

    public int getHappiness() {
        return happiness;
    }

    public String summary() {
        return name + " (" + getType() + ", " + age + "살)";
    }

    public void feed() {
        hunger -= 10;
        if (hunger < 0) {
            hunger = 0;
        }

        System.out.println(name + "에게 먹이를 주었습니다. 현재 배고픔 수치: " + hunger);
    }

    // 놀아주면 +10 규칙 - max 100
    public void play() {
        happiness += 10;
        if (happiness > 100) {
            happiness = 100;
        }

        System.out.println(name + "와(과) 놀아주었습니다. 현재 행복도: " + happiness);
    }

    // Story 5: 상태 확인용
    public String status() {
        return "이름: " + name + "\n"
                + "종류: " + getType() + "\n"
                + "나이: " + age + "살\n"
                + "배고픔 수치: " + hunger + "\n"
                + "행복도: " + happiness;
    }

    // Story 5: 울음소리 (종류별 상이)
    public abstract String makeSound();

}
