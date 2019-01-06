package com.example.android.campuscare;

/**
 * Created by user1 on 10/16/2018.
 */


public class Upload {
    String mId;
     String mUsername;
      String mName;
      String mImageUrl;
    public Upload()
    {

    }

    public Upload(String mId,String mUsername,String mName,String mImageUrl) {
        this.mId=mId;
        this.mUsername=mUsername;
        this.mName=mName;
        this.mImageUrl=mImageUrl;
    }

    public String getName() {
        return mName;
    }
    public String getid()
    {
        return mId;
    }
    public String getUsername() { return mUsername;    }
    public void setName(String mName) {
        this.mName = mName;
    }
    public void setmUsername(String mUsername){this.mUsername=mUsername;}
    public String getImageUrl() {
        return mImageUrl;
    }
    public void setmId(String mId){this.mId=mId;}
    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}

