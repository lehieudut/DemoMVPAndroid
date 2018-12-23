package com.example.lehieudut.samplemvp.service.response;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by lehieudut on 2/28/18.
 */
@Data
public class Meta {
    @SerializedName("error")
    private String error;
    @SerializedName("token")
    private String token;
    private int userId;
    private int totalItem;
    private int totalPage;
    private int currentPage;
    private int rowPerPage;
}
