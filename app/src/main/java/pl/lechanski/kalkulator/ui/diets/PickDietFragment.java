package pl.lechanski.kalkulator.ui.diets;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import pl.lechanski.kalkulator.MainActivity;
import pl.lechanski.kalkulator.R;
import pl.lechanski.kalkulator.databinding.DietsFragmentBinding;
import pl.lechanski.kalkulator.databinding.FragmentPickDietBinding;
import pl.lechanski.kalkulator.ui.database.DietsDB;
import pl.lechanski.kalkulator.ui.database.FoodDB;

public class PickDietFragment extends Fragment {

    FragmentPickDietBinding binding;

    public PickDietFragment() {
        // Required empty public constructor
    }

    public static PickDietFragment newInstance() {
        PickDietFragment fragment = new PickDietFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPickDietBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Spinner spinner = binding.dietSpinner;

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, DietsDB.getDiets());
        spinner.setAdapter(spinnerArrayAdapter);

        binding.pickDiet.setOnClickListener(this::pickDiet);

        return root;
    }

    // wybieranie diety
    public void pickDiet(View view) {
        try {
            String diet = binding.dietSpinner.getSelectedItem().toString();
            Integer calories = DietsDB.getCalories(diet);
            DietsDB.setSelectedDietCalories(calories);

            getFragmentManager().popBackStack();

        } catch (Exception e) {
            Toast.makeText(getContext(), "Wprowadzono niepoprawne dane do wyboru diety :(", Toast.LENGTH_LONG).show();
        }
    }
}