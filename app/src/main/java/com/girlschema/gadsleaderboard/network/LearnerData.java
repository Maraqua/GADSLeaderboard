package com.girlschema.gadsleaderboard.network;


import com.girlschema.gadsleaderboard.model.LearnerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearnerData {
    @GET("/api/hours")
    Call<List<LearnerModel>> getLearnerList();
}
