package com.example.lehieudut.samplemvp.base;

import com.example.lehieudut.samplemvp.service.response.Meta;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by lehieudut on 2/28/18.
 */
@Data
public class BaseResponse {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("meta")
    private Meta meta;
    @SerializedName("error")
    private int error;
}
