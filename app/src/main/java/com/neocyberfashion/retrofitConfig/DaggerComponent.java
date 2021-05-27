package com.neocyberfashion.retrofitConfig;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import javax.inject.Singleton;
import dagger.Component;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Component(modules = DaggerModule.class)
@Singleton
public interface DaggerComponent {
    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
    Gson gson();
    Cache cache();
    JwtAuthenticationInterceptor headerInterceptor();
}
