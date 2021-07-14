package pl.lechanski.kalkulator.ui.calculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private MutableLiveData<String> calories;

    public CalculatorViewModel() {
        calories = new MutableLiveData<>();
    }

    public void setCalories(String value) {
        calories.setValue(value);
    }

    public LiveData<String> getCalories() {
        return calories;
    }
}