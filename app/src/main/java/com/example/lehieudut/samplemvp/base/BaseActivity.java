package com.example.lehieudut.samplemvp.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.WindowFeature;


/**
 * Created by lehieudut on 2/28/18.
 */
@EActivity
@WindowFeature(Window.FEATURE_NO_TITLE)
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView{

    protected final String TAG = this.getClass().getSimpleName();

    protected T mPresenter;

    protected abstract void initPresenter();

    protected abstract void afterView();

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        initPresenter();
        if (mPresenter != null)
            mPresenter.attachView(this);

        intProgressBar();
    }

    @AfterViews
    protected void initView(){this.afterView();}

    /**
     * Init dialog.
     */
    private void intProgressBar() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading");
        mProgressDialog.setCancelable(false);
    }

    /**
     * dismiss message dialog
     */
    public void dismissProgressDialog() {
        if (!isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * show dialog message.
     */
    public void showProgressDialog() {
        if (mProgressDialog != null && !mProgressDialog.isShowing() && !isFinishing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void dismissLoading() {
        dismissProgressDialog();
    }

    @Override
    public void showError(String message) {
        showAlertDialog(message);
    }

    @Override
    public void showMessage(String message) {
        showAlertDialog(message);
    }

    @Override
    public void showErrorConnect() {
        showAlertDialog("No connect");
    }

    /**
     * show message dialog
     *
     * @param msg
     */
    protected void showAlertDialog(@NonNull String msg) {
        try {
            new AlertDialog.Builder(this)
                    .setMessage(msg)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
        }
        super.onDestroy();
    }
}
