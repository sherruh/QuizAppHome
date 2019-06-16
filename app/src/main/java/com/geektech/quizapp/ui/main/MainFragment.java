package com.geektech.quizapp.ui.main;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.ui.widgets.OnItemSelectedListener;
import com.geektech.quizapp.ui.widgets.SeekBarChangeListener;
import com.geektech.quizapp.ui.quiz.QuizActivity;


public class MainFragment extends Fragment {

    public static MainFragment newInstance(){ return new MainFragment();}

    private CardView buttonStart;
    private SeekBar seekBarAmount;
    private TextView textAmount;
    private AppCompatSpinner spinnerDifficulty;
    private String difficulty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        difficulty = "";
        textAmount = view.findViewById(R.id.text_amount_of_questions);
        spinnerDifficulty = view.findViewById(R.id.main_difficulty_spinner);
        spinnerDifficulty.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        difficulty = "easy";
                        break;
                    case 2:
                        difficulty = "medium";
                        break;
                    case 3:
                        difficulty = "hard";
                }
            }
        });
        seekBarAmount = view.findViewById(R.id.seekbar_amount_of_questions);
        seekBarAmount.setOnSeekBarChangeListener(new SeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) progress = 1;
                seekBarAmount.setProgress(progress);
                textAmount.setText(String.valueOf(progress));
            }
        });
        buttonStart = view.findViewById(R.id.text_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.start(getActivity(),seekBarAmount.getProgress(),difficulty);
            }
        });
    }

}
