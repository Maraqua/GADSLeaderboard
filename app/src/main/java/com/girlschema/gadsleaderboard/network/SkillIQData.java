package com.girlschema.gadsleaderboard.network;

import com.girlschema.gadsleaderboard.model.SkillIQModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillIQData {
    @GET("/api/skilliq")
    Call<List<SkillIQModel>>getSkillIQList();
}
