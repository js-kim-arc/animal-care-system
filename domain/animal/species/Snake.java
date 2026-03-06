package domain.animal.species;


import domain.animal.group.Reptile;
import domain.zoo.Food;

import java.util.EnumSet;
import java.util.Set;

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

    @Override
    public Set<Food> getAllowedFoods() {
        return EnumSet.of(Food.MEAT);
    }

}