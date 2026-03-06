package domain.animal.species;

import domain.animal.Animal;
import domain.animal.group.Mammal;
import domain.zoo.Food;

import java.util.EnumSet;
import java.util.Set;

public class Dog extends Mammal {

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String getType() {
        return "강아지";
    }

    @Override
    public String makeSound() {
        return "멍멍!";
    }
    @Override
    public Set<Food> getAllowedFoods() {
        return EnumSet.of(Food.MEAT);
    }

}
