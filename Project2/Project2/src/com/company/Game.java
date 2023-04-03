package com.company;

import java.awt.*;
import java.awt.event.*;

public class Game extends Frame implements Runnable {
    private int width = 1200;
    private int height = 600;
    Rectangles rect1 = new Rectangles(10, (height / 2) - 50, 1);
    Rectangles rect2 = new Rectangles(width - 30, (height / 2) - 50, 2);
    Points points1 = new Points();
    Points points2 = new Points();
    Ball ball = new Ball(width / 2 - 10, height);

    Frame frame = new Frame() {
        public void paint(Graphics g) {
            g.setColor(Color.white);
            g.setFont(new Font("Serif", Font.PLAIN, 50));
            g.drawString("Press 1, 2 or 3 to choose difficulty", width / 6, height / 3);
            g.drawString("1 - Easy", width / 6, (height / 3) + 50);
            g.drawString("2 - Normal", width / 6, (height / 3) + 100);
            g.drawString("3 - Hard", width / 6, (height / 3) + 150);
        }
    };


    public Game() {
        frame.setSize(width, height);
        frame.setBackground(Color.black);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                keyPr(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyPr(e);
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(width / 2, 0, 1, height);
        g.setFont(new Font("Serif", Font.PLAIN, 100));
        g.drawString(Integer.toString(points1.getPoints()), width / 4, height / 3);
        g.drawString(Integer.toString(points2.getPoints()), width * 3 / 4, height / 3);
        rect1.draw(g);
        rect2.draw(g);
        ball.draw(g);
    }

    @Override
    public void update(Graphics g) {
        Graphics buff;
        Image buffer;
        buffer = createImage(width, height);
        buff = buffer.getGraphics();
        buff.setColor(getBackground());
        buff.fillRect(0, 0, width, height);
        buff.setColor(getForeground());
        paint(buff);
        g.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            rect1.move();
            rect2.move();
            ball.move();
            repaint();
            Collision();
            if (points1.getPoints() == 5) {
                createFrame(1);
                break;
            }
            if (points2.getPoints() == 5) {
                createFrame(2);
                break;
            }
            try {
                Thread.sleep(1000 / 30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void Collision() {
        if (rect1.getY() <= 30) {
            rect1.setY(30);
        }
        if (rect1.getY() >= height - 110) {
            rect1.setY(height - 110);
        }
        if (rect2.getY() <= 30) {
            rect2.setY(30);
        }
        if (rect2.getY() >= height - 110) {
            rect2.setY(height - 110);
        }
        if (ball.getY() >= height - 40 || ball.getY() <= 40) {
            ball.setVy(ball.getVy() * -1);
        }
        if (ball.getX() == 30 && (rect1.getY() <= ball.getY() && rect1.getY() + 100 >= ball.getY())) {
            ball.setVx(ball.getVx() * -1);
        }
        if (ball.getX() == width - 50 && (rect2.getY() <= ball.getY() && rect2.getY() + 100 >= ball.getY())) {
            ball.setVx(ball.getVx() * -1);
        }
        if (ball.getX() <= 0) {
            points2.setPoints();
            ball.setX(width / 2 - 10);
            Rand random = new Rand();
            ball.setY(random.randomY(height));
        }
        if (ball.getX() >= width) {
            points1.setPoints();
            ball.setX(width / 2 - 10);
            Rand random = new Rand();
            ball.setY(random.randomY(height));
        }
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void createFrame(int z) {
        this.dispose();
        Frame frame1 = new Frame() {
            public void paint(Graphics g) {
                g.setColor(Color.white);
                g.setFont(new Font("Serif", Font.PLAIN, 100));
                g.drawString("Player " + z + " won", width / 4, height / 2);
                g.drawString(points1.getPoints() + " : " + points2.getPoints(), width / 2 - 100, height / 2 + 100);
            }
        };
        frame1.setBackground(Color.BLACK);
        frame1.setSize(width, height);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        frame1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void keyPr(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1) {
            modes();
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            Rand rand = new Rand();
            ball.setVx(rand.randomVXVY() * 10);
            ball.setVy(rand.randomVXVY() * 10);
            modes();
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            Rand rand = new Rand();
            ball.setVx(rand.randomVXVY() * 20);
            ball.setVy(rand.randomVXVY() * 20);
            modes();
        }

    }

    public void modes() {
        frame.dispose();
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.black);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                rect1.keyR(e);
                rect2.keyR(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                rect1.keyP(e);
                rect2.keyP(e);
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
        new Thread(this).start();
    }

}



