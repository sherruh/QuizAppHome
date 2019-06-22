package com.geektech.quizapp.ui.settings;

import android.os.Parcelable;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.App;
import com.geektech.quizapp.core.SingleLiveEvent;

import java.io.Serializable;

public class SettingsViewModel extends ViewModel  {

    SingleLiveEvent<Integer> deleteAllEvent = new SingleLiveEvent<>();

    public void clearHistory(){

         deleteAllEvent.showDeleteResult(App.historyStorage.deleteAll());
         deleteAllEvent.showDeleteResult(null);

    }

}
