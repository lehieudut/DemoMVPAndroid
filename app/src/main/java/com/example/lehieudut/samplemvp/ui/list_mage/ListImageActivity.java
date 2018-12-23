package com.example.lehieudut.samplemvp.ui.list_mage;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lehieudut.samplemvp.R;
import com.example.lehieudut.samplemvp.base.BaseActivity;
import com.example.lehieudut.samplemvp.service.response.ImageResponse;
import com.example.lehieudut.samplemvp.ui.DetailImageActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class ListImageActivity extends BaseActivity<ListImagePresenterImpl> implements ListImageContract.ListWeatherView,
        ListImageAdapter.OnListClickListener, SwipeRefreshLayout.OnRefreshListener{

    @ViewById
    RecyclerView mRecyclerView;
    @ViewById
    SwipeRefreshLayout mRefreshLayout;

    private ListImageAdapter mAdapter;
    private List<ImageResponse> mList = new ArrayList<>();

    @Override
    protected void initPresenter() {
        mPresenter = new ListImagePresenterImpl(ListImageActivity.this);
    }

    @Override
    protected void afterView() {
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ListImageAdapter(this, mList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Click(R.id.mBtn)
    void onGetDataClick(){
        mPresenter.getDataListImage(false);
    }

    @Override
    public void getListImageSuccess(List<ImageResponse> response) {
        Log.e(TAG, "getListImageSuccess: " + response.size() );
        mList.clear();
        mList.addAll(response);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void dismissRefresh() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClickItem(int position) {
        DetailImageActivity_.intent(this)
                .mUrl(mList.get(position).getUrl())
                .start();
    }

    @Override
    public void onRefresh() {
        mPresenter.getDataListImage(true);
    }
}
