package com.aleksandrp.seeyou.message.entity;

import java.util.ArrayList;

/**
 * Created by AleksandrP on 22.06.2016.
 */
public class UserMessage {

    private String name;
    private String urlPhoto;
    private int countNewMessages;
    private boolean statusOnLine;
    private String idUserFrom;
    private String idUserTo;

    private ArrayList<MessageSeeYou> mMessageSeeYous;

    public UserMessage() {
    }

    public UserMessage(String mName, String mUrlPhoto, int mCountNewMessages,
                       boolean mStatusOnLine, ArrayList<MessageSeeYou> mMessageSeeYous) {
        name = mName;
        urlPhoto = mUrlPhoto;
        countNewMessages = mCountNewMessages;
        statusOnLine = mStatusOnLine;
        this.mMessageSeeYous = mMessageSeeYous;
    }

    public UserMessage(String mName, String mUrlPhoto, int mCountNewMessages,
                       boolean mStatusOnLine, String mIdUserFrom, String mIdUserTo,
                       ArrayList<MessageSeeYou> mMessageSeeYous) {
        name = mName;
        urlPhoto = mUrlPhoto;
        countNewMessages = mCountNewMessages;
        statusOnLine = mStatusOnLine;
        idUserFrom = mIdUserFrom;
        idUserTo = mIdUserTo;
        this.mMessageSeeYous = mMessageSeeYous;
    }

    public String getIdUserFrom() {
        return idUserFrom;
    }

    public void setIdUserFrom(String mIdUserFrom) {
        idUserFrom = mIdUserFrom;
    }

    public String getIdUserTo() {
        return idUserTo;
    }

    public void setIdUserTo(String mIdUserTo) {
        idUserTo = mIdUserTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        name = mName;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String mUrlPhoto) {
        urlPhoto = mUrlPhoto;
    }

    public int getCountNewMessages() {
        return countNewMessages;
    }

    public void setCountNewMessages(int mCountNewMessages) {
        countNewMessages = mCountNewMessages;
    }

    public boolean isStatusOnLine() {
        return statusOnLine;
    }

    public void setStatusOnLine(boolean mStatusOnLine) {
        statusOnLine = mStatusOnLine;
    }

    public ArrayList<MessageSeeYou> getMessageSeeYous() {
        return mMessageSeeYous;
    }

    public void setMessageSeeYous(ArrayList<MessageSeeYou> mMessageSeeYous) {
        this.mMessageSeeYous = mMessageSeeYous;
    }
}
