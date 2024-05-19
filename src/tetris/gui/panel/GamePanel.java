package tetris.gui.panel;

import tetris.gui.Game;
import tetris.gui.MainMenu;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        Game game = new Game();
        GamePanel gamePanel = new GamePanel();

        frame.setSize(MainMenu.MAIN_MENU_WIDTH, MainMenu.MAIN_MENU_LENGTH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gamePanel.add(game, BorderLayout.CENTER);
        frame.add(gamePanel);
        frame.setFocusable(false);
        frame.setVisible(true);

        game.startGameLoop();
    }
}
