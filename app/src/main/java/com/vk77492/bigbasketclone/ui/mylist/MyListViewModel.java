package com.vk77492.bigbasketclone.ui.mylist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My List fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}