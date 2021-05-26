package com.neocyberfashion.retrofitConfig;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.neocyberfashion.retrofitConfig.HostAddress.HOST_ADDRESS;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static RetrofitClient mInstance;
    private static final String BASE_URL = HOST_ADDRESS.getHostAddress();

    private RetrofitClient()
    {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .build();



        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static RetrofitClient getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new RetrofitClient();
        }

        return mInstance;
    }

    public Api getApi()
    {
        return retrofit.create(Api.class);
    }

}
