package com.girlschema.gadsleaderboard.network;

import com.girlschema.gadsleaderboard.model.LearnerModel;
import com.girlschema.gadsleaderboard.model.SkillIQModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("/api/hours")
    Call<List<LearnerModel>> getLearnerList();
    @GET("/api/skilliq")
    Call<List<SkillIQModel>>getSkillIQList();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponses1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponses")
    Call<ResponseBody> submitProjectForm(
            @Field("entry.1877115667")String fname,
            @Field("entry.2006916086")String lname,
            @Field("entry.1824927963")String emailAddress,
            @Field("entry.284483984")String linkToProject
    );



}
