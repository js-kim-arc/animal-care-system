package domain.animal.species;

import domain.animal.ability.Flyable;
import domain.animal.ability.Swimmable;
import domain.animal.group.Bird;
import domain.zoo.Food;

import java.util.EnumSet;
import java.util.Set;

public class Penguin extends Bird implements Swimmable {

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

    @Override
    public void swim() {
        System.out.println(getName() + "가(이) 물속을 빠르게 헤엄칩니다!");
    }

    @Override
    public Set<Food> getAllowedFoods() {
        return EnumSet.of(Food.FISH);
    }
}
