classDiagram
direction TB

class Food {
<<enumeration>>
MEAT
FISH
PLANT
NUT
}

class KeeperSpecialty {
<<enumeration>>
BIRD
LARGE_ANIMAL
REPTILE
}

class Animal {
<<abstract>>
-String name
-int age
-int hunger
-int happiness
+Animal(String name, int age)
+String getGroup()
+String getType()
+String summary()
+String status()
+void feed(Food food)
+void feed(Food food, int hungerDecrease)
+void play()
+void play(int happinessIncrease)
+String makeSound()
+List~Food~ getAllowedFoods()
+boolean canEat(Food food)
}

class Bird {
<<abstract>>
+String getGroup()
}

class Mammal {
<<abstract>>
+String getGroup()
}

class Reptile {
<<abstract>>
+String getGroup()
}

class Flyable {
<<interface>>
+void fly()
}

class Swimmable {
<<interface>>
+void swim()
}

class Dog {
+String getType()
+String makeSound()
+List~Food~ getAllowedFoods()
}

class Cat {
+String getType()
+String makeSound()
+List~Food~ getAllowedFoods()
}

class Eagle {
+String getType()
+String makeSound()
+List~Food~ getAllowedFoods()
+void fly()
}

class Penguin {
+String getType()
+String makeSound()
+List~Food~ getAllowedFoods()
+void swim()
}

class Lion {
+String getType()
+String makeSound()
+List~Food~ getAllowedFoods()
}

class Elephant {
+String getType()
+String makeSound()
+List~Food~ getAllowedFoods()
}

class Snake {
+String getType()
+String makeSound()
+List~Food~ getAllowedFoods()
}

class Turtle {
+String getType()
+String makeSound()
+List~Food~ getAllowedFoods()
+void swim()
}

class ZooKeeper {
-String name
-KeeperSpecialty specialty
+ZooKeeper(String name, KeeperSpecialty specialty)
+boolean isSpecializedFor(Animal animal)
}

class Zoo {
-ArrayList~Animal~ animals
-ArrayList~ZooKeeper~ keepers
+void addAnimal(Animal animal)
+void showAnimals()
+Animal getAnimal(int index)
+void addKeeper(ZooKeeper keeper)
+void showKeepers()
+ZooKeeper getKeeper(int index)
+void feedAnimal(int index, Food food, ZooKeeper keeper)
+void playWithAnimal(int index, ZooKeeper keeper)
+void showAnimalStatus(int index)
+void hearAnimalSound(int index)
+Map~String, Integer~ countByType()
+double averageHappiness()
+List~Animal~ hungryAnimals(int threshold)
+void printSummary(int threshold)
}

class Main {
+main(String[] args)$
}

Animal <|-- Bird
Animal <|-- Mammal
Animal <|-- Reptile

Mammal <|-- Dog
Mammal <|-- Cat
Bird <|-- Eagle
Bird <|-- Penguin
Mammal <|-- Lion
Mammal <|-- Elephant
Reptile <|-- Snake
Reptile <|-- Turtle

Flyable <|.. Eagle
Swimmable <|.. Penguin
Swimmable <|.. Turtle

Zoo "1" o-- "*" Animal : manages
Zoo "1" o-- "*" ZooKeeper : manages

ZooKeeper --> KeeperSpecialty : specialty
Animal --> Food : allowed foods
Zoo --> Food : feed uses
Main --> Zoo : controls