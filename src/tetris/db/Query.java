package tetris.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {


    public static ResultSet executeQuery() {
        testConnection connection = new testConnection();
        Statement stm = null;

        try (Connection cn = connection.connect()) {
            stm = cn.createStatement();
            String sqlInstruction = "SELECT * FROM scores;";
           try (ResultSet rs = stm.executeQuery(sqlInstruction)){
               return rs;
           } catch (SQLException e){
               e.printStackTrace();
               return null;
           }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
}
