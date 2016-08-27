package com.aleksandrp.seeyou.video_activity.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.retrofit.entity.Broadcast;
import com.aleksandrp.seeyou.retrofit.entity.VideoSEEYOU;
import com.aleksandrp.seeyou.utils_app.UtilsApp;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AleksandrP on 05.07.2016.
 */
public class RecyclerVideoAdapterBroadcastShowActivity extends
        RecyclerView.Adapter<RecyclerVideoAdapterBroadcastShowActivity.TimeViewHolder> {

    private List<Broadcast> listItems;
    private List<VideoSEEYOU> mVideoSEEYOUs;
    private Context context;
    private ClickShowVideo mClickShowVideo;

    public RecyclerVideoAdapterBroadcastShowActivity(List<Broadcast> listItems,
                                                     List<VideoSEEYOU> mVideoSEEYOUs, Context context,
                                                     ClickShowVideo mClickShowVideo) {
        this.listItems = listItems;
        this.mVideoSEEYOUs = mVideoSEEYOUs;
        this.context = context;
        this.mClickShowVideo = mClickShowVideo;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;

        TextView title, duration, author, views;
        ImageView mImage;

        TimeViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_item);
            title = (TextView) itemView.findViewById(R.id.tv_tile_video);
            duration = (TextView) itemView.findViewById(R.id.tv_duration_video);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            views = (TextView) itemView.findViewById(R.id.tv_duration);
            mImage = (ImageView) itemView.findViewById(R.id.video_view_entity);
        }
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return (new TimeViewHolder(view));
    }

    @Override
    public void onBindViewHolder(final TimeViewHolder holder, final int position) {
        if (listItems != null && listItems.size() > 0) {

            final Broadcast video = listItems.get(position);
            final String src = video.getSrc();
            Picasso.with(context)
                    .load("http://seeyou.media/" + src + ".jpg")
                    .placeholder(R.drawable.progress_animation)
                    .fit()
                    .into(holder.mImage);

            holder.title.setText(video.getTitle());
            holder.duration.setText(video.getEnd_time());
            holder.author.setText(video.getClub_name());
            holder.views.setText(video.getViews());
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View mView) {
                    UtilsApp.disableDoubleClick(mView);
                    mClickShowVideo.clickShowBroadcast(src);
                }
            });
        } else {
            final VideoSEEYOU videoSEEYOU = mVideoSEEYOUs.get(position);
            final String src = videoSEEYOU.getVideo_src();
            Picasso.with(context)
                    .load("http://seeyou.media/" + src + ".jpg")
                    .placeholder(R.drawable.progress_animation)
                    .fit()
                    .into(holder.mImage);

            holder.title.setText(videoSEEYOU.getTitle());
            holder.duration.setText("");
            holder.author.setText(videoSEEYOU.getAuthor_name());
            holder.views.setText(videoSEEYOU.getViews());
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View mView) {
                    UtilsApp.disableDoubleClick(mView);
                    mClickShowVideo.clickShowVideo(videoSEEYOU);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        int size = 0;
        if (listItems != null && listItems.size() > 0) {
            size = listItems.size();
        } else if (mVideoSEEYOUs != null) {
            size = mVideoSEEYOUs.size();
        }
        return size;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface ClickShowVideo {
        void clickShowVideo(VideoSEEYOU mVideoSEEYOU);

        void clickShowBroadcast(String mSrc);
    }

}