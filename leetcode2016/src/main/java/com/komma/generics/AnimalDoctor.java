package com.komma.generics;

import java.util.ArrayList;
import java.util.List;

public class AnimalDoctor {

  public void checkAnimals(List<? extends Animal> animals) {
    for(Animal animal: animals) {
      animal.checkup();
    }
  }

  public void teset(List<Animal> animals) {
    animals.add(new Dog());
    for(Animal animal: animals) {
      animal.checkup();
    }
  }

  public static void main(String[] args) {

    List<Animal> animals = new ArrayList<>();
    animals.add(new Cat()); // OK
    animals.add(new Dog());

    // make ArrayLists instead of arrays for Dog, Cat, Bird
    List<Dog> dogs = new ArrayList<>();
    dogs.add(new Dog());
    dogs.add(new Dog());
    List<Cat> cats = new ArrayList<>();
    cats.add(new Cat());
    cats.add(new Cat());
    List<Bird> birds = new ArrayList<>();
    birds.add(new Bird());
    // this code is the same as the Array version
    AnimalDoctor doc = new AnimalDoctor();
    // this worked when we used arrays instead of ArrayLists
    doc.checkAnimals(animals); // send a List<Dog>
    doc.checkAnimals(cats); // send a List<Cat>
    //doc.teset(cats); // send a List<Cat>
    doc.checkAnimals(birds); // send a List<Bird>
  }
}
