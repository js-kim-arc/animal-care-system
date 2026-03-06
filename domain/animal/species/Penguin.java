package domain.animal.species;

import domain.animal.group.Bird;

public class Penguin extends Bird {

    public Penguin(String name, int age) {
        super(name, age);
    }

    @Override
    public String getType() {
        return "펭귄";
    }

    @Override
    public String makeSound() {
        return "꾸우!";
    }
}
