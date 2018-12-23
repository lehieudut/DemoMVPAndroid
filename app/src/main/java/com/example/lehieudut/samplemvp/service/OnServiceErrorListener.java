package com.example.lehieudut.samplemvp.service;


import com.example.lehieudut.samplemvp.service.core.ApiError;

public interface OnServiceErrorListener {
    void onNetworkError();

    void onAuthenticationError();

    void onAppError(ApiError apiError);

    void onRequestConnectTimeout();
}
