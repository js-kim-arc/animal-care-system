package domain.animal.species;


import domain.animal.group.Reptile;

public class Snake extends Reptile {

    public Snake(String name, int age) {
        super(name, age);
    }

    @Override
    public String getType() {
        return "뱀";
    }

    @Override
    public String makeSound() {
        return "쉬익!";
    }
}