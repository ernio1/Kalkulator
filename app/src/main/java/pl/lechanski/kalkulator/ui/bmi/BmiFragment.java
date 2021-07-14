package pl.lechanski.kalkulator.ui.bmi;

import android.os.Bundle;
import android.util.Range;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.math.BigDecimal;

import pl.lechanski.kalkulator.databinding.FragmentBmiBinding;

public class BmiFragment extends Fragment {

    private BmiViewModel bmiViewModel;
    private FragmentBmiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        bmiViewModel = new ViewModelProvider(this).get(BmiViewModel.class);

        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textViewResult = binding.bmiResult;
        bmiViewModel.getResult().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewResult.setText(s);
            }
        });

        final TextView textViewDescription = binding.bmiDescription;
        bmiViewModel.getDescription().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewDescription.setText(s);
            }
        });

        binding.calculateButton.setOnClickListener(this::calculateButtonOnClick);

        return root;
    }

    // obliczenia bmi i opisu
    public void calculateButtonOnClick(View view) {
        try {
            double age = Double.parseDouble(binding.ageInput.getText().toString());
            double height = Double.parseDouble(binding.heightInput.getText().toString());
            double weight = Double.parseDouble(binding.weightInput.getText().toString());

            double bmi = weight / (height * height);

            bmiViewModel.setResult(bmi+"");
            bmiViewModel.setDescription(prepareDescription(bmi, age));

        } catch (Exception e) {
            Toast.makeText(getContext(), "Wprowadzono niepoprawne dane do obliczenia BMI :(", Toast.LENGTH_LONG).show();
        }
    }

    // tworzenie opisu bmi
    private String prepareDescription(double bmi, double age) {
        Range<Integer> bmiRange = getBmiRange(age);

        if (bmi < bmiRange.getLower())
            return "BMI poniżej normy";

        if (bmi > bmiRange.getUpper())
            return "BMI powyżej normy";

        return "BMI w normie";
    }

    //Zwracanie zakresu bmi dla danego wieku
    private Range<Integer> getBmiRange(double age) {
        if (age <= 24) return Range.create(19, 24);
        if (age <= 34) return Range.create(20, 25);
        if (age <= 44) return Range.create(21, 26);
        if (age <= 54) return Range.create(22, 27);
        if (age <= 64) return Range.create(23, 28);
        return Range.create(24, 29);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}