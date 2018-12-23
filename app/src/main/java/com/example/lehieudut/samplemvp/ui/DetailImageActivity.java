package com.example.lehieudut.samplemvp.ui;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.lehieudut.samplemvp.R;
import com.example.lehieudut.samplemvp.base.BaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by lehieudut on 3/1/18.
 */
@EActivity(R.layout.activity_image_detail)
public class DetailImageActivity extends BaseActivity{

    @ViewById
    ImageView mImageView;

    @Extra
    String mUrl;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void afterView() {
        showLoading();
        Glide.with(this).load(mUrl).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                dismissLoading();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                dismissLoading();
                return false;
            }
        }).into(mImageView);
    }
}
