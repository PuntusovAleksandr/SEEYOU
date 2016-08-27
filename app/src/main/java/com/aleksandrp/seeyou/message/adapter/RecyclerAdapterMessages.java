package com.aleksandrp.seeyou.message.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.retrofit.entity.UsersMessageOnLine;
import com.aleksandrp.seeyou.utils_app.UtilsApp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by AleksandrP on 22.06.2016.
 */
public class RecyclerAdapterMessages extends
        RecyclerView.Adapter<RecyclerAdapterMessages.TimeViewHolder> {

    private ArrayList<UsersMessageOnLine> listItems;
    private Context mContext;
    private String mUserId;
    private ListenerCardViewMess mListenerCardViewMess;

    public RecyclerAdapterMessages(ArrayList<UsersMessageOnLine> listItems, String mUserId, Context context,
                                   ListenerCardViewMess mListenerCardViewMess) {
        this.listItems = listItems;
        this.mContext = context;
        this.mListenerCardViewMess = mListenerCardViewMess;
        this.mUserId = mUserId;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView tvCountMess, tvNameUser;
        ImageView ivIconUser, ivStatusOnline;
        RelativeLayout rlCountMessages;


        TimeViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_item_message_user);
            ivIconUser = (ImageView) itemView.findViewById(R.id.video_view_entity);
            ivStatusOnline = (ImageView) itemView.findViewById(R.id.iv_status_connect);
            tvCountMess = (TextView) itemView.findViewById(R.id.tv_count_message);
            tvNameUser = (TextView) itemView.findViewById(R.id.tv_name_user);
            rlCountMessages = (RelativeLayout) itemView.findViewById(R.id.rl_count_messages);
        }

    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mewssage, parent, false);
        return (new TimeViewHolder(view));
    }

    @Override
    public void onBindViewHolder(final TimeViewHolder holder, final int position) {
        final UsersMessageOnLine userMessage = listItems.get(position);

        holder.tvNameUser.setText(userMessage.getName() + " " +
                userMessage.getSurname());

        String urlPhoto = String.valueOf(userMessage.getAvatar());
        if (urlPhoto.equals("") || urlPhoto.contains("null")) {
            Picasso.with(mContext)
                    .load(R.drawable.com_facebook_profile_picture_blank_portrait)
                    .fit()
                    .into(holder.ivIconUser);
        } else {
            Picasso.with(mContext)
                    .load(urlPhoto)
                    .placeholder(R.drawable.com_facebook_profile_picture_blank_portrait)
                    .fit()
                    .into(holder.ivIconUser);
        }

        if (userMessage.getCountMessage() == null || userMessage.getCountMessage().equals(0)) {
            holder.tvCountMess.setText("");
            holder.rlCountMessages.setVisibility(View.GONE);
        } else {
            holder.tvCountMess.setText(userMessage.getCountMessage());
            holder.rlCountMessages.setVisibility(View.VISIBLE);
        }

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                UtilsApp.disableDoubleClick(mView);
                mListenerCardViewMess.onClickCardView(userMessage.getId(), mUserId);
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

    public interface ListenerCardViewMess {
        void onClickCardView(String mIdUserTo, String mUserId);
//        void onClickCardView(UserMessage mUserMessage);
    }

}