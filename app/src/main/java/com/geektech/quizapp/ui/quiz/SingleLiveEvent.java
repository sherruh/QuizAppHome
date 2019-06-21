package com.geektech.quizapp.ui.quiz;

import android.support.v4.media.session.PlaybackStateCompat;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.annotation.MainThread;

public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private static final String TAG = "SingleLiveEvent";

    @MainThread
    public void observe(LifecycleOwner owner, final Observer<? super T> observer) {

        super.observe(owner, t -> {
            observer.onChanged(t);
        });
    }

    @MainThread
    public void sendId(@Nullable T t) {
        super.setValue(t);
    }

    @MainThread
    public void quizFinish(long id) {
        setValue(null);
    }

    @MainThread
    public void skipAnswer(int id){setValue(null);}

}