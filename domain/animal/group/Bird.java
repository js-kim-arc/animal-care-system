package domain.animal.group;

import domain.animal.Animal;

public abstract class Bird extends Animal {

    public Bird(String name, int age) {
        super(name, age);
    }

    @Override
    public String getGroup() {
        return "조류";
    }
}
