package domain.animal.species;

import domain.animal.Animal;
import domain.animal.group.Mammal;

public class Cat extends Mammal {

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String getType() {
        return "고양이";
    }

    @Override
    public String makeSound() {
        return "야옹!";
    }
}
