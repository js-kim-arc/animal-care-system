package domain;

import domain.animal.Animal;
import domain.animal.Cat;
import domain.animal.Dog;
import domain.zoo.Zoo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Zoo zoo = new Zoo();

    public static void main(String[] args) {
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
                System.out.println("프로그램을 종료합니다.");
                scanner.close();
                return;
            default:
                System.out.println("잘못된 메뉴입니다.");
        }

        System.out.println();
    }


    // readable code 
    private static void printMenu() {
        System.out.println("=== 동물원 관리 시스템 ===");
        System.out.println("1. 동물 등록");
        System.out.println("2. 동물 목록 보기");
        System.out.println("3. 종료");
    }

    // Animal 생성 - Ocp
    private static Animal createAnimal(int type, String name, int age) {
        if (type == 1) {
            return new Dog(name, age);
        }

        if (type == 2) {
            return new Cat(name, age);
        }

        throw new IllegalArgumentException("동물 종류는 1 또는 2만 선택할 수 있습니다.");
    }

    private static void registerAnimal() {
        try {
            System.out.print("동물 이름을 입력하세요: ");
            String name = scanner.nextLine();

            int age = readInt("동물 나이를 입력하세요: ");
            int type = readInt("동물 종류를 선택하세요 (1.강아지 2.고양이): ");

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
}
