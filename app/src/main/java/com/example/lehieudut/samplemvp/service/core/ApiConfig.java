package com.example.lehieudut.samplemvp.service.core;

import android.content.Context;

import lombok.Builder;
import lombok.Data;

/**
 * Created by lehieudut on 2/27/18.
 */
@Data
@Builder
public class ApiConfig {
    private Context context;
    private String baseUrl;
    private String auth;
}
