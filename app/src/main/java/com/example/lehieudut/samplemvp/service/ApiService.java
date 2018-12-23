package com.example.lehieudut.samplemvp.service;


import com.example.lehieudut.samplemvp.service.response.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("images/latest")
    Call<DataResponse> getListImage();
}
