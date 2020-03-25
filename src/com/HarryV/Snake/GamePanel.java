package com.HarryV.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    //FIELDS
    public static int width = 602;
    public static int height = 602;
    public static Head snakeHead;
    public JPanel gameSpace;


    private Thread thread;
    private boolean running;

    private int FPS = 10;

    private Graphics2D g;
    private BufferedImage image;

    Apple apple;
    private boolean searching = false;
    private static boolean isMoving;

    public static ArrayList<Tail> tails;
    private static int score = 0;
    private static int highScore = 0;


    //CONSTRUCTOR
    GamePanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        gameSpace = new JPanel();
        gameSpace.setPreferredSize(new Dimension(width, height));

        add(gameSpace);
        thread = new Thread(this);
        thread.start();

        createKeyBindings(gameSpace);

        setFocusable(true);
        requestFocus();
    }

    public static void setMoving(boolean moving) {
        isMoving = moving;
    }

    @Override
    public void run() {

        running = true;

        snakeHead = new Head();
        tails = new ArrayList<Tail>();
        for (int i = 0; i < 2; i++) {
            tails.add(new Tail(335 + (i * 30), 305, 20 + 5, 20));
        }

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        long startTime;
        long URDTimeMillis;
        long waitTime;
        long totalTime = 0;

        long targetTime = 1000 / FPS;

        int frameCount = 0;
        int maxFrameCount = 10;

        while (running) {

            gameUpdate();
            gameRender();
            gameDraw();

            //Set FPS
            startTime = System.nanoTime();
            URDTimeMillis = ((System.nanoTime() - startTime) / 1000);
            waitTime = targetTime - URDTimeMillis;

            try {
                Thread.sleep(waitTime);
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == maxFrameCount) {
                System.out.println(1000.0 / ((totalTime / frameCount) / 1000000));
                frameCount = 0;
                totalTime = 0;
            }
        }

    }

    public void gameUpdate() {
        //apple update
        if (!searching)
            createApple();


        for (int i = tails.size() - 1; i >= 0; i--) {
            if (isMoving) {
                if (i == 0) {
                    tails.get(0).setX(snakeHead.getHx());
                    tails.get(0).setY(snakeHead.getHy());
                    tails.get(0).setrAlpha(snakeHead.getHrAlpha());
                    tails.get(0).setrBeta(snakeHead.getHrBeta());

                } else {
                    if (i == tails.size() - 1 && Tail.isGrowing()) {
                        tails.add(new Tail(tails.get(i).getX(), tails.get(i).getY(), tails.get(i).getrAlpha(), tails.get(i).getrBeta()));
                        Tail.setGrowing(false);
                    }
                    tails.get(i).setX(tails.get(i - 1).getX());
                    tails.get(i).setY(tails.get(i - 1).getY());
                    tails.get(i).setrAlpha(tails.get(i - 1).getrAlpha());
                    tails.get(i).setrBeta(tails.get(i - 1).getrBeta());

                }
            }
        }

        //head update
        snakeHead.update();

        //head-apple collision
        if (searching) {
            int hx = snakeHead.getX() + 15;
            int hy = snakeHead.getY() + 15;
            int hr = 10;

            double ax = apple.getX();
            double ay = apple.getY();
            double ar = 10;

            double dx = hx - ax;
            double dy = hy - ay;
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < hr + ar) {
                searching = false;
                Tail.setGrowing(true);
                score += tails.size();
            }
        }

        //head-tail collision
        int hx = snakeHead.getX() + 15;
        int hy = snakeHead.getY() + 15;
        int hr = 10;

        for(int i = 0; i < tails.size(); i++) {
            double tx = tails.get(i).getX();
            double ty = tails.get(i).getY();
            double tr = 10;

            double dx = hx - tx;
            double dy = hy - ty;
            double dist = Math.sqrt(dx * dx + dy * dy);

            if(dist < hr + tr) {
                for(int j = tails.size() -1; j >= 2; j--) {
                    tails.remove(j);
                }
                if(highScore < score) {
                    highScore = score;
                }
                score = 0;
            }
        }



    }

    public void gameRender() {

        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);


        //draw grid
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.LIGHT_GRAY.darker());
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                g.drawLine(i * 30, j * 30, i * 30, j * 30);
            }
        }

        //draw apple
        apple.draw(g);

        //draw head
        snakeHead.draw(g);

        //draw tail
        for (Tail tail : tails) {
            tail.draw(g);
        }

        //draw Scores
        g.setColor(Color.white);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        g.drawString("Score: " + score, 10, 25);

        g.setColor(Color.white);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        g.drawString("Highscore: " + highScore, 10, 50);
    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    public void createApple() {
        int randomX;
        int randomY;
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                randomX = (15 + ((int) ((Math.random() * i)) * 30));
                randomY = (15 + ((int) ((Math.random() * j)) * 30));
                apple = new Apple(randomX, randomY);

                searching = true;
            }
        }
    }

    private void createKeyBindings(JPanel p) {
        InputMap im = p.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = p.getActionMap();
        im.put(KeyStroke.getKeyStroke("UP"), MoveAction.Action.MOVE_UP);
        im.put(KeyStroke.getKeyStroke("DOWN"), MoveAction.Action.MOVE_DOWN);
        im.put(KeyStroke.getKeyStroke("LEFT"), MoveAction.Action.MOVE_LEFT);
        im.put(KeyStroke.getKeyStroke("RIGHT"), MoveAction.Action.MOVE_RIGHT);
        am.put(MoveAction.Action.MOVE_UP, new MoveAction(this, MoveAction.Action.MOVE_UP));
        am.put(MoveAction.Action.MOVE_DOWN, new MoveAction(this, MoveAction.Action.MOVE_DOWN));
        am.put(MoveAction.Action.MOVE_LEFT, new MoveAction(this, MoveAction.Action.MOVE_LEFT));
        am.put(MoveAction.Action.MOVE_RIGHT, new MoveAction(this, MoveAction.Action.MOVE_RIGHT));
    }

}
