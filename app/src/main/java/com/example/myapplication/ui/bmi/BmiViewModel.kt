package com.example.myapplication.ui.bmi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {
    private val mText: MutableLiveData<String>

    init {
        mText = MutableLiveData()
        mText.value = "This is BMI fragment"
    }
}