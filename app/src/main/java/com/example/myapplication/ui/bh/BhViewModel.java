package com.example.myapplication.ui.bh;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BhViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BhViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is BH fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}