package pl.lechanski.kalkulator.ui.diets;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import pl.lechanski.kalkulator.R;
import pl.lechanski.kalkulator.databinding.FragmentDishesPropositionBinding;
import pl.lechanski.kalkulator.databinding.FragmentPickDietBinding;
import pl.lechanski.kalkulator.ui.database.DietsDB;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DishesPropositionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DishesPropositionFragment extends Fragment {

    FragmentDishesPropositionBinding binding;

    public DishesPropositionFragment() {
        // Required empty public constructor
    }

    public static DishesPropositionFragment newInstance() {
        DishesPropositionFragment fragment = new DishesPropositionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDishesPropositionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.backButton.setOnClickListener(this::goBack);

        setLinksInTextViews((ConstraintLayout) root);

        return root;
    }

    // ustawianie linkow we wszystkich textview
    private void setLinksInTextViews(ConstraintLayout root) {
        for (int i = 0; i < 8; i+=2) {
            ConstraintLayout includedLayout = (ConstraintLayout)((LinearLayout) ((ScrollView) root.getChildAt(1)).getChildAt(0)).getChildAt(i);
            for (int j = 0; j < includedLayout.getChildCount(); j++) {
                View childAt = includedLayout.getChildAt(j);
                if (childAt instanceof TextView){
                    ((TextView)childAt).setMovementMethod(LinkMovementMethod.getInstance());
                }
            }
        }
    }

    // powrót
    public void goBack(View view) {
        getFragmentManager().popBackStack();
    }
}