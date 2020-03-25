package com.HarryV.Snake;

import java.awt.*;

public class Tail {

    //FIELDS
    private int x;
    private int y;
    private int rAlpha;
    private int rBeta;
    private static int r = 30;
    private static boolean isGrowing;



    //CONSTRUCTOR
    public Tail(int x, int y,int rAlpha, int rBeta){
        this.x = x;
        this.y = y;
        this.rAlpha = rAlpha;
        this.rBeta = rBeta;
    }

    //METHODS
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setrAlpha(int rAlpha) { this.rAlpha = rAlpha; }
    public void setrBeta(int rBeta) { this.rBeta = rBeta; }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getrAlpha() { return rAlpha; }
    public int getrBeta() { return rBeta; }

    public static boolean isGrowing() { return isGrowing; }
    public static void setGrowing(boolean growing) { isGrowing = growing; }

    //draw tail
    public void draw(Graphics2D g) {

        g.setColor(Color.GREEN);
        g.fillRect(x,y,rAlpha,rBeta);

    }


}
