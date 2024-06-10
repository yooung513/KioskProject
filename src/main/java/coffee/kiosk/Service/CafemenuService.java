package coffee.kiosk.Service;

import coffee.kiosk.Controller.CafemenuLoginController;
import coffee.kiosk.DAO.CafemenuLoginRepository;
import coffee.kiosk.DAO.ManagementRepository;
import coffee.kiosk.DTO.Food;

import java.sql.SQLException;
import java.util.List;

public class CafemenuService {
    CafemenuLoginRepository managementRepository = new CafemenuLoginRepository();

    public String getCode() throws SQLException {
        return managementRepository.getCode();
    }
}
