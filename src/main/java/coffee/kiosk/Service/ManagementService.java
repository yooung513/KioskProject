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

    public List<Food> findByMenu(int menuId) throws SQLException {
        return managementRepository.findByMenu(menuId);
    }

    public int deleteMenu(int deleteId) throws SQLException {
        return managementRepository.deleteMenu(deleteId);
    }

    public Food findByFoodId(int foodId) throws SQLException {
        return managementRepository.findByFoodId(foodId);
    }

    public List<PossibleOptions> findPossibleOptions(int foodId) throws SQLException {
        return managementRepository.findPossibleOptions(foodId);
    }

    public int updateFoodAndPO(Food food, List<PossibleOptions> possibleList) throws SQLException {
        return managementRepository.updateFoodAndPO(food, possibleList);
    }

    public List<Food> findByWord(String word) throws SQLException {
        return managementRepository.findByWord(word);
    }

    public FoodOptions findOptionById(int optionId) throws SQLException {
        return managementRepository.findOptionById(optionId);
    }

    public int updateOption(FoodOptions foodOptions) throws SQLException {
        return managementRepository.updateOption(foodOptions);
    }

    public int deleteOption(int id) throws SQLException {
        return managementRepository.deleteOption(id);
    }

    public int insertOption(FoodOptions foodOptions) throws SQLException {
        return managementRepository.insertOption(foodOptions);
    }
}
