package com.geektech.quizapp.data;

import com.geektech.quizapp.model.Question;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionsResponse {
    @SerializedName("response_code")
    private int responseCode;
    private List<Question> results;

    public QuestionsResponse(int responseCode, List<Question> results) {
        this.responseCode = responseCode;
        this.results = results;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }
}
