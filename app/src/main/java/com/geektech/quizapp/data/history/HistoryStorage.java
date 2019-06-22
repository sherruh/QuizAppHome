package com.geektech.quizapp.data.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.geektech.quizapp.model.QuizResult;
import com.geektech.quizapp.model.ShortQuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    private HistoryDao dao;

    public HistoryStorage(HistoryDao historyDao){
        dao = historyDao;
    }

    @Override
    public long saveQuizResult(QuizResult result) {
        return dao.insert(result);
    }

    @Override
    public QuizResult getQuizResult(long id) {
        return dao.get(id);
    }

    @Override
    public void deleteQuizResult(long id) {
        dao.delete(id);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return dao.getAll();
    }

    @Override
    public LiveData<List<ShortQuizResult>> getAllShort() {
        return Transformations.map(dao.getAll(), quizResults -> {
            ArrayList<ShortQuizResult> shortQuizResults = new ArrayList<>();

            for (QuizResult quizResult : quizResults) {
                shortQuizResults.add(new ShortQuizResult(
                        quizResult.getId(),
                        quizResult.getQuestions().size(),
                        quizResult.getCorrectAnswers(),
                        quizResult.getCreatedAt(),
                        quizResult.getCategory(),
                        quizResult.getDifficulty()
                ));
            }

            return shortQuizResults;
        });
    }

    @Override
    public int deleteAll() {
        return(dao.deleteAll());
    }
}
