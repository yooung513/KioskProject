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
        String sql = "SELECT * from FOODS";
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
        String sql = "SELECT * from FOOD_OPTIONS";
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

            // POSSIBLE OPTIONS 등록
            for (PossibleOptions option : possibleList) {

                callableStatementPO = conn.prepareCall(insertPOSql);

                callableStatementPO.setInt(1, option.getOptionId());
                callableStatementPO.setString(2, option.getFoodName());
                callableStatementPO.setString(3, option.getOptionName());

                int resultPO = callableStatementPO.executeUpdate();

                return resultPO * resultFood;
            }

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
}
