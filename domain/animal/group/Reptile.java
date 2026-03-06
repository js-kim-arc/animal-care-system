package domain.animal.group;

import domain.animal.Animal;

public abstract class Reptile extends Animal {

    public Reptile(String name, int age) {
        super(name, age);
    }

    @Override
    public String getGroup() {
        return "파충류";
    }
}
