package com.HarryV.Snake;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveAction extends AbstractAction {
    enum Action {
        MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT;
    }


    GamePanel window;
    Action action;

    public MoveAction(GamePanel window, Action action) {
        this.window = window;
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (action) {
            case MOVE_UP:
                GamePanel.setMoving(true);
                GamePanel.snakeHead.setUp(true);
                GamePanel.snakeHead.setDown(false);
                GamePanel.snakeHead.setLeft(false);
                GamePanel.snakeHead.setRight(false);

                break;
            case MOVE_DOWN:
                GamePanel.setMoving(true);
                GamePanel.snakeHead.setDown(true);
                GamePanel.snakeHead.setUp(false);
                GamePanel.snakeHead.setLeft(false);
                GamePanel.snakeHead.setRight(false);
                break;
            case MOVE_LEFT:
                GamePanel.setMoving(true);
                GamePanel.snakeHead.setLeft(true);
                GamePanel.snakeHead.setUp(false);
                GamePanel.snakeHead.setDown(false);
                GamePanel.snakeHead.setRight(false);
                break;
            case MOVE_RIGHT:
                GamePanel.setMoving(true);
                GamePanel.snakeHead.setRight(true);
                GamePanel.snakeHead.setUp(false);
                GamePanel.snakeHead.setDown(false);
                GamePanel.snakeHead.setLeft(false);
                break;
        }
    }
}

