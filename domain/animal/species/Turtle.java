package domain.animal.species;

import domain.animal.group.Reptile;

public class Turtle extends Reptile {

    public Turtle(String name, int age) {
        super(name, age);
    }

    @Override
    public String getType() {
        return "거북이";
    }

    @Override
    public String makeSound() {
        return "…";
    }
}