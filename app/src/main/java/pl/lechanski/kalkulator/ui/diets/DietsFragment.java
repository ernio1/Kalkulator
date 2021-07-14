package pl.lechanski.kalkulator.ui.diets;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.lechanski.kalkulator.R;
import pl.lechanski.kalkulator.databinding.DietsFragmentBinding;
import pl.lechanski.kalkulator.databinding.FragmentBmiBinding;
import pl.lechanski.kalkulator.databinding.FragmentCalculatorBinding;
import pl.lechanski.kalkulator.ui.bmi.BmiFragment;
import pl.lechanski.kalkulator.ui.calculator.CalculatorFragment;
import pl.lechanski.kalkulator.ui.database.DietsDB;
import pl.lechanski.kalkulator.ui.database.FoodDB;

public class DietsFragment extends Fragment {

    private DietsFragmentBinding binding;
    private List<Spinner> spinners;

    public static DietsFragment newInstance() {
        return new DietsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DietsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spinners = Arrays.asList( binding.spinner2, binding.spinner3, binding.spinner4,
                binding.spinner5, binding.spinner6, binding.spinner7);

        attachDataToFoodSpinners();

        binding.calcCaloriesButton.setOnClickListener(this::calculateButtonOnClick);
        binding.pickDietButton.setOnClickListener(this::openDietsPicker);
        binding.dishesProposition.setOnClickListener(this::openDishesPropositions);

        binding.dietDifferenceAmount.setText(DietsDB.getSelectedDietCalories()+"");

        return root;
    }

    private void attachDataToFoodSpinners() {
        for (Spinner spinner : spinners) {
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, FoodDB.getFoodVariants());
            spinner.setAdapter(spinnerArrayAdapter);
        }
    }

    // obliczenia kalorii
    public void calculateButtonOnClick(View view) {
        try {
            List<TextView> amounts = Arrays.asList( binding.amount2, binding.amount3, binding.amount4,
                    binding.amount5, binding.amount6, binding.amount7);

            double sum = 0;

            for (int i = 0; i < spinners.size(); i++) {
                double amount = computeCaloriesPerFoodWithAmount(amounts.get(i), spinners.get(i));
                sum += amount;
            }

            binding.caloriesSum.setText(sum+"");
            binding.dietDifferenceAmount.setText(((int)Math.max(0, (DietsDB.getSelectedDietCalories() - sum)))+"");

        } catch (Exception e) {
            Toast.makeText(getContext(), "Wprowadzono niepoprawne dane do obliczenia kalorii :(", Toast.LENGTH_LONG).show();
        }
    }

    // oblicznie kalorii dla danego dania ze spinnera i podanej ilości
    private double computeCaloriesPerFoodWithAmount(TextView amountView, Spinner spinner) {
        try {
            String food = spinner.getSelectedItem().toString();
            Integer calories = FoodDB.getCalories(food);
            double amount = Double.parseDouble(amountView.getText().toString());

            return calories * amount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // otworzenie ekranu z wyborem diety
    public void openDietsPicker(View view) {
        PickDietFragment pickDietFragment = PickDietFragment.newInstance();
        FragmentManager manager = getParentFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main,pickDietFragment,"pickDietFragment");
        transaction.addToBackStack("pickDietFragment");
        transaction.commit();
    }

    // otworzenie ekranu z propozycjami diety
    public void openDishesPropositions(View view) {
        DishesPropositionFragment dishesPropositionFragment = DishesPropositionFragment.newInstance();
        FragmentManager manager = getParentFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main,dishesPropositionFragment,"dishesPropositionFragment");
        transaction.addToBackStack("dishesPropositionFragment");
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}