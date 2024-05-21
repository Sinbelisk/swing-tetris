package tetris.gui.panel;

import tetris.gui.MainMenu;
import tetris.gui.loadResources.InitImage;
import tetris.gui.loadResources.InitSoundtrack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OptionLayer extends JPanel {
    private MainMenu menu;
    public static final String VOLUME_OPTIONS_TEXT = "Volume Options";
    public static final String CONTROLS_TEXT = "Controls";
    public static final String BACK_TEXT = "Back";
    public JSlider volumeSlider;
    public JButton back;

    public OptionLayer(MainMenu menu) {
        this.menu = menu;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        back = new JButton(new BackHandler(BACK_TEXT));

        JLabel options = new JLabel(VOLUME_OPTIONS_TEXT);
        options.setForeground(Color.WHITE);

        Dimension buttonSize = new Dimension(200, 50);
        back.setPreferredSize(buttonSize);

        volumeSlider = new JSlider(0, 100, 0);
        volumeSlider.addChangeListener(e -> {
            float volume = volumeSlider.getValue() / 100.0f;
            InitSoundtrack.MENU_SOUNDTRACK.setVolume(volume);
            InitSoundtrack.GAME_SOUNDTRACK.setVolume(volume);
        });

        JLabel controls = new JLabel(CONTROLS_TEXT);
        controls.setForeground(Color.WHITE);

        // Creating the controls panel
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridBagLayout());
        controlsPanel.setOpaque(false);
        controlsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        GridBagConstraints controlConstraints = new GridBagConstraints();
        controlConstraints.insets = new Insets(5, 5, 5, 5);
        controlConstraints.anchor = GridBagConstraints.WEST;

        addControlMapping(controlsPanel, controlConstraints, "A", "Move Left", 0);
        addControlMapping(controlsPanel, controlConstraints, "D", "Move Right", 1);
        addControlMapping(controlsPanel, controlConstraints, "S", "Lower", 2);
        addControlMapping(controlsPanel, controlConstraints, "R", "Rotate", 3);
        addControlMapping(controlsPanel, controlConstraints, "ESC", "Pause", 4);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 0);
        panel.add(options, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 0, 0);
        panel.add(volumeSlider, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(controls, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(controlsPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(back, constraints);

        add(panel, BorderLayout.CENTER);
        setOpaque(false);

        setVisible(true);
    }

    private void addControlMapping(JPanel panel, GridBagConstraints constraints, String key, String action, int row) {
        constraints.gridx = 0;
        constraints.gridy = row;
        JLabel keyLabel = new JLabel(key);
        keyLabel.setForeground(Color.WHITE);
        panel.add(keyLabel, constraints);

        constraints.gridx = 1;
        JLabel actionLabel = new JLabel(action);
        actionLabel.setForeground(Color.WHITE);
        panel.add(actionLabel, constraints);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = InitImage.BACKGROUND.getImage();
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private class BackHandler extends AbstractAction {
        public BackHandler(String name) {
            putValue(AbstractAction.NAME, name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            menu.showMainLayer();

        }
    }
}
