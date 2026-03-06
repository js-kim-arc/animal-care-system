package domain;

import domain.animal.Animal;
import domain.animal.ability.Flyable;
import domain.animal.ability.Swimmable;
import domain.animal.species.*;
import domain.zoo.Zoo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Zoo zoo = new Zoo();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int menu = readInt("메뉴를 선택하세요: ");

            switch (menu) {
                case 1:
                    registerAnimal();
                    break;
                case 2:
                    zoo.showAnimals();
                    break;
                case 3:
                    feedAnimal();
                    break;
                case 4:
                    playWithAnimal();
                    break;
                case 5:
                    showAnimalStatus();
                    break;
                case 6:
                    hearAnimalSound();
                    break;
                case 7:
                    runAbility();
                    break;
                case 8:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 메뉴입니다.");
            }

            System.out.println();
        }
    }


    // readable code 
    private static void printMenu() {
        System.out.println("=== 동물원 관리 시스템 ===");
        System.out.println("1. 동물 등록");
        System.out.println("2. 동물 목록 보기");
        System.out.println("3. 먹이주기");
        System.out.println("4. 동물과 놀기");
        System.out.println("5. 동물 상태 확인");
        System.out.println("6. 울음소리 듣기");
        System.out.println("7. 능력 실행");
        System.out.println("8. 종료");
    }


    // Animal 생성 - Ocp
    // 확장
    private static Animal createAnimal(int type, String name, int age) {
        switch (type) {
            case 1:
                return new Dog(name, age);
            case 2:
                return new Cat(name, age);
            case 3:
                return new Eagle(name, age);
            case 4:
                return new Penguin(name, age);
            case 5:
                return new Lion(name, age);
            case 6:
                return new Elephant(name, age);
            case 7:
                return new Snake(name, age);
            case 8:
                return new Turtle(name, age);
            default:
                throw new IllegalArgumentException("동물 종류는 1~8 범위에서 선택할 수 있습니다.");
        }
    }

    private static void registerAnimal() {
        try {
            System.out.print("동물 이름을 입력하세요: ");
            String name = scanner.nextLine();

            int age = readInt("동물 나이를 입력하세요: ");
            int type = readInt(
                    "동물 종류를 선택하세요 (1.강아지 2.고양이 3.독수리 4.펭귄 5.사자 6.코끼리 7.뱀 8.거북이): "
                              );

            Animal animal = createAnimal(type, name, age);
            zoo.addAnimal(animal);

        } catch (IllegalArgumentException e) {
            System.out.println("입력 오류: " + e.getMessage());
        }
    }

    private static void feedAnimal() {
        try {
            if (zoo.isEmpty()) {
                System.out.println("등록된 동물이 없습니다.");
                return;
            }

            // 코드 리팩토링 고민
            System.out.println("먹이를 줄 동물을 선택하세요:");
            zoo.showAnimals();

            int choice = readInt("선택: ");
            zoo.feedAnimal(choice - 1);

        } catch (IllegalArgumentException e) {
            System.out.println("입력 오류: " + e.getMessage());
        }
    }

    private static void playWithAnimal() {
        try {
            if (zoo.isEmpty()) {
                System.out.println("등록된 동물이 없습니다.");
                return;
            }

            System.out.println("놀아줄 동물을 선택하세요:");
            zoo.showAnimals();

            int choice = readInt("선택: ");
            zoo.playWithAnimal(choice - 1);

        } catch (IllegalArgumentException e) {
            System.out.println("입력 오류: " + e.getMessage());
        }
    }

    private static void showAnimalStatus() {
        try {
            if (zoo.isEmpty()) {
                System.out.println("등록된 동물이 없습니다.");
                return;
            }

            System.out.println("상태를 확인할 동물을 선택하세요:");
            zoo.showAnimals();

            int choice = readInt("선택: ");
            zoo.showAnimalStatus(choice - 1);

        } catch (IllegalArgumentException e) {
            System.out.println("입력 오류: " + e.getMessage());
        }
    }

    private static void hearAnimalSound() {
        try {
            if (zoo.isEmpty()) {
                System.out.println("등록된 동물이 없습니다.");
                return;
            }

            System.out.println("울음소리를 들을 동물을 선택하세요:");
            zoo.showAnimals();

            int choice = readInt("선택: ");
            zoo.hearAnimalSound(choice - 1);

        } catch (IllegalArgumentException e) {
            System.out.println("입력 오류: " + e.getMessage());
        }
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                scanner.nextLine();
            }
        }
    }

    // 진행 -> flag 값으로 ability 발현 판단
    private static void runAbility() {
        try {
            if (zoo.isEmpty()) {
                System.out.println("등록된 동물이 없습니다.");
                return;
            }

            System.out.println("능력을 실행할 동물을 선택하세요:");
            zoo.showAnimals();
            int choice = readInt("선택: ");

            Animal animal = zoo.getAnimal(choice - 1);

            boolean executed = false;

            if (animal instanceof Flyable) {
                ((Flyable) animal).fly();
                executed = true;
            }

            if (animal instanceof Swimmable) {
                ((Swimmable) animal).swim();
                executed = true;
            }

            // 능력 없음
            if (!executed) {
                System.out.println(animal.getName() + "는(은) 실행할 수 있는 특별 능력이 없습니다.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("입력 오류: " + e.getMessage());
        }
    }
}
