// 
// Decompiled by Procyon v0.5.36
// 

package id.git.utils;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import id.git.model.Param;
import retrofit2.Retrofit;

public class RetrofitInstance
{
    private static Retrofit retrofit;
    
    public static Retrofit getRetrofitClient() {
        System.out.println(Param.getUrl());
        if (RetrofitInstance.retrofit == null) {
            RetrofitInstance.retrofit = new Retrofit.Builder().baseUrl(Param.getUrl()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).client(new OkHttpClient().newBuilder().addInterceptor((Interceptor)new MyIntercepter()).build()).build();
        }
        return RetrofitInstance.retrofit;
    }
}
