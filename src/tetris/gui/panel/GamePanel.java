package tetris.gui.panel;

import tetris.gui.MainMenu;
import tetris.gui.loadResources.InitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static tetris.gui.MainMenu.game;

public class GamePanel extends JPanel {
    private final MainMenu menu;
    public static final String PAUSE_TEXT = "Pause";
    private JButton pause;
    public GamePanel(MainMenu menu) {
        this.menu = menu;
        init();
    }

    private void init() {
        setOpaque(false);
        setLayout(new GridLayout(1, 2));

        JPanel gamePanel = new JPanel();
        gamePanel.setOpaque(false);
        gamePanel.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);

        pause = new JButton(new PauseAction("Pause"));

        Dimension buttonSize = new Dimension(150, 50);
        pause.setPreferredSize(buttonSize);

        infoPanel.add(pause);

        GridBagConstraints constraints = new GridBagConstraints();
        /* Score
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 0, 0, 0);
        infoPanel.add(start, constraints);

         */

        /* Grid
        constraints.gridx = 0;
        constraints.gridy = 2;
        infoPanel.add(scores, constraints);
         */

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
