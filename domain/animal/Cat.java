package domain.animal;

public class Cat extends Animal {

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String getType() {
        return "고양이";
    }
}
