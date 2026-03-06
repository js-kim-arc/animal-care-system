package domain.animal.species;

import domain.animal.Animal;
import domain.animal.group.Mammal;

public class Dog extends Mammal {

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String getType() {
        return "강아지";
    }

    @Override
    public String makeSound() {
        return "멍멍!";
    }
}
