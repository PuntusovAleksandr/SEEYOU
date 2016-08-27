package com.aleksandrp.seeyou.message.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.message.entity.MessageSeeYou;
import com.aleksandrp.seeyou.message.entity.UserMessage;

import java.util.ArrayList;

/**
 * Created by AleksandrP on 22.06.2016.
 */
public class RecyclerMessagesAdapter extends
        RecyclerView.Adapter<RecyclerMessagesAdapter.TimeViewHolder> {

    private ArrayList<MessageSeeYou.MessageSY> listItems;
    private Context mContext;
    private String mUserId;
    private ListenerCardViewMessSend mListenerCardViewMess;

    public RecyclerMessagesAdapter(ArrayList<MessageSeeYou.MessageSY> listItems, String mUserId, Context context,
                                   ListenerCardViewMessSend mListenerCardViewMess) {
        this.listItems = listItems;
        this.mContext = context;
        this.mUserId = mUserId;
        this.mListenerCardViewMess = mListenerCardViewMess;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView tvCoutent;
        ImageView ivLeft, ivRight;


        TimeViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_item_message_send);
            tvCoutent = (TextView) itemView.findViewById(R.id.tv_content_mess);
            ivLeft = (ImageView) itemView.findViewById(R.id.left_view);
            ivRight = (ImageView) itemView.findViewById(R.id.right_view);
        }

    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mewssage_send, parent, false);
        return (new TimeViewHolder(view));
    }

    @Override
    public void onBindViewHolder(final TimeViewHolder holder, final int position) {
        final MessageSeeYou.MessageSY userMessage = listItems.get(position);

        holder.tvCoutent.setText(userMessage.getText());
        if (userMessage.getUser_id().equals(mUserId)) {
            holder.ivRight.setVisibility(View.GONE);
            holder.ivLeft.setVisibility(View.VISIBLE);
            holder.tvCoutent.setBackgroundResource(R.drawable.status_connect_green);
            holder.tvCoutent.setTextColor(mContext.getResources().getColor(R.color.dark));
        } else {
            holder.ivLeft.setVisibility(View.GONE);
            holder.ivRight.setVisibility(View.VISIBLE);
            holder.tvCoutent.setBackgroundResource(R.drawable.round);
            holder.tvCoutent.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
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

    public interface ListenerCardViewMessSend {
        void onClickCardView(UserMessage mUserMessage);
    }

}
