package com.girlschema.gadsleaderboard.network;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {


    public static  RetrofitClientInstance sRetrofitClientInstance;
    private  Retrofit getRetrofit = null;
    private static final String HTTPS_GADSAPI_HEROKUAPP = "https://gadsapi.herokuapp.com";

    public  static  RetrofitClientInstance getRetrofitInstance(){
        if (sRetrofitClientInstance == null){
            sRetrofitClientInstance = new RetrofitClientInstance();
        }
        return sRetrofitClientInstance;

    }
    public Retrofit getClient() {
        return getClient(null);
    }

    private Retrofit getClient(final  Context context){

        getRetrofit = new Retrofit.Builder()
                .baseUrl(HTTPS_GADSAPI_HEROKUAPP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return getRetrofit;
    }

}
