package com.jad.view;

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
    private static final Font MATRICIAL_FONT = new Font("Cascadia Mono", Font.PLAIN, 12);
    private static final int FRAME_WIDTH = 750;
    private static final int FRAME_HEIGHT = 806;
    private final JTextArea screen = new JTextArea("");

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
        // The code to manage the key pressed event
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
