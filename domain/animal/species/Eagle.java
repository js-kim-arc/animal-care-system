package domain.animal.species;

import domain.animal.group.Bird;

public class Eagle extends Bird {

    public Eagle(String name, int age) {
        super(name, age);
    }

    @Override
    public String getType() {
        return "독수리";
    }

    @Override
    public String makeSound() {
        return "끼이익!";
    }
}
