package com.HarryV.Snake;

import javax.swing.*;
import java.awt.*;

public class SnakeGame {

    public static void main(String[] args) {
    initGame();
    }

    //FIELDS


    //Game Initiation
    public static void initGame() {

        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(450,75);
        frame.setVisible(true);
        frame.setResizable(false);


        frame.add(new GamePanel(),BorderLayout.CENTER);
        frame.pack();





    }


}
