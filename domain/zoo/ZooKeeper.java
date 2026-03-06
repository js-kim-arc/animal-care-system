package domain.zoo;

import domain.animal.Animal;

public class ZooKeeper {
    private final String name;
    private final KeeperSpecialty specialty;

    public ZooKeeper(String name, KeeperSpecialty specialty) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("사육사 이름은 비어 있을 수 없습니다.");
        }
        if (specialty == null) {
            throw new IllegalArgumentException("사육사 전문 분야를 선택해야 합니다.");
        }

        this.name = name;
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public KeeperSpecialty getSpecialty() {
        return specialty;
    }

    public boolean isSpecializedFor(Animal animal) {
        if (animal == null) return false;

        switch (specialty) {
            case BIRD:
                return "조류".equals(animal.getGroup());
            case REPTILE:
                return "파충류".equals(animal.getGroup());
            case LARGE_ANIMAL:
                // 간단 규칙: 사자/코끼리를 대형동물로 취급
                return "사자".equals(animal.getType()) || "코끼리".equals(animal.getType());
            default:
                return false;
        }
    }
}