package com.example.lehieudut.samplemvp.base;

/**
 * Created by lehieudut on 2/28/18.
 */

public interface BaseView<T> {

    void showLoading();

    void dismissLoading();

    void showError(String message);

    void showMessage(String message);

    void showErrorConnect();
}
