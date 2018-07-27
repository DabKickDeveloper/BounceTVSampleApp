package com.dabkick.videoplayer.bouncetv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dabkick.videoplayer.bouncetv.PlayListModels.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HorizontalListAdapter extends BaseAdapter {

    List<Video> videoItemDetails;
    Context mActivity;

    HorizontalListAdapter(Context mActivity, List<Video> videoItemDetails){

        this.mActivity = mActivity;
        this.videoItemDetails = videoItemDetails;

    }

    @Override
    public int getCount() {
        return videoItemDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return videoItemDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View rowView;

        if (convertView == null) {
            rowView = LayoutInflater.from(mActivity).inflate(R.layout.horizontal_list_item, null);
        }else{
            rowView = convertView;
        }

        ImageView thumbnailImg = (ImageView) rowView.findViewById(R.id.thumbnail);
        TextView videoDesc = (TextView) rowView.findViewById(R.id.video_desc);

        Picasso.with(mActivity).setLoggingEnabled(true);
        Picasso.with(mActivity)
                .load(videoItemDetails.get(position).getImage()).into(thumbnailImg);

        videoDesc.setText(videoItemDetails.get(position).getTitle());

        return rowView;
    }
}
