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
    public void showAnimals() {
        if (animals.isEmpty()) {
            System.out.println("등록된 동물이 없습니다.");
            return;
        }

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            System.out.println(
                    (i + 1) + ". " + animal.getName() + " (" + animal.getType() + ", " + animal.getAge() + "살)"
                              );
        }
    }
}
