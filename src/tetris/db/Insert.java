package tetris.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
    public static int Insert(String name,Integer score){
        testConnection connection = new testConnection();
        Statement stm = null;


     try(Connection cn = connection.connect()){

        stm= cn.createStatement();
        String sqlInstruction = "INSERT INTO scores VALUES('"+name+"',"+score+");";
        stm.executeUpdate(sqlInstruction);
        return 0;
     } catch (SQLException e) {
         e.printStackTrace();
         return 1;
     }


    }
}
