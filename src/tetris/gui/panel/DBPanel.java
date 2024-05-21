package tetris.gui.panel;


import tetris.db.DBManager;
import tetris.gui.MainMenu;
import tetris.gui.loadResources.InitImage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DBPanel extends JPanel {
    public static final String SCORES_TEXT = "Scores";
    private final MainMenu menu;
    private JTable dbTable;
    private JButton back;
    private JButton deleteAll;

    public DBPanel(MainMenu menu) {
        this.menu = menu;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        back = new JButton(new DBHandler("Back"));
        deleteAll = new JButton(new DBHandler("Delete All"));

        Dimension buttonSize = new Dimension(200, 50);
        back.setPreferredSize(buttonSize);
        deleteAll.setPreferredSize(buttonSize);

        JLabel scoresLabel = new JLabel(SCORES_TEXT);
        scoresLabel.setForeground(Color.WHITE);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 0, 0);
        panel.add(scoresLabel, constraints);

        dbTable = createScoresTable();
        JScrollPane tableScrollPane = new JScrollPane(dbTable);
        dbTable.setMinimumSize(new Dimension(1, 1));

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        panel.add(tableScrollPane, constraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(back);
        buttonPanel.add(deleteAll);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        constraints.weighty = 0;
        panel.add(buttonPanel, constraints);

        add(panel, BorderLayout.CENTER);
        setOpaque(false);

        setVisible(true);
    }

    private JTable createScoresTable() {
        try {
            ArrayList<String[]> data = DBManager.selectFrom();
            DBManager.ScoresTableModel model = new DBManager.ScoresTableModel(data);
            dbTable = new JTable(model);

            dbTable.setPreferredScrollableViewportSize(new Dimension(200, 300));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbTable;
    }

    private void updateTable() {
        removeAll();
        init();
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = InitImage.BACKGROUND.getImage();
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private class DBHandler extends AbstractAction {
        public DBHandler(String name) {
            putValue(AbstractAction.NAME, name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Back".equals(getValue(AbstractAction.NAME))) {
                menu.showMainLayer();
            } else if ("Delete All".equals(getValue(AbstractAction.NAME))) {
                DBManager.deleteFrom();
                updateTable();
            }
        }
    }
}

