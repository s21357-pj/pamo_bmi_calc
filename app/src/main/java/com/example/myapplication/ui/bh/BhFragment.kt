package com.example.myapplication.ui.bh

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentBhBinding

class BhFragment : Fragment() {
    private var binding: FragmentBhBinding? = null
    private var waga = 0 // bill amount entered by the user
    private var wzrost = 0.0 // initial tip percentage
    private var gender = ""
    private var wiek = 0 // initial tip percentage
    private var wzrostTextView: TextView? = null
    private var totalTextView: TextView? = null
    private val resultTextView: TextView? = null
    private var wagaTextView: TextView? = null
    private var wiekTextView: TextView? = null
    private var genderTextView // shows formatted bill amount
            : AutoCompleteTextView? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBhBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        wzrostTextView = binding!!.wzrostinputBh
        wagaTextView = binding!!.wzrostinputBh
        totalTextView = binding!!.totalTextViewBh
        wiekTextView = binding!!.wiekinputBh
        genderTextView = binding!!.genderFiled
        // set amountEditText's TextWatcher
        (wzrostTextView as EditText).addTextChangedListener(wzrostEditTextWatcher)
        (wagaTextView as EditText).addTextChangedListener(wagaEditTextWatcher)
        (wiekTextView as EditText).addTextChangedListener(wiekEditTextWatcher)
        genderTextView!!.addTextChangedListener(genderEditTextWatcher)

        //final TextView textView = binding.textHome;
        //bhViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root
    }

    private val wagaEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the bill amount
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try { // get bill amount and display currency formatted value
                waga = s.toString().toInt()
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                wagaTextView!!.text = "0"
                waga = 0
            }
            calculate() // update the tip and total TextViews
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
        }
    }
    private val genderEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the bill amount
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            gender = s.toString()
            calculate() // update the tip and total TextViews
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
        }
    }
    private val wiekEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the bill amount
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try { // get bill amount and display currency formatted value
                wiek = s.toString().toInt()
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                wiekTextView!!.text = "0"
                wiek = 0
            }
            calculate() // update the tip and total TextViews
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
        }
    }
    private val wzrostEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the bill amount
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try { // get bill amount and display currency formatted value
                wzrost = s.toString().toDouble() / 100
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                wzrostTextView!!.text = "0"
                wzrost = 0.0
            }
            calculate() // update the tip and total TextViews
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
        }
    }

    private fun calculate() {
        // calculate the tip and total
        var index: Double
        if (gender !== "" && wzrost != 0.0 && waga != 0 && wiek != 0) {
            if (gender == "Mężczyzna") {
                index = 66.47 + 13.7 * waga + 5.0 * wzrost - 6.76 * wiek
                totalTextView!!.text = java.lang.Double.toString(index)
            }
            if (gender == "Kobieta") {
                index = 655.1 + 9.567 * waga + 1.85 * wzrost - 4.68 * wiek
                totalTextView!!.text = java.lang.Double.toString(index)
            }
        }
        // display tip and total formatted as currency
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}