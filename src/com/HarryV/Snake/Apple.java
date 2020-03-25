package com.HarryV.Snake;

import java.awt.*;

public class Apple {

    private Color appleColor;
    private Color staengel;
    private int r;
    private int x;
    private int y;

    Apple(int x,int y) {
        this.x = x;
        this.y = y;
        r = 8;
        appleColor = Color.RED;
        staengel = new Color(139,10,10);
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void draw(Graphics2D g) {
        g.setColor(appleColor);
        g.fillOval(x - r,y - r,2 * r, 2 * r);

        g.setStroke(new BasicStroke(1));
        g.setColor(appleColor.brighter());
        g.drawOval(x - r,y - r,2 * r, 2 * r);

        g.setColor(staengel);
        g.setStroke(new BasicStroke(3));
        g.drawLine(x - r/3,y - (r /2),x + r/2 ,(int)(y - 1.5 * r));


    }

}
