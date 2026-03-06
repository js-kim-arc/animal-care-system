package domain.animal.species;

import domain.animal.ability.Flyable;
import domain.animal.group.Bird;

public class Eagle extends Bird implements Flyable {

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

    @Override
    public void fly() {
        System.out.println(getName() + "가(이) 하늘 높이 날아오릅니다!");
    }
}
