package pl.lechanski.kalkulator.ui.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import pl.lechanski.kalkulator.databinding.FragmentCalculatorBinding;
import pl.lechanski.kalkulator.ui.database.FoodDB;

public class CalculatorFragment extends Fragment {

    private CalculatorViewModel calculatorViewModel;
    private FragmentCalculatorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calculatorViewModel =
                new ViewModelProvider(this).get(CalculatorViewModel.class);

        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Spinner spinner = binding.foodPicker;

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, FoodDB.getFoodVariants());
        spinner.setAdapter(spinnerArrayAdapter);

        final TextView caloriesSumText = binding.caloriesSumText;
        calculatorViewModel.getCalories().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                caloriesSumText.setText(s);
            }
        });

        binding.calculateCaloriesButton.setOnClickListener(this::calculateButtonOnClick);

        return root;
    }

    // obliczenia kalorii
    public void calculateButtonOnClick(View view) {
        try {
            double amount = Double.parseDouble(binding.caloriesInput.getText().toString());
            String food = binding.foodPicker.getSelectedItem().toString();
            Integer calories = FoodDB.getCalories(food);

            double multipliedCalories = calories * amount;

            calculatorViewModel.setCalories(multipliedCalories+"");

        } catch (Exception e) {
            Toast.makeText(getContext(), "Wprowadzono niepoprawne dane do obliczenia kalorii :(", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}