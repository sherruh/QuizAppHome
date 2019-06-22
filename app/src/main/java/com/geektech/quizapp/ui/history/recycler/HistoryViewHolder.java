package com.geektech.quizapp.ui.history.recycler;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.ShortQuizResult;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    private TextView textCategory;
    private TextView textAnswerStats;
    private TextView textDifficulty;
    private TextView textDate;
    private ImageView imageOptions;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        textCategory = itemView.findViewById(R.id.item_history_category);
        textAnswerStats = itemView.findViewById(R.id.item_history_correct_amount);
        textDifficulty = itemView.findViewById(R.id.item_history_difficulty);
        textDate = itemView.findViewById(R.id.item_history_date);
        imageOptions = itemView.findViewById(R.id.item_history_dots);
    }

    public void onBind(ShortQuizResult shortQuizResult) {

        textCategory.setText("Category: " + shortQuizResult.getCategory());
        textDifficulty.setText("Difficulty: " + shortQuizResult.getDifficculty());
        textAnswerStats.setText("Correct answers: " + shortQuizResult.getCorrectAnswers() + "/" + shortQuizResult.getQuestionsAmount());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        textDate.setText( simpleDateFormat.format(shortQuizResult.getCreatedAt()));
    }

    public interface OnHistoryOptionsClick{

        void OnClick();

    }
}
