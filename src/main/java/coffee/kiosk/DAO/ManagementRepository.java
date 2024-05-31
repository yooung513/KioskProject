package coffee.kiosk.DAO;

import coffee.kiosk.DTO.Food;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql = "SELECT * from FOOD";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Food> foodList = new ArrayList<>();
        while (rs.next()) {
            Food food = new Food();
            food.setFoodId(rs.getString("FOOD_ID"));
            food.setMenuId(Integer.parseInt(rs.getString("MENU_ID")));
            food.setFoodName(rs.getString("FOOD_NAME"));
            food.setFoodPrice(Integer.parseInt(rs.getString("FOOD_PRICE")));
            food.setFoodImg(rs.getString("FOOD_IMG"));
            foodList.add(food);
        }

        return foodList;
    }
}
