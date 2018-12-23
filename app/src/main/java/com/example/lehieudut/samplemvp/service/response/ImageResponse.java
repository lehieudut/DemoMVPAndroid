package com.example.lehieudut.samplemvp.service.response;

import io.realm.RealmObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by lehieudut on 2/28/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ImageResponse extends RealmObject{
    private int id;
    private String url;
    private Byte[] mByteArray;

    public ImageResponse(){

    }
}
