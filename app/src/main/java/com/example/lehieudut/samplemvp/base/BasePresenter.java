package com.example.lehieudut.samplemvp.base;

import android.content.Context;

/**
 * Created by lehieudut on 2/28/18.
 */

public abstract class BasePresenter<T> {

    protected T view;
    protected Context context;

    public BasePresenter(Context context){
        this.context = context;
    }

    protected void attachView(T view){
        this.view = view;
    };

    protected void detachView(){
        this.view = null;
    };

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
