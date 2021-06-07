package com.neocyberfashion.networkConfig.retrofitAuth;

import com.neocyberfashion.networkConfig.AuthApi;
import com.neocyberfashion.networkConfig.HostAddress;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAuthClient {

    private static Retrofit retrofit = null;
    private static RetrofitAuthClient retrofitAuthClient;

    private RetrofitAuthClient() {

        String hostAddress = HostAddress.HOST_ADDRESS.getHostAddress();

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(hostAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static RetrofitAuthClient getRetrofitAuthClient() {
        if(retrofitAuthClient == null)
            retrofitAuthClient = new RetrofitAuthClient();

        return retrofitAuthClient;
    }

    public AuthApi getAuthApis()
    {
        return retrofit.create(AuthApi.class);
    }
}
