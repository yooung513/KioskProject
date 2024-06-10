package coffee.kiosk.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CafemenuGetImageRepository {

    public List<String> getImagePaths() throws SQLException {
        List<String> imagePaths = new ArrayList<>();
        String sql = "SELECT FOOD_IMG FROM FOODS ORDER BY FOOD_ID ";

        try(Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String imagePath = rs.getString("FOOD_IMG");
                imagePaths.add(imagePath);
            }
            return imagePaths;
        }
    }

    public List<String> getCoffeeImagePaths() throws SQLException {
        List<String> coffeeImagePaths = new ArrayList<>();
        String sql = "SELECT FOOD_IMG FROM FOODS WHERE MENU_ID = 1 ORDER BY FOOD_ID ";

        try(Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String coffeeImagePath = rs.getString("FOOD_IMG");
                coffeeImagePaths.add(coffeeImagePath);
            }
            return coffeeImagePaths;
        }
    }

    public List<String> getBeverageImagePaths() throws SQLException {
        List<String> beverageImagePaths = new ArrayList<>();
        String sql = "SELECT FOOD_IMG FROM FOODS WHERE MENU_ID = 2 ORDER BY FOOD_ID ";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String beverageImagePath = rs.getString("FOOD_IMG");
                beverageImagePaths.add(beverageImagePath);
            }
            return beverageImagePaths;
        }
    }

    public List<String> getSmoothieImagePaths() throws SQLException {
        List<String> smoothieImagePaths = new ArrayList<>();
        String sql = "SELECT FOOD_IMG FROM FOODS WHERE MENU_ID = 3 ORDER BY FOOD_ID ";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String smoothieImagePath = rs.getString("FOOD_IMG");
                smoothieImagePaths.add(smoothieImagePath);
            }
            return smoothieImagePaths;
        }
    }

    public List<String> getTeaImagePaths() throws SQLException {
        List<String> teaImagePaths = new ArrayList<>();
        String sql = "SELECT FOOD_IMG FROM FOODS WHERE MENU_ID = 4 ORDER BY FOOD_ID ";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String teaImagePath = rs.getString("FOOD_IMG");
                teaImagePaths.add(teaImagePath);
            }
            return teaImagePaths;
        }
    }

    public List<String> getDessertImagePaths() throws SQLException {
        List<String> dessertImagePaths = new ArrayList<>();
        String sql = "SELECT FOOD_IMG FROM FOODS WHERE MENU_ID = 5 ORDER BY FOOD_ID ";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String dessertImagePath = rs.getString("FOOD_IMG");
                dessertImagePaths.add(dessertImagePath);
            }
            return dessertImagePaths;
        }
    }

    public List<String> getFoodNames() throws SQLException {
        List<String> namePaths = new ArrayList<>();
        String sql = "SELECT FOOD_NAME FROM FOODS ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String namePath = rs.getString("FOOD_NAME");
                namePaths.add(namePath);
            }
            return namePaths;
        }
    }

    public List<String> getCoffeeNames() throws SQLException {
        List<String> coffeenames = new ArrayList<>();
        String sql = "SELECT FOOD_NAME FROM FOODS WHERE MENU_ID =1 ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String coffeename = rs.getString("FOOD_NAME");
                coffeenames.add(coffeename);
            }
            return coffeenames;
        }
    }

    public List<String> getBeverageNames() throws SQLException {
        List<String> beveragenames = new ArrayList<>();
        String sql = "SELECT FOOD_NAME FROM FOODS WHERE MENU_ID =2 ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String beveragename = rs.getString("FOOD_NAME");
                beveragenames.add(beveragename);
            }
            return beveragenames;
        }
    }

    public List<String> getSmoothieNames() throws SQLException {
        List<String> smoothieNames = new ArrayList<>();
        String sql = "SELECT FOOD_NAME FROM FOODS WHERE MENU_ID =3 ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String smoothieName = rs.getString("FOOD_NAME");
                smoothieNames.add(smoothieName);
            }
            return smoothieNames;
        }
    }

    public List<String> getTeaNames() throws SQLException {
        List<String> teaNames = new ArrayList<>();
        String sql = "SELECT FOOD_NAME FROM FOODS WHERE MENU_ID =4 ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String teaName = rs.getString("FOOD_NAME");
                teaNames.add(teaName);
            }
            return teaNames;
        }
    }

    public List<String> getDessertNames() throws SQLException {
        List<String> dessertNames = new ArrayList<>();
        String sql = "SELECT FOOD_NAME FROM FOODS WHERE MENU_ID =5 ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String dessertName = rs.getString("FOOD_NAME");
                dessertNames.add(dessertName);
            }
            return dessertNames;
        }
    }


    public List<Integer> getFoodPrice() throws SQLException {
        List<Integer> pricePaths = new ArrayList<>();
        String sql = "SELECT FOOD_PRICE FROM FOODS ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int pricePath = rs.getInt("FOOD_PRICE");
                pricePaths.add(pricePath);
            }
            return pricePaths;
        }
    }

    public List<Integer> getCoffeePrice() throws SQLException {
        List<Integer> coffeePrices = new ArrayList<>();
        String sql = "SELECT FOOD_PRICE FROM FOODS WHERE MENU_ID=1  ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int coffeePrice = rs.getInt("FOOD_PRICE");
                coffeePrices.add(coffeePrice);
            }
            return coffeePrices;
        }
    }

    public List<Integer> getBeveragePrice() throws SQLException {
        List<Integer> beveragePrices = new ArrayList<>();
        String sql = "SELECT FOOD_PRICE FROM FOODS WHERE MENU_ID=2  ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int beveragePrice = rs.getInt("FOOD_PRICE");
                beveragePrices.add(beveragePrice);
            }
            return beveragePrices;
        }
    }

    public List<Integer> getSmoothiePrice() throws SQLException {
        List<Integer> smoothiePrices = new ArrayList<>();
        String sql = "SELECT FOOD_PRICE FROM FOODS WHERE MENU_ID=3  ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int smoothiePrice = rs.getInt("FOOD_PRICE");
                smoothiePrices.add(smoothiePrice);
            }
            return smoothiePrices;
        }
    }

    public List<Integer> getTeaPrice() throws SQLException {
        List<Integer> teaPrices = new ArrayList<>();
        String sql = "SELECT FOOD_PRICE FROM FOODS WHERE MENU_ID=4  ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int teaPrice = rs.getInt("FOOD_PRICE");
                teaPrices.add(teaPrice);
            }
            return teaPrices;
        }
    }

    public List<Integer> getDessertPrice() throws SQLException {
        List<Integer> dessertPrices = new ArrayList<>();
        String sql = "SELECT FOOD_PRICE FROM FOODS WHERE MENU_ID=5  ORDER BY FOOD_ID";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int dessertPrice = rs.getInt("FOOD_PRICE");
                dessertPrices.add(dessertPrice);
            }
            return dessertPrices;
        }
    }

}
