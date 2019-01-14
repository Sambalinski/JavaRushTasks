package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;

    public static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void move() {
        for (Horse horse : horses) horse.move();
    }

    public void print() {
        for (Horse horse : horses) horse.print();
        for (int i = 0; i < 10; i++) System.out.print(System.lineSeparator());
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();

    }

    public void printWinner() {
        System.out.printf("Winner is %s!", getWinner().getName());
    }

    public static void main(String[] args) {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Thunder", 3, 0));
        horses.add(new Horse("Lightning", 3, 0));
        horses.add(new Horse("Storm", 3, 0));
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();

    }
}
