package domain.animal;

public class Dog extends Animal {

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
}
