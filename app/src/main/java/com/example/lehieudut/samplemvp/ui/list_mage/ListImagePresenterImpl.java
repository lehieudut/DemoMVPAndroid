package com.example.lehieudut.samplemvp.ui.list_mage;

import android.content.Context;

import com.example.lehieudut.samplemvp.base.BasePresenter;
import com.example.lehieudut.samplemvp.service.core.ApiCallBack;
import com.example.lehieudut.samplemvp.service.core.ApiClient;
import com.example.lehieudut.samplemvp.service.core.ApiError;
import com.example.lehieudut.samplemvp.service.response.DataResponse;
import com.example.lehieudut.samplemvp.service.response.ImageResponse;
import com.example.lehieudut.samplemvp.utils.ConnectionUtil;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;

/**
 * Created by lehieudut on 2/28/18.
 * <p>
 * Sampe presenter. We write all logic function here
 */
public class ListImagePresenterImpl extends BasePresenter<ListImageContract.ListWeatherView> implements ListImageContract.ListImagePresenter {

    private Realm mRealm;

    ListImagePresenterImpl(Context context) {
        super(context);
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void getDataListImage(boolean isRefresh) {
        if (ConnectionUtil.isConnected(getContext())) {
            getDataServer(isRefresh);
        } else {
            getView().showErrorConnect();
            getView().getListImageSuccess(getDataOffline());
            getView().dismissRefresh();
        }
    }

    @Override
    public void getDataServer(final boolean isRefresh) {
        if(!isRefresh){
            getView().showLoading();
        }
        Call<DataResponse> req = ApiClient.getService().getListImage();
        req.enqueue(new ApiCallBack<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {
                getView().getListImageSuccess(response.getImages());
                if(isRefresh){
                    getView().dismissRefresh();
                }else {
                    getView().dismissLoading();
                }
                saveDataOffline(response.getImages());
            }

            @Override
            public void onFailed(ApiError apiError) {
                getView().showError(apiError.getMessage());
            }
        });
    }

    @Override
    public List<ImageResponse> getDataOffline() {
        RealmResults<ImageResponse> realmResults = mRealm.where(ImageResponse.class).findAll();
        return mRealm.copyFromRealm(realmResults);
    }

    @Override
    public void saveDataOffline(final List<ImageResponse> list) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealmOrUpdate(list);
            }
        });
    }

    @Override
    public void convertImageToByte(String link) {

    }
}
