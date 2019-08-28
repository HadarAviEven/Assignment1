package com.hadar.assignment1;

public class Fruit {

    private String name;
    private int calories;
    private int imageID;

    public Fruit(String name, int calories, int imageID) {
        this.name = name;
        this.calories = calories;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getImageID() {
        return imageID;
    }
}
