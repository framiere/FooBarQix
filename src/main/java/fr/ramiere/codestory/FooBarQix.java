package fr.ramiere.codestory;

public abstract class FooBarQix {
    public void displayUpTo(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("number should be strictly positive, got " + number);
        }
        for (int i = 1; i <= number; i++) {
            System.out.println(convert(i));
        }
    }

    public abstract String convert(int number);
}