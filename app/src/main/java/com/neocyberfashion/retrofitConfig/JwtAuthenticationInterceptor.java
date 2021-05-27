package com.neocyberfashion.retrofitConfig;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class JwtAuthenticationInterceptor implements Interceptor {

    private String jwtToken;

    @Inject
    public JwtAuthenticationInterceptor() { }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization","Bearer " +jwtToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}