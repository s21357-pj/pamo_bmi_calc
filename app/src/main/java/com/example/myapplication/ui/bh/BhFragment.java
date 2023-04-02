package com.example.myapplication.ui.bh;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentBhBinding;
import com.example.myapplication.databinding.FragmentNotificationsBinding;

public class BhFragment extends Fragment {

    private FragmentBhBinding binding;



    private int waga = 0; // bill amount entered by the user
    private Double wzrost = 0.0; // initial tip percentage
    private String gender = "";
    private int wiek = 0; // initial tip percentage
    private TextView wzrostTextView;
    private TextView totalTextView;
    private TextView resultTextView;
    private TextView wagaTextView;
    private TextView wiekTextView;
    private AutoCompleteTextView genderTextView; // shows formatted bill amount

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBhBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        wzrostTextView = binding.wzrostinputBh;
        wagaTextView = binding.wzrostinputBh;
        totalTextView = binding.totalTextViewBh;
        wiekTextView = binding.wiekinputBh;
        genderTextView = binding.genderFiled;
        // set amountEditText's TextWatcher
        wzrostTextView.addTextChangedListener(wzrostEditTextWatcher);
        wagaTextView.addTextChangedListener(wagaEditTextWatcher);
        wiekTextView.addTextChangedListener(wiekEditTextWatcher);
        genderTextView.addTextChangedListener(genderEditTextWatcher);

        //final TextView textView = binding.textHome;
        //bhViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
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

    private final TextWatcher genderEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            gender = s.toString();
            calculate(); // update the tip and total TextViews
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
    private final TextWatcher wiekEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get bill amount and display currency formatted value
                wiek = Integer.parseInt(s.toString());
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                wiekTextView.setText("0");
                wiek = 0;
            }

            calculate(); // update the tip and total TextViews
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

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
        double index;
        if (gender != "" && wzrost != 0.0 && waga !=0 && wiek != 0) {
            if (gender.equals("Mężczyzna")) {
                index = 66.47 + (13.7 * waga) + (5.0 * wzrost) - (6.76 * wiek);
                totalTextView.setText(Double.toString(index));
            }
            if (gender.equals("Kobieta")) {
                index = 655.1 + (9.567 * waga) + (1.85 * wzrost) - (4.68 * wiek);
                totalTextView.setText(Double.toString(index));
            }

        }
        // display tip and total formatted as currency

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}