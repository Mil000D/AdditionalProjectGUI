package com.company;

import java.awt.*;

public
class Ball extends Rand {
    private int x, y;
    private int vx, vy;

    public Ball(int x, int height) {
        this.x = x;
        this.y = randomY(height);
        this.vx = randomVXVY() * 5;
        this.vy = randomVXVY() * 5;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void move() {
        this.x += this.vx;
        this.y += this.vy;
    }

    public void draw(Graphics g) {
        g.fillOval(x, y, 20, 20);
    }
}
