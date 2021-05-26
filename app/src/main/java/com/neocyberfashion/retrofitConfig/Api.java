package com.neocyberfashion.retrofitConfig;

import com.neocyberfashion.login.AuthenticationResponse;
import com.neocyberfashion.register.UserRegistration;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("/api/v1/user/registerNewUser")
    Call<ResponseBody> registerNewUser(@Body UserRegistration userRegistration);

    @POST("/api/v1/user/authenticate")
    Call<AuthenticationResponse> authenticateUser(@Body UserRegistration userRegistration);
}
