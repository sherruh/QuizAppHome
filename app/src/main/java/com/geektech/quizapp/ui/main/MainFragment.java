package com.geektech.quizapp.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.ui.widgets.SeekBarChangeListener;
import com.geektech.quizapp.ui.quiz.QuizActivity;


public class MainFragment extends Fragment {

    public static MainFragment newInstance(){ return new MainFragment();}

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
        seekBarAmount.setOnSeekBarChangeListener(new SeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) progress = 1;
                seekBarAmount.setProgress(progress);
                textAmount.setText(String.valueOf(progress));
            }
        });
        buttonStart = view.findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.start(getActivity(),seekBarAmount.getProgress());
            }
        });
    }

}