package coffee.kiosk.Service;

import coffee.kiosk.DAO.ManagementRepository;
import coffee.kiosk.DTO.Food;
import coffee.kiosk.DTO.FoodOptions;
import coffee.kiosk.DTO.PossibleOptions;

import java.sql.SQLException;
import java.util.List;

/**
 *  Date    : 2024.05.31
 *  Author  : 이다영
 *  Summary : 메뉴 관리 화면 서비스
 */
public class ManagementService {

    ManagementRepository managementRepository = new ManagementRepository();

    public List<Food> findAll() throws SQLException {
        return managementRepository.findAll();
    }

    public List<FoodOptions> findOptions() throws SQLException {
        return managementRepository.findOptions();
    }

    public int insertFoodAndPossibleOptions(Food food, List<PossibleOptions> possibleList) throws SQLException {
        return managementRepository.insertFoodAndPossibleOptions(food, possibleList);
    }
}
