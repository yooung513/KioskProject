package coffee.kiosk.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class  OptionRepository {

    public List<String> getOptionsByFoodName(String foodName) throws SQLException {
        List<String> options = new ArrayList<>();

        String sql = "SELECT OPTION_NAME FROM POSSIBLEOPTIONS WHERE FOOD_NAME = ? ORDER BY CASE " +
                     "WHEN OPTION_NAME = '선택안함' THEN 1 " +
                     "WHEN OPTION_NAME = 'HOT' THEN 2 " +
                     "WHEN OPTION_NAME = 'ICE' THEN 3 " +
                     "WHEN OPTION_NAME = '얼음 조금' THEN 4 " +
                     "WHEN OPTION_NAME = '얼음 보통' THEN 5 " +
                     "WHEN OPTION_NAME = '얼음 많이' THEN 6 "+
                     "WHEN OPTION_NAME = '샷 연하게' THEN 7 " +
                     "WHEN OPTION_NAME = '샷 보통' THEN 8 " +
                     "WHEN OPTION_NAME = '샷 진하게' THEN 9 " +
                     "WHEN OPTION_NAME = '스태비아 추가' THEN 10 " +
                     "WHEN OPTION_NAME = '시럽 추가' THEN 11 " +
                     "WHEN OPTION_NAME = '디카페인 변경' THEN 12 " +
                     "ELSE 13 END";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, foodName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String optionName = rs.getString("OPTION_NAME");
                options.add(optionName);
            }
        }

        return options;
    }

    public List<Integer> getOptionPrice(String foodName) throws SQLException {
        List<Integer> optionPrices = new ArrayList<>();

        String sql =  "SELECT fo.OPTION_PRICE " +
                      "FROM POSSIBLEOPTIONS po " +
                      "JOIN FOOD_OPTIONS fo ON po.OPTION_ID = fo.OPTION_ID " +
                      "WHERE po.FOOD_NAME = ?";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, foodName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int optionPrice = rs.getInt("OPTION_PRICE");
                optionPrices.add(optionPrice);
            }
        }

        return optionPrices;
    }

}


