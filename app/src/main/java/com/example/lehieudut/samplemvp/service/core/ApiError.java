package com.example.lehieudut.samplemvp.service.core;

import com.google.gson.annotations.SerializedName;

public class ApiError {
    private int code;
    @SerializedName("message")
    private String message;

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
