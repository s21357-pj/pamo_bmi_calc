package com.example.myapplication.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {
    private val mText: MutableLiveData<String> = MutableLiveData()

    init {
        mText.value = "This is notifications fragment"
    }

    val text: LiveData<String>
        get() = mText
}