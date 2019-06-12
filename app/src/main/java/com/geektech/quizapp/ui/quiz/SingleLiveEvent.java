package com.geektech.quizapp.ui.quiz;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;

public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private static final String TAG = "SingleLiveEvent";

    @MainThread
    public void observe(LifecycleOwner owner, final Observer<T> observer) {

        super.observe(owner, t -> {
            observer.onChanged(t);
        });
    }

    @MainThread
    public void quizFinish() {
        setValue(null);
    }

}