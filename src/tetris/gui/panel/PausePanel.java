package tetris.gui.panel;

import tetris.gui.Game;
import tetris.gui.MainMenu;
import tetris.gui.loadResources.InitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PausePanel extends JPanel {
    private final MainMenu menu;
    private final Game game;
    public static final String MENU_PAUSE_NAME = "Pause";
    public static final String CONTINUE_TEXT = "Continue";
    public static final String EXIT_TEXT = "Exit";
    public JButton continue_button;
    public JButton exit;
    public PausePanel(MainMenu menu, Game game) {
        this.menu = menu;
        this.game = game;
        init();
    }
    private void init() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        JLabel label = new JLabel(MENU_PAUSE_NAME);
        label.setForeground(Color.WHITE);
        panel.add(label);

        continue_button = new JButton(new PauseHandler(CONTINUE_TEXT));
        exit = new JButton(new PauseHandler(EXIT_TEXT));

        Dimension buttonSize = new Dimension(200, 50);
        continue_button.setPreferredSize(buttonSize);
        exit.setPreferredSize(buttonSize);

        panel.add(continue_button);
        panel.add(exit);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 0);
        panel.add(continue_button, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(exit, constraints);

        setOpaque(true);

        add(panel, BorderLayout.CENTER);

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

    private class PauseHandler extends AbstractAction {
        public PauseHandler(String name) {
            putValue(AbstractAction.NAME, name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (getValue(AbstractAction.NAME) == CONTINUE_TEXT) {
                menu.showGame();
                game.resumeGameLoop();
            } else if (getValue(AbstractAction.NAME) == EXIT_TEXT) {
                menu.showMainLayer();
                menu.reset();
                game.stopGameLoop();
            }
        }
    }
}
