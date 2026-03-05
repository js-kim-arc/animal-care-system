package domain.animal;

public abstract class Animal {

    private final String name;
    private final int age;

    // 생성 시에 바로 검증
    public Animal(String name, int age) {
        validateName(name);
        validateAge(age);

        this.name = name;
        this.age = age;
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
}
