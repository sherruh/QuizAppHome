package com.geektech.quizapp.ui.history;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.ShortQuizResult;
import com.geektech.quizapp.ui.history.recycler.HistoryAdapter;
import com.geektech.quizapp.ui.history.recycler.HistoryViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    private HistoryViewModel historyViewModel;
    private RecyclerView recyclerHistory;
    private HistoryAdapter adapterHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        historyViewModel.history.observe(this, new Observer<List<ShortQuizResult>>() {
            @Override
            public void onChanged(List<ShortQuizResult> shortQuizResults) {
                adapterHistory.setQuizResults(shortQuizResults);
            }
        });
    }

    private void init(View view) {
        adapterHistory = new HistoryAdapter(new ArrayList<>());

        recyclerHistory = view.findViewById(R.id.history_recycler);
        recyclerHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerHistory.setAdapter(adapterHistory);

    }
}
