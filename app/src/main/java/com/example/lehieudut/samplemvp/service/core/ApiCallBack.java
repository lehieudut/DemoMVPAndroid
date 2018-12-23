package com.example.lehieudut.samplemvp.service.core;

import android.util.Log;

import com.example.lehieudut.samplemvp.base.BaseResponse;
import com.example.lehieudut.samplemvp.service.OnServiceErrorListener;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Api callback
 */
public abstract class ApiCallBack<T extends BaseResponse> implements Callback<T> {
    private static final String TAG = ApiCallBack.class.getSimpleName();
    private static final int RETRY_NUMBER_MAX = 3;
    private boolean isRetry;
    private int retryCount;

    private static OnServiceErrorListener sListener;

    public ApiCallBack() {
    }

    /**
     * listener
     *
     * @param onAppErrorListener
     */
    public static void setOnServiceErrorListener(OnServiceErrorListener onAppErrorListener) {
        sListener = onAppErrorListener;
    }

    public ApiCallBack(boolean isRetry) {
        this.isRetry = isRetry;
    }

    public abstract void onSuccess(T response);

    public abstract void onFailed(ApiError apiError);

    public void onRetry(Call<T> call, ApiError error) {
        Log.d(TAG, "onRetry: ");
        if (retryCount < RETRY_NUMBER_MAX) {
            retryCount++;
            call.clone().enqueue(this);
        } else {
            retryCount = 0;
            onFailed(error);
        }
    }

    /**
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                //if (response.body().isStatus() && response.body().getError() == 0 || response.body().getError() == 1001) {
                if(response.body() != null){
                    Log.d(TAG, "onResponse: success");
                    onSuccess(response.body());
                } else if (response.body().getError() == 1991) {
                    if (sListener != null)
                        sListener.onAuthenticationError();
                } else {
                    Log.d(TAG, "onResponse: error");
                    ApiError error = new ApiError(response.code(), response.body().getMeta() != null
                            ? response.body().getMeta().getError()
                            : response.body().getMessage());
                    handlerError(call, error);
                }
            } else {
                Log.d(TAG, "onResponse: Not Body");
                Reader reader = new InputStreamReader(response.errorBody().byteStream(), Charset.forName("UTF-8"));
                Gson gson = new Gson();
                ApiError apiError = gson.fromJson(reader, ApiError.class);
                handlerError(call, apiError);
            }
        } else {
            Log.d(TAG, "onResponse: service error");
            // handler error message here
            handlerError(call, new ApiError(response.code(), "Service error, please try again!"));
        }
    }

    /**
     * on Failure
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d(TAG, "onFailure: ");
        if (t instanceof ConnectException && (!isRetry || retryCount == RETRY_NUMBER_MAX)) {
            retryCount = 0;
            if (sListener != null)
                sListener.onNetworkError();
            return;
        } else if (t instanceof TimeoutException || t instanceof SocketTimeoutException && (!isRetry || retryCount == RETRY_NUMBER_MAX)) {
            retryCount = 0;
            if (sListener != null) {
                sListener.onRequestConnectTimeout();
                return;
            }
        }
        ApiError error = new ApiError(0, t.getMessage());
        handlerError(call, error);
    }

    private void handlerError(Call<T> call, ApiError error) {
        if (isRetry) {
            onRetry(call, error);
        } else {
            onFailed(error);
        }
    }
}
