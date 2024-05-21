package tetris.db;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    public static int insertInto(String name, Integer score) {
        Connection connection = new Connection();
        Statement stm = null;

        try (java.sql.Connection cn = connection.connect()) {
            stm = cn.createStatement();
            String sqlInstruction = "INSERT INTO scores VALUES('" + name + "'," + score + ");";
            stm.executeUpdate(sqlInstruction);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static int deleteFrom() {
        Connection connection = new Connection();
        Statement stm = null;

        try (java.sql.Connection cn = connection.connect()) {
            stm = cn.createStatement();
            String sqlInstruction = "DELETE FROM scores;";
            stm.executeUpdate(sqlInstruction);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static void main(String[] args) {
        //deleteFrom();
        insertInto("Javier Milei", 99999);
    }

    public static ArrayList<String[]> selectFrom() {
        ArrayList<String[]> data = new ArrayList<>();
        Connection connection = new Connection();
        Statement stm = null;

        try (java.sql.Connection cn = connection.connect()) {
            stm = cn.createStatement();
            String sqlInstruction = "SELECT * FROM scores;";
            try (ResultSet rs = stm.executeQuery(sqlInstruction)) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    String[] row = new String[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = rs.getString(i);
                    }
                    data.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static class ScoresTableModel extends AbstractTableModel {
        private ArrayList<String[]> data;
        private String[] columnNames = {"User", "Score"};

        public ScoresTableModel(ArrayList<String[]> data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data.get(rowIndex)[columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }
}
