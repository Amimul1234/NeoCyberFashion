package com.neocyberfashion.networkConfig;

import java.util.concurrent.TimeUnit;
import io.paperdb.Paper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.neocyberfashion.networkConfig.HostAddress.HOST_ADDRESS;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static RetrofitClient mInstance;
    private static final String BASE_URL = HOST_ADDRESS.getHostAddress();

    private RetrofitClient()
    {
        String token = Paper.book().read("jwt");

        final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {

            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(newRequest);

        }).readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .connectTimeout(120, TimeUnit.SECONDS)
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

    public Api getApis()
    {
        return retrofit.create(Api.class);
    }
}
