package com.company;


import java.awt.*;
import java.awt.event.KeyEvent;

public
class Rectangles {
    private int player;
    private int x, y;
    private int vy;


    public Rectangles(int x, int y, int player) {
        this.x = x;
        this.y = y;
        this.player = player;

    }

    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }

    public void move() {
        y += vy;
    }


    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 20, 100);
    }

    public void keyP(KeyEvent e) {
        if (player == 1) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                vy = -20;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                vy = 20;
            }
        } else if (player == 2) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                vy = -20;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                vy = 20;
            }
        }
    }

    public void keyR(KeyEvent e) {
        if (player == 1) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                vy = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                vy = 0;
            }
        } else if (player == 2) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                vy = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                vy = 0;
            }
        }
    }
}

