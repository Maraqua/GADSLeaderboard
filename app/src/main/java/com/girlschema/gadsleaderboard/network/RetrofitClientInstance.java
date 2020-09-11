package com.girlschema.gadsleaderboard.network;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {


    public static  RetrofitClientInstance sRetrofitClientInstance;
    private  Retrofit getRetrofit = null;
    private  Retrofit postRetrofit = null;
    private static final String HTTPS_GADSAPI_HEROKUAPP = "https://gadsapi.herokuapp.com";
    private static final String HTTPS_DOCS_GOOGLE_COM_FORMS = "https://docs.google.com/forms/d/e/";

    public  static  RetrofitClientInstance getRetrofitInstance(){
        if (sRetrofitClientInstance == null){
            sRetrofitClientInstance = new RetrofitClientInstance();
        }
        return sRetrofitClientInstance;

    }
    public Retrofit getClient() {
        return getClient(null);
    }

    public Retrofit postClient() {
        return postClient(null);
    }
    private Retrofit getClient(final  Context context){

        getRetrofit = new Retrofit.Builder()
                .baseUrl(HTTPS_GADSAPI_HEROKUAPP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return getRetrofit;
    }
    private Retrofit postClient(final  Context context){

        postRetrofit = new Retrofit.Builder()
                .baseUrl(HTTPS_DOCS_GOOGLE_COM_FORMS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return postRetrofit;
    }
}
