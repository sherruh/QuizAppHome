package com.geektech.quizapp.ui.history.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.ShortQuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ShortQuizResult> mQuizResults;

    public HistoryAdapter(List<ShortQuizResult> quizResults) {
        mQuizResults = quizResults;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_hoolder_history,parent,false);
        return new HistoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HistoryViewHolder) ((HistoryViewHolder)holder).onBind(mQuizResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuizResults.size();
    }

    public void setQuizResults(List<ShortQuizResult> quizResults){
        mQuizResults.clear();
        mQuizResults.addAll(quizResults);
        notifyDataSetChanged();
    }
}
