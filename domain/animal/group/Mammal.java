package domain.animal.group;

import domain.animal.Animal;

public abstract class Mammal extends Animal {

    public Mammal(String name, int age) {
        super(name, age);
    }

    @Override
    public String getGroup() {
        return "포유류";
    }
}
