package com.example.myapplication.ui.bmi

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBmiBinding

class BmiFragment : Fragment(), View.OnClickListener {
    private var binding: FragmentBmiBinding? = null
    private var button2: Button? = null
    private var waga = 0 // bill amount entered by the user
    private var wzrost = 0.0 // initial tip percentage
    private var wzrostTextView // shows formatted bill amount
            : TextView? = null
    private var wagaTextView // shows formatted bill amount
            : TextView? = null
    private var totalTextView // shows calculated total bill amount
            : TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val bmiViewModel = ViewModelProvider(this).get(BmiViewModel::class.java)
        binding = FragmentBmiBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        wzrostTextView = binding!!.wzrostinput
        wagaTextView = binding!!.wagainput
        totalTextView = binding!!.totalTextView
        button2 = binding!!.button2
        // set amountEditText's TextWatcher
        (wzrostTextView as EditText).addTextChangedListener(wzrostEditTextWatcher)
        (wagaTextView as EditText).addTextChangedListener(wagaEditTextWatcher)
        button2!!.setOnClickListener(this)
        return root
    }

    override fun onClick(v: View) {
        findNavController(v).navigate(R.id.action_navigation_calculator_bmi_to_navigation_result)
    }

    private val wzrostEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the bill amount
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
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
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }

    private fun calculate() {
        // calculate the tip and total
        val bmi: Double
        if (wzrost != 0.0 && waga != 0) {
            bmi = waga / (wzrost * wzrost)
            totalTextView!!.text = java.lang.Double.toString(bmi)
        }
        // display tip and total formatted as currency
    }

    private val wagaEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the bill amount
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
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
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}