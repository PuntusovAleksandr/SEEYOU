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
import com.aleksandrp.seeyou.retrofit.entity.CommentVideo;
import com.aleksandrp.seeyou.utils_app.UtilsApp;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AleksandrP on 04.07.2016.
 */
public class RecyclerVideoAdapterBroadcastVideoActivity extends
        RecyclerView.Adapter<RecyclerVideoAdapterBroadcastVideoActivity.TimeViewHolder> {

    private List<CommentVideo> mListComments;
    private Context context;
    private ClickVideo mClickVideo;


    public RecyclerVideoAdapterBroadcastVideoActivity(List<CommentVideo> mListComments, Context context,
                                                      ClickVideo mClickVideo) {
        this.mListComments = mListComments;
        this.context = context;
        this.mClickVideo = mClickVideo;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;

        TextView name, time, description, replay;
        ImageView mImage;

        View mView;

        TimeViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_item_comments);
            name = (TextView) itemView.findViewById(R.id.tv_user_name_comment);
            time = (TextView) itemView.findViewById(R.id.tv_user_time_comment);
            description = (TextView) itemView.findViewById(R.id.tv_content_comment);
            replay = (TextView) itemView.findViewById(R.id.tv_replay);
            mImage = (ImageView) itemView.findViewById(R.id.image_user_comment);
            mView = itemView.findViewById(R.id.view_margin_left);
        }
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_comments, parent, false);
        return (new TimeViewHolder(view));
    }

    @Override
    public void onBindViewHolder(final TimeViewHolder holder, final int position) {
        final CommentVideo commentVideo = mListComments.get(position);
        if (commentVideo.getType().contains("1")) {
            holder.mView.setVisibility(View.GONE);
        } else holder.mView.setVisibility(View.VISIBLE);

        Picasso.with(context)
                .load("http://seeyou.media/" + commentVideo.getAuthor_avatar())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(holder.mImage);

        holder.name.setText(commentVideo.getAuthor_name());
        holder.time.setText(commentVideo.getCreated_at());
        holder.description.setText(commentVideo.getText());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                UtilsApp.disableDoubleClick(mView);
                mClickVideo.clickToConnect(commentVideo);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mListComments.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface ClickVideo {
        void clickToConnect(CommentVideo mCommentVideo);
    }

}