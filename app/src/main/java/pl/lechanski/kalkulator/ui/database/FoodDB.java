package pl.lechanski.kalkulator.ui.database;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FoodDB {
    private static Map<String, Integer> foodCaloriesIndex;

    static {
        foodCaloriesIndex = new HashMap<>();
        foodCaloriesIndex.put("Omlet", 96);
        foodCaloriesIndex.put("Domowe musli", 410);
        foodCaloriesIndex.put("Gruszkowo-jabłkowa owsianka", 160);
        foodCaloriesIndex.put("Malinowa owsianka", 103);
        foodCaloriesIndex.put("Dietetyczne curry z kurczaka z ryżem", 144);
        foodCaloriesIndex.put("Krem z kukurydzy ", 44);
        foodCaloriesIndex.put("Chipsy marchewkowe ", 81);
        foodCaloriesIndex.put("Wiosenna botwinka ", 25);
        foodCaloriesIndex.put("Muffinki jajeczne ze szpiankiem", 124);
        foodCaloriesIndex.put("Smoothie z bananem, mango i szpinakiem", 44);
        foodCaloriesIndex.put("Placuszki owsiane z owocami ", 147);
        foodCaloriesIndex.put("Szybka chrupiąca sałatka z kiełkami i serem feta", 69);
        foodCaloriesIndex.put("Zupa meksykańska z nutką chilli", 35);
        foodCaloriesIndex.put("Pieczona pierś z kurczaka z suszonymi pomidorami i mozzarellą", 108);
        foodCaloriesIndex.put("Marchewkowe frytki ", 66);
        foodCaloriesIndex.put("Sałatka z pomidorkami koktailowymi i pysznym kremowym sosem", 38);
        foodCaloriesIndex.put("Placuszki owsiane ", 164);
        foodCaloriesIndex.put("Budyń z owocami", 121);
        foodCaloriesIndex.put("Kanapka z awokado", 175);
        foodCaloriesIndex.put("Ryż z warzywami ", 109);
        foodCaloriesIndex.put("Deser jabłkowy z orzechami ", 100);
        foodCaloriesIndex.put("Jogurt z migdałami", 79);
        foodCaloriesIndex.put("Sałatka", 38);
        foodCaloriesIndex.put("Naleśniki ", 107);
        foodCaloriesIndex.put("Placuszki owsiane z dżemem ", 216);
        foodCaloriesIndex.put("Jogurt z borówkami i migdałami ", 79);
        foodCaloriesIndex.put("Indyjskie orzeźwienie - mango lassi", 70);
        foodCaloriesIndex.put("Jajecznica z łososiem", 197);
        foodCaloriesIndex.put("Zupa rybna z makaronem", 53);
        foodCaloriesIndex.put("Tuńczyk z w sosie pesto z bazylii", 115);
        foodCaloriesIndex.put("Serek wiejski z jabłkiem", 78);
        foodCaloriesIndex.put("Warzywna tortilla z orzechami nerkowca", 102);




    }

    public static Integer getCalories(String foodName) {
        return foodCaloriesIndex.get(foodName);
    }

    public static List<String> getFoodVariants() {
        return new LinkedList<>(foodCaloriesIndex.keySet());
    }
}
