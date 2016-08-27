package com.aleksandrp.seeyou.main.entity;

/**
 * Created by AleksandrP on 17.06.2016.
 */
public class VideoEntity {

    private int id;
    private String title;
    private String author;
    private int views;
    private int duration;

    public VideoEntity() {
    }

    public VideoEntity(int mId, String mTitle, String mAuthor, int mViews, int mDuration) {
        id = mId;
        title = mTitle;
        author = mAuthor;
        views = mViews;
        duration = mDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int mId) {
        id = mId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String mAuthor) {
        author = mAuthor;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int mViews) {
        views = mViews;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int mDuration) {
        duration = mDuration;
    }
}
