package com.HarryV.Snake;

import java.awt.*;

public class Head {

    private int x;
    private int y;
    private int r;

    private int dx;
    private int dy;

    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;

    private int alpha;
    private int beta;
    private int gamma;
    private int delta;

    private int hx;
    private int hy;
    private int hrAlpha;
    private int hrBeta;

    //CONSTRUCTOR
    public Head() {

        x = 300;
        y = 300;
        r = 20;
        alpha = 5;
        beta = 0;
        gamma = 5;
        delta = 5;
        hx = 0;
        hy = 0;
        hrAlpha = 0;
        hrBeta = 0;
    }

    //METHODS
    public void setRight(boolean right) {
        this.right = right;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public void setDown(boolean down) {
        this.down = down;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public int getHx() { return hx; }
    public int getHy() { return hy; }
    public int getHrAlpha() { return hrAlpha; }
    public int getHrBeta() { return hrBeta; }

    public void update() {

        if (right) {
            dx = 30;
            alpha = 5;
            beta = 0;
            gamma = 0;
            delta = 5;
        }
        if (left) {
            dx = -30;
            alpha = 5;
            beta = 0;
            gamma = 5;
            delta = 5;
        }
        if (up) {
            dy = -30;
            alpha = 0;
            beta = 5;
            gamma = 5;
            delta = 5;
        }
        if (down) {
            dy = 30;
            alpha = 0;
            beta = 5;
            gamma = 5;
            delta = 0;
        }

        x += dx;
        y += dy;

        if(x < 0) x = GamePanel.width - 30;
        if(y < 0) y = GamePanel.height - 30;
        if(x > GamePanel.width - r) x = 0;
        if(y > GamePanel.width - r) y = 0;

        dx = 0;
        dy = 0;

        hx = x + gamma;
        hy = y + delta;
        hrAlpha = r + alpha;
        hrBeta = r + beta;


    }

    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect(hx,hy,hrAlpha ,hrBeta);

    }

}
