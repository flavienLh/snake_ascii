package com.jad.view;

import com.jad.Constants;
import com.jad.IController;
import com.jad.IModel;
import com.jad.IView;
import com.jad.model.Model;

import javax.swing.*;
import java.awt.*;

import static com.jad.view.GameWindow.*;

public class View implements IView {
    private IModel model;
    private IController controller;
    private JTextArea screen;
    private final GameWindow gameWindow = new GameWindow("Ascii Snake");

    @Override
    public void display() {
        this.gameWindow.setScreen("Not yet implemented");
    }

    @Override
    public void setModel(final IModel model) {
        this.model = model;
    }

    @Override
    public void setController(final IController controller) {
        this.controller = controller;
    }

    public void drawGameState(IModel model) {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < Constants.GAME_HEIGHT; y++) {
            for (int x = 0; x < Constants.GAME_WIDTH; x++) {
                Point point = new Point(x, y);

                if (model.getPlayerSnake().contains(point)) {
                    sb.append(PLAYER_SNAKE_CELL);
                } else if (model.getAiSnake().contains(point)) {
                    sb.append(AI_SNAKE_CELL);
                } else if (model.getApple() != null && model.getApple().getPosition().equals(point)) {
                    int value = model.getApple().getValue();
                    sb.append(value > 0 ? APPLE_CHARACTERS[value - 1] : POISON_APPLE_CHARACTERS[-value - 1]);
                } else {
                    sb.append(EMPTY_CELL);
                }
            }

            sb.append('\n');
        }

        screen.setText(sb.toString());
    }
}
