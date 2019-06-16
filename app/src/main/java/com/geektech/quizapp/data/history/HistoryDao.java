package com.geektech.quizapp.data.history;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.geektech.quizapp.model.QuizResult;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    long insert(QuizResult result);

    @Query("SELECT * FROM quiz_result WHERE id=:id")
    QuizResult get(long id);

    @Query("SELECT * FROM quiz_result")
    LiveData<List<QuizResult>> getAll();
}
