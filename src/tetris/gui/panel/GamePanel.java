package tetris.gui.panel;

import tetris.gui.Game;
import tetris.gui.MainMenu;
import tetris.gui.NextPieceBox;
import tetris.gui.loadResources.InitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {

    private final MainMenu menu;
    private final Game game;
    private final NextPieceBox nextPieceBox;
    public static final String PAUSE_TEXT = "Pause";
    private JButton pause;
    public GamePanel(MainMenu menu, Game game) {
        this.menu = menu;
        this.game = game;
        nextPieceBox = new NextPieceBox();
        game.configureNextPieceBox(nextPieceBox);
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 2));
        setOpaque(false);

        JPanel gamePanel = new JPanel();
        gamePanel.setOpaque(false);
        gamePanel.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(infoPanel, BoxLayout.Y_AXIS);
        infoPanel.setLayout(boxLayout);
        infoPanel.setOpaque(false);

        pause = new JButton(new PauseAction("Pause"));

        Dimension buttonSize = new Dimension(150, 50);
        pause.setPreferredSize(buttonSize);

        infoPanel.add(pause);
        infoPanel.add(nextPieceBox);

        GridBagConstraints constraints = new GridBagConstraints();
        /* Score
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 0, 0, 0);
        infoPanel.add(start, constraints);

         */

        constraints.gridx = 0;
        constraints.gridy = 2;
        infoPanel.add(nextPieceBox, constraints);



        constraints.gridx = 0;
        constraints.gridy = 3;
        infoPanel.add(pause, constraints);


        add(game);
        add(infoPanel);
        setFocusable(false);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = InitImage.BACKGROUND.getImage();
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private class PauseAction extends AbstractAction {
        public PauseAction(String name) {
            putValue(AbstractAction.NAME, name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.showPauseLayer();
        }
    }
}
