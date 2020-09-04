package com.girlschema.gadsleaderboard.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit sRetrofit;
    private static final String BASE_URL = "https://gadsapi.herokuapp.com";
    public  static  Retrofit getRetrofitInstance(){
        if (sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory
                            .create())
                    .build();
        }
        return sRetrofit;
    }
}
