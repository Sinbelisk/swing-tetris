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
    private JLabel tittle;
    private JButton pause;
    private JLabel scoreLabel;
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

        tittle = new JLabel(MainMenu.APP_NAME);
        tittle.setForeground(Color.WHITE);
        pause = new JButton(new PauseAction("Pause"));

        Dimension buttonSize = new Dimension(150, 50);
        pause.setPreferredSize(buttonSize);

        scoreLabel = new JLabel();
        scoreLabel.setForeground(Color.WHITE);
        String score = String.valueOf(game.getGameManager().scoreManager.getCurrentScore());
        scoreLabel.setText(score);


        infoPanel.add(pause);
        infoPanel.add(nextPieceBox);

        GridBagConstraints constraints = new GridBagConstraints();


        constraints.gridx = 0;
        constraints.gridy = 1;
        infoPanel.add(tittle, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        infoPanel.add(scoreLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        infoPanel.add(nextPieceBox, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        infoPanel.add(pause, constraints);

        add(game);
        add(infoPanel);
        setFocusable(false);
        setVisible(true);
    }

    public void setScore(int score){
        String value = String.valueOf(score);
        String labelText = "Current score: " + value;
        scoreLabel.setText(labelText);
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
