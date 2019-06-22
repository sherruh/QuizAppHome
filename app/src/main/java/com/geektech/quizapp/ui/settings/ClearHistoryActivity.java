package com.geektech.quizapp.ui.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.utils.Toaster;

import java.io.Serializable;

public class ClearHistoryActivity extends AppCompatActivity {


    public static void start(Context context){
        Intent intent = new Intent(context,ClearHistoryActivity.class);
        context.startActivity(intent);
    }

    private SettingsViewModel settingsViewModel;
    private TextView textClear;
    private TextView textFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_history);

        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        settingsViewModel.deleteAllEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                if (integer == null) return;
                if (integer == 0) Toaster.showMessage(ClearHistoryActivity.this,"History is empty");
                if (integer > 0) Toaster.showMessage(ClearHistoryActivity.this,"History cleared");

            }
        });

        textClear = findViewById(R.id.clear_history_clear);
        textClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsViewModel.clearHistory();
            }
        });

        textFinish = findViewById(R.id.clear_history_finish);
        textFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
