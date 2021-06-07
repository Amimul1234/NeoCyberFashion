package com.neocyberfashion.networkConfig;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/api/v1/user/hello")
    Call<ResponseBody> getHello();
}
