package pl.lechanski.kalkulator.ui.bmi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BmiViewModel extends ViewModel {

    private MutableLiveData<String> description;
    private MutableLiveData<String> result;

    public BmiViewModel() {
        description = new MutableLiveData<>();
        result = new MutableLiveData<>();
    }

    public void setDescription(String value) {
        description.setValue(value);
    }

    public void setResult(String value) {
        result.setValue(value);
    }

    public LiveData<String> getDescription() {
        return description;
    }

    public LiveData<String> getResult() {
        return result;
    }
}