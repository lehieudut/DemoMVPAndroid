package com.example.lehieudut.samplemvp.ui.list_mage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lehieudut.samplemvp.R;
import com.example.lehieudut.samplemvp.base.BaseAdapter;
import com.example.lehieudut.samplemvp.service.response.ImageResponse;

import java.util.List;

/**
 * Created by lehieudut on 2/28/18.
 */

public class ListImageAdapter extends BaseAdapter {

    /**
     * Interface
     */
    public interface OnListClickListener {
        void onClickItem(int position);
    }

    private OnListClickListener mListener;
    private List<ImageResponse> mList ;

    ListImageAdapter(@NonNull Context context, List<ImageResponse> list, OnListClickListener listener) {
        super(context);
        mListener = listener;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindItemViewHolder((ContentViewHolder) holder, position);
    }

    /**
     * on Bind view holder
     *
     * @param holder
     * @param position
     */
    private void onBindItemViewHolder(ContentViewHolder holder, int position) {
        holder.mLId.setText(String.valueOf(mList.get(position).getId()));
        Glide.with(getContext())
                .load(mList.get(position).getUrl())
                .centerCrop()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * ViewHolder item
     */
    public class ContentViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mLId;

        public ContentViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.mImageView);
            mLId = (TextView) v.findViewById(R.id.mLId);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onClickItem(getAdapterPosition());
                    }
                }
            });
        }
    }
}
