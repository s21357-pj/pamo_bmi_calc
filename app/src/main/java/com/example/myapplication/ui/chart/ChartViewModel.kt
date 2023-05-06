package com.example.myapplication.ui.chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChartViewModel : ViewModel() {
    private val mText: MutableLiveData<String>

    init {
        mText = MutableLiveData()
        mText.value = "This is notifications fragment"
    }

    val text: LiveData<String>
        get() = mText
}