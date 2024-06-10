package coffee.kiosk.model;

import java.util.HashSet;
import java.util.Set;

public class OptionsSingleton {
    private static OptionsSingleton instance;
    private Set<Integer> selectedOptions;

    private OptionsSingleton() {
        selectedOptions = new HashSet<>();
    }

    public static OptionsSingleton getInstance() {
        if (instance == null) {
            instance = new OptionsSingleton();
        }
        return instance;
    }

    public Set<Integer> getSelectedOptions() {
        return selectedOptions;
    }

    public void addOption(Integer option) {
        selectedOptions.add(option);
    }

    public void removeOption(Integer option) {
        selectedOptions.remove(option);
    }
}
