package com.example.lehieudut.samplemvp.ui.list_mage;

import com.example.lehieudut.samplemvp.base.BaseView;
import com.example.lehieudut.samplemvp.service.response.ImageResponse;

import java.util.List;

/**
 * Created by lehieudut on 3/1/18.
 */

public interface ListImageContract {
    /**
     * View - show view function
     */
    interface ListWeatherView extends BaseView {

        void getListImageSuccess(List<ImageResponse> response);

        void dismissRefresh();
    }

    /**
     * Presenter - logic and data function
     */
    interface ListImagePresenter {

        void getDataListImage(boolean isRefresh);

        void getDataServer(boolean isRefresh);

        List<ImageResponse> getDataOffline();

        void saveDataOffline(List<ImageResponse> responses);

        void convertImageToByte(String link);
    }
}
