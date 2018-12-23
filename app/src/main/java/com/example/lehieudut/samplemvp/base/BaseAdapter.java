package com.example.lehieudut.samplemvp.base;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Base Adapter.
 *
 * @param <VH> is a type extend from {@link RecyclerView.ViewHolder}
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final Context mContext;

    protected BaseAdapter(@NonNull Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }

    protected Resources getResources() {
        return mContext.getResources();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
