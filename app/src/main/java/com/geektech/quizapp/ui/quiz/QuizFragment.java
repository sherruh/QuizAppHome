package com.geektech.quizapp.ui.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.geektech.quizapp.R;


public class QuizFragment extends Fragment {

    public static QuizFragment newInstance(){ return new QuizFragment();}

    private Button buttonStart;
    private SeekBar seekBarAmount;
    private TextView textAmount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textAmount = view.findViewById(R.id.text_amount_of_questions);
        seekBarAmount = view.findViewById(R.id.seekbar_amount_of_questions);
        seekBarAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("ololo", String.valueOf(seekBar.getProgress()));
                textAmount.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        buttonStart = view.findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
