package com.aleksandrp.seeyou.main.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.main.entity.VideoEntity;
import com.aleksandrp.seeyou.utils_app.UtilsApp;

import java.util.ArrayList;

/**
 * Created by AleksandrP on 17.06.2016.
 */
public class RecyclerVideoAdapter extends
        RecyclerView.Adapter<RecyclerVideoAdapter.TimeViewHolder> {

    private ArrayList<VideoEntity> listItems;
    private Context context;

    private boolean adult;


    public RecyclerVideoAdapter(ArrayList<VideoEntity> listItems, Context context, boolean adult) {
        this.listItems = listItems;
        this.context = context;
        this.adult = adult;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;

        TextView title, duration, author, views;

        TimeViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_item);
            title = (TextView) itemView.findViewById(R.id.tv_tile_video);
            duration = (TextView) itemView.findViewById(R.id.tv_duration_video);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            views = (TextView) itemView.findViewById(R.id.tv_duration);
        }
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return (new TimeViewHolder(view));
    }

    @Override
    public void onBindViewHolder(final TimeViewHolder holder, final int position) {
        final VideoEntity video = listItems.get(position);

        holder.title.setText(video.getTitle());
        holder.duration.setText(video.getDuration() + "");
        holder.author.setText(video.getAuthor());
        holder.views.setText(video.getViews() + "");

        if (adult) {
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View mView) {
                    UtilsApp.disableDoubleClick(mView);
                    // TODO: 11.07.2016

                }
            });
        }
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