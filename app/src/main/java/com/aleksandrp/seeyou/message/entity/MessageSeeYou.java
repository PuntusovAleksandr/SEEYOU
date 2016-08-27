package com.aleksandrp.seeyou.message.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AleksandrP on 22.06.2016.
 */
public class MessageSeeYou {

    @SerializedName("dialog_id")
    @Expose
    private String dialog_id;

    @SerializedName("messages")
    @Expose
    private ArrayList<MessageSY> mMessageSie;

    private String content;
    private String dateMessage;
    private boolean fromUser;

    public MessageSeeYou() {
    }

    public MessageSeeYou(String mContent, String mDateMessage, boolean mFromUser) {
        content = mContent;
        dateMessage = mDateMessage;
        fromUser = mFromUser;
    }

    public ArrayList<MessageSY> getMessageSie() {
        return mMessageSie;
    }

    public void setMessageSie(ArrayList<MessageSY> mMessageSie) {
        this.mMessageSie = mMessageSie;
    }

    public String getDialog_id() {
        return dialog_id;
    }

    public void setDialog_id(String mDialog_id) {
        dialog_id = mDialog_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String mContent) {
        content = mContent;
    }

    public String getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(String mDateMessage) {
        dateMessage = mDateMessage;
    }

    public boolean isFromUser() {
        return fromUser;
    }

    public void setFromUser(boolean mFromUser) {
        fromUser = mFromUser;
    }



    public class MessageSY {

        @SerializedName("user_id")
        @Expose
        private String user_id;

        @SerializedName("text")
        @Expose
        private String text;

        @SerializedName("date")
        @Expose
        private String date;

        public MessageSY() {
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String mUser_id) {
            user_id = mUser_id;
        }

        public String getText() {
            return text;
        }

        public void setText(String mText) {
            text = mText;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String mDate) {
            date = mDate;
        }
    }
}
