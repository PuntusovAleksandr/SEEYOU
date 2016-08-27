package com.aleksandrp.seeyou.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.retrofit.entity.Broadcast;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.utils_app.UtilsApp;
import com.aleksandrp.seeyou.video_activity.VideoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AleksandrP on 03.07.2016.
 */
public class RecyclerVideoAdapterBroadcast  extends
        RecyclerView.Adapter<RecyclerVideoAdapterBroadcast.TimeViewHolder> {

    private List<Broadcast> listItems;
    private Context context;

    public RecyclerVideoAdapterBroadcast(List<Broadcast> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
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
        final Broadcast video = listItems.get(position);
        Picasso.with(context)
                .load("http://seeyou.media/" + video.getSrc() + ".jpg")
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
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra(STATICS_PARAMS.KEY_ID_VIDEO, video.getId());
                intent.putExtra(STATICS_PARAMS.KEY_FROM_VIDEO, STATICS_PARAMS.TAG_BRODCAST_FRAGMENT);
                context.startActivity(intent);
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
}