package tetris.gui.panel;

import tetris.db.DBManager;
import tetris.gui.MainMenu;
import tetris.gui.loadResources.InitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DBPanel extends JPanel {
    public static final String SCORES_TEXT = "Scores";
    private final MainMenu menu;
    private JButton back;

    public DBPanel(MainMenu menu) {
        this.menu = menu;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        back = new JButton(new BackHandler("Back"));

        Dimension buttonSize = new Dimension(200, 50);
        back.setPreferredSize(buttonSize);

        JLabel scoresLabel = new JLabel(SCORES_TEXT);
        scoresLabel.setForeground(Color.WHITE);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 0, 0);
        panel.add(scoresLabel, constraints);

        JTable scoresTable = createScoresTable();
        JScrollPane tableScrollPane = new JScrollPane(scoresTable);
        scoresTable.setMinimumSize(new Dimension(1, 1));

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.5;
        constraints.weighty = 1.0;
        panel.add(tableScrollPane, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        constraints.weighty = 0;
        panel.add(back, constraints);

        add(panel, BorderLayout.CENTER);
        setOpaque(false);

        setVisible(true);
    }

    private JTable createScoresTable() {
        JTable table = null;
        try {
            ArrayList<String[]> data = DBManager.selectFrom();
            DBManager.ScoresTableModel model = new DBManager.ScoresTableModel(data);
            table = new JTable(model);

            // Establecer el tamaño preferido de la tabla
            table.setPreferredScrollableViewportSize(new Dimension(1, 50));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
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
