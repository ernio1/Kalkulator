package pl.lechanski.kalkulator.ui.database;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DietsDB {
    private static Map<String, Integer> dietsIndex;
    private static Integer selectedDietCalories;

    static {
        dietsIndex = new HashMap<>();
        dietsIndex.put("Dieta 1", 2000);
        dietsIndex.put("Dieta 2", 2500);
        dietsIndex.put("Dieta 3", 3000);

        selectedDietCalories = dietsIndex.get("Dieta 1");
    }

    public static List<String> getDiets() {
        return new LinkedList<>(dietsIndex.keySet());
    }

    public static Integer getSelectedDietCalories() {
        return selectedDietCalories;
    }

    public static void setSelectedDietCalories(Integer calories) {
        selectedDietCalories = calories;
    }

    public static Integer getCalories(String dietName) {
        return dietsIndex.get(dietName);
    }
}
