package domain.animal.species;

import domain.animal.group.Reptile;
import domain.zoo.Food;

import java.util.EnumSet;
import java.util.Set;

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

    @Override
    public Set<Food> getAllowedFoods() {
        return EnumSet.of(Food.PLANT, Food.FISH);
    }
}