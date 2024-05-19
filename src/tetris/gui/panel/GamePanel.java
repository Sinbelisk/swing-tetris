package tetris.gui.panel;

import tetris.gui.BoardDrawer;
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
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setFocusable(false);
        Game game = new Game();
        GamePanel gamePanel = new GamePanel();

        frame.setSize(MainMenu.MAIN_MENU_WIDTH, MainMenu.MAIN_MENU_LENGTH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gamePanel.add(game, BorderLayout.CENTER);
        frame.add(gamePanel);
        frame.setFocusable(false);
        frame.setVisible(true);

        // Este metodo inicia el game loop, y me acabo  de dar cuenta que tengo que implementar uno para pausarlo o detenerlo xxdxdxdxdxdxdxdxdxdx
        // Es posible que el thread the de problemas con la interfaces, la implementation que he hecho es muy peruana.
        //game.startGameLoop();
    }
}
