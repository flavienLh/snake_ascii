package com.jad.controller;

import com.jad.Direction;
import com.jad.IController;
import com.jad.IModel;
import com.jad.IView;
import com.jad.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller extends Component implements IController {
    private final IView view;
    private IModel model;
    private boolean gameRunning = true;
    private int t;

    private Timer timer;

    public Controller(final IView view, final IModel model) {
        this.view = view;
        this.model = model;
        this.view.setController(this);
        this.view.setModel(this.model);
        this.model.setController(this);
        this.timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

    }

    @Override
    public Direction getCurrentDirection() {
        return model.getPlayerSnake().getDirection();
    }

    @Override
    public void changePlayerDirection(Direction direction) {
        model.changePlayerDirection(direction);
    }

    @Override
    public void start() {
        this.model.start();
        this.view.display();
        runGame();
        timer.start();
    }

    public void runGame() {
        int t = 0;
        while (gameRunning) {
            this.model.updateGame(t);
            view.display();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t = (t + 1) % 51;
        }
    }

    @Override
    public void restartGame() {

    }

    public void isEndOfTheGame() {
        if (model.isWin()) {
            gameRunning = !gameRunning;
        }
    }

    public void gameOver() {
        Object[] options = {"Recommencer", "Quitter"};
        int choice = JOptionPane.showOptionDialog(this,
                "Game Over! Que voulez-vous faire?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case JOptionPane.YES_OPTION:
                model.resetGame();
                break;
            case JOptionPane.NO_OPTION:
                System.exit(0);
                break;
        }
    }
}

