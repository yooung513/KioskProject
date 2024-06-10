package coffee.kiosk.Service;

import coffee.kiosk.DAO.CafemenuGetImageRepository;

import java.sql.SQLException;
import java.util.List;

public class CafemenuGetimageService {

    CafemenuGetImageRepository managementRepository = new CafemenuGetImageRepository();

    public List<String> getImage() throws SQLException {
        return managementRepository.getImagePaths();
    }

    public List<String> getCoffeeImage() throws SQLException {
        return managementRepository.getCoffeeImagePaths();
    }

    public List<String> getBeverageImage() throws SQLException {
        return managementRepository.getBeverageImagePaths();
    }

    public List<String> getSmoothieImage() throws SQLException {
        return managementRepository.getSmoothieImagePaths();
    }

    public List<String> getTeaImage() throws SQLException {
        return managementRepository.getTeaImagePaths();
    }

    public List<String> getDessertImage() throws SQLException {
        return managementRepository.getDessertImagePaths();
    }

    public List<String> getName() throws SQLException {
        return managementRepository.getFoodNames();
    }

    public List<String> getCoffeeName() throws SQLException {
        return managementRepository.getCoffeeNames();
    }

    public List<String> getBeverageName() throws SQLException {
        return managementRepository.getBeverageNames();
    }

    public List<String> getSmoothieName() throws SQLException {
        return managementRepository.getSmoothieNames();
    }

    public List<String> getTeaName() throws SQLException {
        return managementRepository.getTeaNames();
    }

    public List<String> getDessertName() throws SQLException {
        return managementRepository.getDessertNames();
    }

    public List<Integer> getPrice() throws SQLException {
        return managementRepository.getFoodPrice();
    }

    public List<Integer> getCoffeePrice() throws SQLException {
        return managementRepository.getCoffeePrice();
    }

    public List<Integer> getBeveragePrice() throws SQLException {
        return managementRepository.getBeveragePrice();
    }

    public List<Integer> getSmoothiePrice() throws SQLException {
        return managementRepository.getSmoothiePrice();
    }

    public List<Integer> getTeaPrice() throws SQLException {
        return managementRepository.getTeaPrice();
    }

    public List<Integer> getDessertPrice() throws SQLException {
        return managementRepository.getDessertPrice();
    }
}

