package com.example.dreamteam.model;

public class Upload {
    private String  mName;
    private String mImageUrl;

    public Upload(){

    }

    public Upload(String name, String imageUrl){

        if (name.trim().equals("")){
            name = "No name";
        }

        mName = name;
        mImageUrl = imageUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}