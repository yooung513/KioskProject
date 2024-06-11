package coffee.kiosk.DAO;

import coffee.kiosk.DTO.Food;
import coffee.kiosk.DTO.FoodOptions;
import coffee.kiosk.DTO.PossibleOptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Date    : 2024.05.31
 *  Author  : 이다영
 *  Summary : 메뉴 관리 화면 레포지토리
 */

public class ManagementRepository {

    public List<Food> findAll() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM FOODS";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Food> foodList = new ArrayList<>();
        while (rs.next()) {
            Food food = new Food();
            food.setFoodId(Integer.parseInt(rs.getString("FOOD_ID")));
            food.setMenuId(Integer.parseInt(rs.getString("MENU_ID")));
            food.setFoodName(rs.getString("FOOD_NAME"));
            food.setFoodPrice(Integer.parseInt(rs.getString("FOOD_PRICE")));
            food.setFoodImg(rs.getString("FOOD_IMG"));

            foodList.add(food);
        }

        conn.close();
        return foodList;
    }

    public List<FoodOptions> findOptions() throws SQLException{
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM FOOD_OPTIONS";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<FoodOptions> OptionsList = new ArrayList<>();
        while (rs.next()) {
            FoodOptions option = new FoodOptions();
            option.setOptionId(Integer.parseInt(rs.getString("OPTION_ID")));
            option.setOptionName(rs.getString("OPTION_NAME"));
            option.setOptionPrice(Integer.parseInt(rs.getString("OPTION_PRICE")));

            OptionsList.add(option);
        }

        conn.close();
        return OptionsList;
    }

    public int insertFoodAndPossibleOptions(Food food, List<PossibleOptions> possibleList) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String insertFoodSql = "{call insert_food(?, ?, ?, ?)}";
        String insertPOSql = "{call insert_possibleoptions(?, ?, ?)}";
        CallableStatement callableStatementFood = null;
        CallableStatement callableStatementPO = null;

        try {
            // FOODS 등록
            callableStatementFood = conn.prepareCall(insertFoodSql);
            callableStatementFood.setInt(1, food.getMenuId());
            callableStatementFood.setString(2, food.getFoodName());
            callableStatementFood.setInt(3, food.getFoodPrice());
            callableStatementFood.setString(4, food.getFoodImg());

            int resultFood = callableStatementFood.executeUpdate();

            int resultPO = 0;
            // POSSIBLE OPTIONS 등록
            for (PossibleOptions option : possibleList) {

                callableStatementPO = conn.prepareCall(insertPOSql);

                callableStatementPO.setInt(1, option.getOptionId());
                callableStatementPO.setString(2, option.getFoodName());
                callableStatementPO.setString(3, option.getOptionName());

                resultPO = callableStatementPO.executeUpdate();

            }
            return resultPO * resultFood;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (callableStatementFood != null) {
                callableStatementFood.close();
            }

            if (callableStatementPO != null) {
                callableStatementPO.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }

    public List<Food> findByMenu(int menuId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        List<Food> foodList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM foods WHERE menu_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setFoodId(Integer.parseInt(rs.getString("FOOD_ID")));
                food.setMenuId(Integer.parseInt(rs.getString("MENU_ID")));
                food.setFoodName(rs.getString("FOOD_NAME"));
                food.setFoodPrice(Integer.parseInt(rs.getString("FOOD_PRICE")));
                food.setFoodImg(rs.getString("FOOD_IMG"));

                foodList.add(food);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return foodList;
    }

    public int deleteMenu(int deleteId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        int result = 0;
        try {
            String sql = "{call delete_food_and_po(?)}";
            CallableStatement callableStatement = conn.prepareCall(sql);
            callableStatement.setInt(1, deleteId);
            result = callableStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return result;
    }

    public Food findByFoodId(int foodId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        Food food = new Food();

        try {
            String sql = "SELECT * FROM foods WHERE food_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                food.setFoodId(Integer.parseInt(rs.getString("FOOD_ID")));
                food.setMenuId(Integer.parseInt(rs.getString("MENU_ID")));
                food.setFoodName(rs.getString("FOOD_NAME"));
                food.setFoodPrice(Integer.parseInt(rs.getString("FOOD_PRICE")));
                food.setFoodImg(rs.getString("FOOD_IMG"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return food;
    }

    public List<PossibleOptions> findPossibleOptions(int foodId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        List<PossibleOptions> poList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM possibleoptions WHERE food_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PossibleOptions po = new PossibleOptions();

                po.setPossibleIdx(Integer.parseInt(rs.getString("POSSIBLE_IDX")));
                po.setOptionId(Integer.parseInt(rs.getString("OPTION_ID")));
                po.setFoodId(Integer.parseInt(rs.getString("FOOD_ID")));
                po.setFoodName(rs.getString("FOOD_NAME"));
                po.setOptionName(rs.getString("OPTION_NAME"));

                poList.add(po);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return poList;
    }

    public int updateFoodAndPO(Food food, List<PossibleOptions> possibleList) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String updateFoodSql = "{call update_food(?, ?, ?, ?, ?)}";
        String updatePOSql = "{call update_po(?, ?, ?, ?)}";
        String deletePOSql = "DELETE FROM possibleoptions WHERE food_id=?";
        CallableStatement callableStatementFood = null;
        CallableStatement callableStatementPO = null;

        try {
            // FOODS 등록
            callableStatementFood = conn.prepareCall(updateFoodSql);
            callableStatementFood.setInt(1, food.getMenuId());
            callableStatementFood.setString(2, food.getFoodName());
            callableStatementFood.setInt(3, food.getFoodPrice());
            callableStatementFood.setString(4, food.getFoodImg());
            callableStatementFood.setInt(5, food.getFoodId());

            int resultFood = callableStatementFood.executeUpdate();

            int resultPO = 0;

            // POSSIBLE OPTIONS 등록
            PreparedStatement pstmt = conn.prepareStatement(deletePOSql);
            pstmt.setInt(1, food.getFoodId());
            pstmt.executeUpdate();

            for (PossibleOptions option : possibleList) {

                callableStatementPO = conn.prepareCall(updatePOSql);
                callableStatementPO.setInt(1, option.getOptionId());
                callableStatementPO.setString(2, option.getFoodName());
                callableStatementPO.setString(3, option.getOptionName());
                callableStatementPO.setInt(4, option.getFoodId());

                resultPO = callableStatementPO.executeUpdate();

            }
            return resultPO + resultFood;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (callableStatementFood != null) {
                callableStatementFood.close();
            }

            if (callableStatementPO != null) {
                callableStatementPO.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }

    public List<Food> findByWord(String word) throws SQLException {
        Connection conn = DBConnection.getConnection();
        List<Food> foodList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM foods WHERE food_name like ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + word + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setFoodId(Integer.parseInt(rs.getString("FOOD_ID")));
                food.setMenuId(Integer.parseInt(rs.getString("MENU_ID")));
                food.setFoodName(rs.getString("FOOD_NAME"));
                food.setFoodPrice(Integer.parseInt(rs.getString("FOOD_PRICE")));
                food.setFoodImg(rs.getString("FOOD_IMG"));

                foodList.add(food);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return foodList;
    }

    public FoodOptions findOptionById(int optionId) throws SQLException{
        Connection conn = DBConnection.getConnection();
        FoodOptions foodOptions = new FoodOptions();

        try {
            String sql = "SELECT * FROM food_options WHERE option_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, optionId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                foodOptions.setOptionId(optionId);
                foodOptions.setOptionName(rs.getString("OPTION_NAME"));
                foodOptions.setOptionPrice(Integer.parseInt(rs.getString("OPTION_PRICE")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return foodOptions;
    }

    public int updateOption(FoodOptions foodOptions) throws SQLException {
        String sql = "{call update_option(?, ?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement callableStatement = conn.prepareCall(sql)) {

            callableStatement.setInt(1, foodOptions.getOptionId());
            callableStatement.setString(2, foodOptions.getOptionName());
            callableStatement.setInt(3, foodOptions.getOptionPrice());

            int result = callableStatement.executeUpdate();
            System.out.println("result = " + result);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int deleteOption(int id) throws SQLException {
        String sql = "DELETE FROM food_options WHERE option_id=?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int result = pstmt.executeUpdate();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int insertOption(FoodOptions foodOptions) throws SQLException {
        String sql = "{call insert_option(?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement callableStatement = conn.prepareCall(sql);) {

            callableStatement.setString(1, foodOptions.getOptionName());
            callableStatement.setInt(2, foodOptions.getOptionPrice());

            int result = callableStatement.executeUpdate();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
