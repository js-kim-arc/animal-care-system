package domain.animal.species;


import domain.animal.group.Mammal;
import domain.zoo.Food;

import java.util.EnumSet;
import java.util.Set;

public class Elephant extends Mammal {

    public Elephant(String name, int age) {
        super(name, age);
    }

    @Override
    public String getType() {
        return "코끼리";
    }

    @Override
    public String makeSound() {
        return "뿌우우!";
    }

    @Override
    public Set<Food> getAllowedFoods() {
        return EnumSet.of(Food.PLANT, Food.NUT);
    }
}