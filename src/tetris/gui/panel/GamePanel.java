package tetris.gui.panel;

import tetris.gui.MainMenu;

import javax.swing.*;
import java.awt.*;

import static tetris.gui.MainMenu.game;

public class GamePanel extends JPanel {
    private final MainMenu menu;
    public GamePanel(MainMenu menu) {
        this.menu = menu;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        add(game, BorderLayout.CENTER);
        setFocusable(false);
        setVisible(true);
    }
}
