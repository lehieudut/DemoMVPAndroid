package com.example.lehieudut.samplemvp.service.response;

import com.example.lehieudut.samplemvp.base.BaseResponse;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by lehieudut on 2/27/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataResponse extends BaseResponse{
    private List<ImageResponse> images;
}
