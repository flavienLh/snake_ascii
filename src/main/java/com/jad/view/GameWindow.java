package com.jad.view;

import com.jad.Direction;
import com.jad.IController;
import com.jad.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The type Game window.
 * Use it to display a String in a window.
 * The window is sized to display a string which has 100 characters in width and 50 in height.
 */
class GameWindow extends JFrame {

    private IController controller;
    private static final Font MATRICIAL_FONT = new Font("Cascadia Mono", Font.PLAIN, 12);
    private static final int FRAME_WIDTH = 750;
    private static final int FRAME_HEIGHT = 806;
    private final JTextArea screen = new JTextArea("");
    static final char EMPTY_CELL = ' ';
    static final char PLAYER_SNAKE_CELL = '█';
    static final char AI_SNAKE_CELL = '░';
    private static final char[] DIRECTION_CHARACTERS = {'^', '>', 'v', '<'};
    static final char[] APPLE_CHARACTERS = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final char[] POISON_APPLE_CHARACTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
    private Model gameState;


    /**
     * Instantiates a new Game window.
     *
     * @param title the title of the window
     */
    public GameWindow(final String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(GameWindow.FRAME_WIDTH, GameWindow.FRAME_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.screen.setFont(GameWindow.MATRICIAL_FONT);
        this.screen.setEditable(false);
        this.add(this.screen, BorderLayout.CENTER);
        this.controller = controller;
        this.gameState = gameState;

        this.screen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent keyEvent) {
                GameWindow.this.keyTyped(keyEvent);
            }

            @Override
            public void keyPressed(final KeyEvent keyEvent) {
                GameWindow.this.keyPressed(keyEvent);
            }

            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                GameWindow.this.keyReleased(keyEvent);
            }
        });

        this.setVisible(true);
    }

    private void keyReleased(final KeyEvent keyEvent) {
        // The code to manage the key released event
    }

    private void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyChar()) {
            case 'q':
                controller.changePlayerDirection(Direction.LEFT);
                break;
            case 'd':
                controller.changePlayerDirection(Direction.RIGHT);
                break;
            case 'z':
                controller.changePlayerDirection(Direction.UP);
                break;
            case 's':
                controller.changePlayerDirection(Direction.DOWN);
                break;
        }
    }

    private void keyTyped(final KeyEvent keyEvent) {
        // The code to manage the key typed event
    }

    /**
     * Sets screen.
     *
     * @param screen the screen
     */
    public void setScreen(final String screen) {
        this.screen.setText(screen);
    }


}
