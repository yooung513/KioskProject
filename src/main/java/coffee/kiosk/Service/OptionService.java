package coffee.kiosk.Service;

import coffee.kiosk.DAO.OptionRepository;
import coffee.kiosk.model.Menuimg;

import java.sql.SQLException;
import java.util.List;

public class OptionService {
    private Menuimg selectedMenuimg;
    private OptionRepository optionRepository;

    public OptionService(Menuimg selectedMenuimg){
        this.selectedMenuimg = selectedMenuimg;
        this.optionRepository = new OptionRepository();
    }

    public List<String> getOptionName(Menuimg selectedMenuimg) throws SQLException {
        return optionRepository.getOptionsByFoodName(selectedMenuimg.getFood_name());
    }


}

