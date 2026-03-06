package domain.animal.species;

import domain.animal.group.Mammal;

public class Lion extends Mammal {

    public Lion(String name, int age) {
        super(name, age);
    }

    @Override
    public String getType() {
        return "사자";
    }

    @Override
    public String makeSound() {
        return "어흥!";
    }
}
