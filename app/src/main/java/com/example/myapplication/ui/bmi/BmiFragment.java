package com.example.myapplication.ui.bmi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentBmiBinding;


public class BmiFragment extends Fragment {

    private FragmentBmiBinding binding;
    private int waga = 0; // bill amount entered by the user
    private Double wzrost = 0.0; // initial tip percentage
    private TextView wzrostTextView; // shows formatted bill amount
    private TextView wagaTextView; // shows formatted bill amount
    private TextView totalTextView; // shows calculated total bill amount
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BmiViewModel bmiViewModel =
                new ViewModelProvider(this).get(BmiViewModel.class);

        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        wzrostTextView = binding.wzrostinput;
        wagaTextView = binding.wagainput;
        totalTextView = binding.totalTextView;

        // set amountEditText's TextWatcher
        wzrostTextView.addTextChangedListener(wzrostEditTextWatcher);
        wagaTextView.addTextChangedListener(wagaEditTextWatcher);
        return root;
    }

    private final TextWatcher wzrostEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get bill amount and display currency formatted value
                wzrost = Double.parseDouble(s.toString()) / 100;
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                wzrostTextView.setText("0");
                wzrost = 0.0;
            }

            calculate(); // update the tip and total TextViews
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    private void calculate() {
        // calculate the tip and total
        double bmi;
        if (wzrost != 0.0 && waga !=0) {
            bmi = waga / (wzrost * wzrost);
            totalTextView.setText(Double.toString(bmi));
        }
        // display tip and total formatted as currency

    }

    private final TextWatcher wagaEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get bill amount and display currency formatted value
                waga = Integer.parseInt(s.toString());
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                wagaTextView.setText("0");
                waga = 0;
            }

            calculate(); // update the tip and total TextViews
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}