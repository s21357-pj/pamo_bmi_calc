package com.example.myapplication.ui.bh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BhViewModel : ViewModel() {
    private val mText: MutableLiveData<String>

    init {
        mText = MutableLiveData()
        mText.value = "This is BH fragment"
    }

    val text: LiveData<String>
        get() = mText
}