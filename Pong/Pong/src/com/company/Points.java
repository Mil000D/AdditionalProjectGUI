package com.company;

public class Points extends Thread {
    private int points;


    public Points() {
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }


    public void setPoints() {
        this.points++;
    }


}
