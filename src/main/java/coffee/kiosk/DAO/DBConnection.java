package coffee.kiosk.DAO;

import java.sql.*;

public class DBConnection {
    public static Connection con;

    public static Connection getConnection() {
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String id = "dev_kiosk";
            String pw = "1234";

            con = DriverManager.getConnection(url, id, pw);


        } catch (Exception e) {
            System.out.println("DB 연결 실패");
        }

        return con;
    }
}
