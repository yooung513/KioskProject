package coffee.kiosk.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CafemenuLoginRepository {

    public String getCode() throws SQLException{

        Connection conn = DBConnection.getConnection();
        String sql = "SELECT USER_CODE FROM USERS";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String password = null;

        if (rs.next()){
            password = rs.getString("USER_CODE");
        }


        return password;
    }

}
