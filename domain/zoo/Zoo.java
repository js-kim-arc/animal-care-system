package domain.zoo;

import domain.animal.Animal;

import java.util.ArrayList;

public class Zoo {

    private final ArrayList<Animal> animals = new ArrayList<>();


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

    // 코드 리팩토링 - (이유) 반복 행동
    public Animal findAnimalByIndex(int index) {
        if (index < 0 || index >= animals.size()) {
            throw new IllegalArgumentException("잘못된 동물 번호입니다.");
        }

        return animals.get(index);
    }

    // 동물 찾아서 밥주기
    public void feedAnimal(int index) {
        Animal animal = findAnimalByIndex(index);
        animal.feed();
    }

    public void playWithAnimal(int index) {
        Animal animal = findAnimalByIndex(index);
        animal.play();
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


}
