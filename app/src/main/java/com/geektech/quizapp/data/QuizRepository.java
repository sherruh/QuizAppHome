package com.geektech.quizapp.data;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizRepository implements IQuizRepository {

    private static final String BASE_URL = "https://opentdb.com";

    private QuizNetworkClient client = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizNetworkClient.class);

    @Override
    public void getQuestions(int amount,String type, QuestionsCallback callback) {
        Call<QuestionsResponse> call = client.getQuestions(amount,type);

        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Call<QuestionsResponse> call, Response<QuestionsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body().getResults());
                    } else {
                        callback.onFailure("Body is empty");
                    }
                } else {
                    callback.onFailure("Response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<QuestionsResponse> call, Throwable t) {
                callback.onFailure("Failure " + t.getMessage());
            }
        });
    }

    private interface QuizNetworkClient {

        @GET("/api.php")
        Call<QuestionsResponse> getQuestions(
                @Query("amount") int amount,
                @Query("type") String type
        );

    }
}
