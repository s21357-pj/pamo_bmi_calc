package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.TextView; // for displaying text

import java.text.NumberFormat; // for currency formatting

public class MainActivity extends AppCompatActivity {

    // currency and percent formatter objects
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    private int waga = 0; // bill amount entered by the user
    private Double wzrost = 0.0; // initial tip percentage
    private TextView wzrostTextView; // shows formatted bill amount
    private TextView wagaTextView; // shows formatted bill amount

    private TextView totalTextView; // shows calculated total bill amount

    // called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        // get references to programmatically manipulated TextViews
        wzrostTextView = (TextView) findViewById(R.id.wzrostinput);
        wagaTextView = (TextView) findViewById(R.id.wagainput);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        // set amountEditText's TextWatcher
        wzrostTextView.addTextChangedListener(wzrostEditTextWatcher);
        wagaTextView.addTextChangedListener(wagaEditTextWatcher);

    }

    // calculate and display tip and total amounts
    private void calculate() {
        // calculate the tip and total
        double bmi;
        if (wzrost != 0.0 && waga !=0) {
            bmi = waga / (wzrost * wzrost);
            totalTextView.setText(Double.toString(bmi));
        }
        // display tip and total formatted as currency

    }

    // listener object for the EditText's text-changed events
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
}
