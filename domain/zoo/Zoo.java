package domain.zoo;

import domain.animal.Animal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Zoo {

    private final ArrayList<Animal> animals = new ArrayList<>();
    private final ArrayList<ZooKeeper> keepers = new ArrayList<>();

    public Zoo() {
        // ✅ "이미 있는 사육사 체계" 느낌: 기본 사육사 몇 명을 미리 넣어둔다 (원하면 삭제 가능)
        keepers.add(new ZooKeeper("조류 전문가 김씨", KeeperSpecialty.BIRD));
        keepers.add(new ZooKeeper("대형동물 전문가 박씨", KeeperSpecialty.LARGE_ANIMAL));
        keepers.add(new ZooKeeper("파충류 전문가 이씨", KeeperSpecialty.REPTILE));
    }

    public void addAnimal(Animal animal) {
        validateAnimal(animal);
        animals.add(animal);

        System.out.println(
                animal.getName() + "(" + animal.getType() + ", " + animal.getAge() + "살)가 등록되었습니다."
                          );
    }

    // 동물 리스트 검증
    private void validateAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("등록할 동물이 존재하지 않습니다.");
        }
    }

    public boolean isEmpty() {
        return animals.isEmpty();
    }

    // 동물 보여주기
    // 코드 리팩토링 - Animal 이름 말하기 자동화,
    public void showAnimals() {
        if (animals.isEmpty()) {
            System.out.println("등록된 동물이 없습니다.");
            return;
        }

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            System.out.println((i + 1) + ". " + animal.summary());
        }
    }

    // 사육사 curd
    public boolean hasKeepers() {
        return !keepers.isEmpty();
    }

    public void addKeeper(ZooKeeper keeper) {
        if (keeper == null) throw new IllegalArgumentException("사육사가 존재하지 않습니다.");
        keepers.add(keeper);
        System.out.println("사육사가 등록되었습니다: " + keeper.getName() + " (" + keeper.getSpecialty().getDisplayName() + ")");
    }

    public void showKeepers() {
        if (keepers.isEmpty()) {
            System.out.println("등록된 사육사가 없습니다.");
            return;
        }
        for (int i = 0; i < keepers.size(); i++) {
            ZooKeeper k = keepers.get(i);
            System.out.println((i + 1) + ". " + k.getName() + " (" + k.getSpecialty().getDisplayName() + ")");
        }
    }

    public ZooKeeper getKeeper(int index) {
        if (index < 0 || index >= keepers.size()) {
            throw new IllegalArgumentException("잘못된 사육사 번호입니다.");
        }
        return keepers.get(index);
    }

    // 코드 리팩토링 - (이유) 반복 행동
    public Animal findAnimalByIndex(int index) {
        if (index < 0 || index >= animals.size()) {
            throw new IllegalArgumentException("잘못된 동물 번호입니다.");
        }

        return animals.get(index);
    }

    // 사육사 - 특별효과
    public void feedAnimal(int animalIndex, Food food, ZooKeeper keeper) {
        Animal animal = findAnimalByIndex(animalIndex);

        int base = 10;
        int bonus = 10; // 전문 분야면 +10
        int decrease = (keeper != null && keeper.isSpecializedFor(animal)) ? base + bonus : base;

        if (keeper != null) {
            System.out.println("[사육사] " + keeper.getName() + " (" + keeper.getSpecialty().getDisplayName() + ")");
        } else {
            System.out.println("[사육사] (선택 없음) 기본 효과로 진행");
        }

        animal.feed(food, decrease);
    }

    public void playWithAnimal(int animalIndex, ZooKeeper keeper) {
        Animal animal = findAnimalByIndex(animalIndex);

        int base = 10;
        int bonus = 10;
        int increase = (keeper != null && keeper.isSpecializedFor(animal)) ? base + bonus : base;

        if (keeper != null) {
            System.out.println("[사육사] " + keeper.getName() + " (" + keeper.getSpecialty().getDisplayName() + ")");
        } else {
            System.out.println("[사육사] (선택 없음) 기본 효과로 진행");
        }

        animal.play(increase);
    }

    // 상태 확인
    public void showAnimalStatus(int index) {
        Animal animal = findAnimalByIndex(index);
        System.out.println(animal.status());
    }

    // 울음소리 듣기
    public void hearAnimalSound(int index) {
        Animal animal = findAnimalByIndex(index);
        System.out.println(animal.getName() + ": " + animal.makeSound());
    }

    public Animal getAnimal(int index) {
        if (index < 0 || index >= animals.size()) {
            throw new IllegalArgumentException("잘못된 동물 번호입니다.");
        }
        return animals.get(index);
    }

    // 통계 파트
    public Map<String, Integer> countByType() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (Animal a : animals) {
            counts.put(a.getType(), counts.getOrDefault(a.getType(), 0) + 1);
        }
        return counts;
    }

    public Map<String, Integer> countByGroup() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (Animal a : animals) {
            counts.put(a.getGroup(), counts.getOrDefault(a.getGroup(), 0) + 1);
        }
        return counts;
    }

    public double averageHappiness() {
        if (animals.isEmpty()) return 0.0;

        int sum = 0;

        for (Animal a : animals) sum += a.getHappiness();
        return (double) sum / animals.size();
    }

    public List<Animal> hungryAnimals(int threshold) {
        List<Animal> result = new ArrayList<>();
        for (Animal a : animals) {
            if (a.getHunger() <= threshold) result.add(a);
        }
        return result;
    }

    public void printSummary(int hungryThreshold) {
        System.out.println("=== 동물원 현황 요약 ===");
        System.out.println("- 총 동물 수: " + animals.size());

        Map<String, Integer> groupCounts = countByGroup();
        System.out.println("- 분류별 개체 수:");
        for (Map.Entry<String, Integer> e : groupCounts.entrySet()) {
            System.out.println("  - " + e.getKey() + ": " + e.getValue());
        }

        System.out.println("- 종류별 개체 수:");
        Map<String, Integer> typeCounts = countByType();
        for (Map.Entry<String, Integer> e : typeCounts.entrySet()) {
            System.out.println("  - " + e.getKey() + ": " + e.getValue());
        }

        System.out.printf("- 평균 행복도: %.2f%n", averageHappiness());

        List<Animal> hungry = hungryAnimals(hungryThreshold);
        System.out.println("- 배고픈 동물 수(기준치 " + hungryThreshold + " 이하): " + hungry.size());
        if (!hungry.isEmpty()) {
            System.out.println("- 배고픈 동물 목록:");
            for (Animal a : hungry) {
                System.out.println("  - " + a.summary() + " | 배고픔: " + a.getHunger());
            }
        }
    }
}
