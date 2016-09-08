package com.aleksandrp.seeyou.main.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.retrofit.entity.VideoSEEYOU;
import com.aleksandrp.seeyou.utils_app.UtilsApp;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AleksandrP on 30.06.2016.
 */
public class RecyclerVideoAdapterMAinActivity extends
        RecyclerView.Adapter<RecyclerVideoAdapterMAinActivity.TimeViewHolder> {

    private List<VideoSEEYOU> listItems;
    private Context context;
    private PlayVideoSelfi mSelfi;

    public RecyclerVideoAdapterMAinActivity(List<VideoSEEYOU> listItems, Context context,
                                            PlayVideoSelfi mSelfi) {
        this.listItems = listItems;
        this.context = context;
        this.mSelfi = mSelfi;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;

        TextView title, duration, author, views;
        ImageView icon;

        TimeViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_item_main);
            title = (TextView) itemView.findViewById(R.id.tv_tile_video);
            duration = (TextView) itemView.findViewById(R.id.tv_duration_video);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            views = (TextView) itemView.findViewById(R.id.tv_duration);
            icon = (ImageView) itemView.findViewById(R.id.video_view_entity_main);
        }
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_main, parent, false);
        return (new TimeViewHolder(view));
    }

    @Override
    public void onBindViewHolder(final TimeViewHolder holder, final int position) {
        final VideoSEEYOU video = listItems.get(position);

        holder.title.setText(video.getTitle());
        holder.duration.setText("00:00");
        holder.author.setText(video.getAuthor_name());
        holder.views.setText(video.getViews());
        Picasso.with(context)
                .load("http://seeyou.media/" + video.getVideo_src() + ".jpg")
                .placeholder(R.drawable.progress_animation)
                .fit()
                .into(holder.icon);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                UtilsApp.disableDoubleClick(mView);
                mSelfi.playVideo(video.getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface PlayVideoSelfi {
        void playVideo(String ig);
    }

}
