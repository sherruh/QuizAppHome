package com.geektech.quizapp.ui.quiz.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Question> questions;
    OnAnswerClick mListener;

    public QuestionsAdapter(ArrayList<Question> questions, OnAnswerClick listener) {
        this.questions = questions;
        mListener = listener;
    }

    public void setData(List<Question> questions){
        this.questions.clear();
        this.questions.addAll(questions);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.view_holder_question,viewGroup,false);
        return new QuestionViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((QuestionViewHolder)viewHolder).onBind(questions.get(i));
    }


    @Override
    public int getItemCount() {
        return questions.size();
    }

    public interface OnAnswerClick {
        void onClick(int answerPostition, int adapterPosition);
    }

}
