package coffee.kiosk.DAO;

import java.sql.*;

public class DatabaseConnect {
    public static void main(String[] args) {

        // SQL 사용하는 방법 테스트

        Connection conn = null;
        ResultSet rs = null;
        String userid = "dev_kiosk";
        String passwd = "1234";

        try {

            conn = serverConnect(userid, passwd);

            String sql = "select hot from orders";

            rs = getSQLResult(conn, sql);

            while (rs.next()) {
                int hot = rs.getInt("hot");
                System.out.println(hot);
            }

            closeResources(rs, conn);

        } catch (Exception e) {
            e.printStackTrace();  // Print stack trace to console
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                System.out.println("EROOR");
                e.printStackTrace();  // Print stack trace to console
            }
        }
    }

    public static Connection serverConnect(String userid, String pwd) {
        try {
            // Step 1: Load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Step 2: Create the connection object
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", userid, pwd);
        } catch (Exception e) {
            System.out.println("Connection denied: " + e.getMessage());
            return null;
        }
    }

    public static ResultSet getSQLResult(Connection con, String query) {
        try {
            // Step 3: Create the statement object
            Statement stmt = con.createStatement();

            // Step 4: Execute query
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Cannot execute query: " + e.getMessage());
            return null;
        }
    }

    public static void closeResources(ResultSet rs, Connection con) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("Cannot close ResultSet: " + e.getMessage());
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Cannot close the Database connection: " + e.getMessage());
            }
        }
    }
}

