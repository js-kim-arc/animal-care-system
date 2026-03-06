package domain.animal;

import domain.zoo.Food;

import java.util.Set;

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


    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("동물 이름은 비어 있을 수 없습니다.");
        }
    }
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
    public int getHunger() {return hunger;}
    public int getHappiness() {
        return happiness;
    }

    public abstract String getType();
    public abstract String getGroup();
    public abstract String makeSound();


    public String summary() {
        return name + " (" + getType() + ", " + age + "살)";
    }

    // 허용 먹이 목록 - 먹이 제외 컷
    public abstract Set<Food> getAllowedFoods();

    public boolean canEat(Food food) {
        return getAllowedFoods().contains(food);
    }

    // 기존 기본 먹이주기 (기본 감소량 10)
    public void feed(Food food) {
        feed(food, 10);
    }

    // 사육사 효과 반영 먹이주기
    public void feed(Food food, int hungerDecrease) {
        if (food == null) throw new IllegalArgumentException("먹이를 선택해야 합니다.");
        if (hungerDecrease <= 0) throw new IllegalArgumentException("감소량은 1 이상이어야 합니다.");

        if (!canEat(food)) {
            System.out.println("경고: " + name + "에게 '" + food.getDisplayName()
                    + "'는(은) 부적절한 먹이입니다. (배고픔 감소 없음)");
            return;
        }

        hunger -= hungerDecrease;
        if (hunger < 0) hunger = 0;

        System.out.println(name + "에게 '" + food.getDisplayName()
                + "'를(을) 주었습니다. 현재 배고픔 수치: " + hunger);
    }

    // 놀아주면 +10 규칙 - max 100
    public void play() {
        play(10);
    }

    public void play(int happinessIncrease) {
        if (happinessIncrease <= 0) throw new IllegalArgumentException("증가량은 1 이상이어야 합니다.");

        happiness += happinessIncrease;
        if (happiness > 100) happiness = 100;

        System.out.println(name + "와(과) 놀아주었습니다. 현재 행복도: " + happiness);
    }

    // 상태 확인용
    public String status() {
        return "이름: " + name + "\n"
                + "종류: " + getType() + "\n"
                + "나이: " + age + "살\n"
                + "배고픔 수치: " + hunger + "\n"
                + "행복도: " + happiness;
    }
}
