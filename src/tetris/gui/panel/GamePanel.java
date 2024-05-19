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
        // layout border layout
        setLayout(new BorderLayout());
        setFocusable(false);
        Game game = new Game();
        add(game, BorderLayout.CENTER);
        setVisible(true);

        game.startGameLoop();
        //He puesto el Game game = new Game() aqui para probar si era el initGame() de MainMenu el problema pero no, el
        //el problema pasa en los dos
    }

    public static void main(String[] args) {
        //Este si funcina
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
