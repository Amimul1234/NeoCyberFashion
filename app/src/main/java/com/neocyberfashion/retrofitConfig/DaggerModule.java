package com.neocyberfashion.retrofitConfig;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DaggerModule {
    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache,
                                     JwtAuthenticationInterceptor interceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);
        client.cache(cache);
        return client.build();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HostAddress.HOST_ADDRESS.getHostAddress())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    JwtAuthenticationInterceptor provideHeaderInterceptor(){
        return new JwtAuthenticationInterceptor();
    }
}
