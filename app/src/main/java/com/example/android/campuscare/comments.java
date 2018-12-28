package com.example.android.campuscare;

/**
 * Created by user1 on 11/13/2018.
 */

public class comments {
    String nId;
    String ncom;
    public comments()
    {

    }

    public comments(String mId,String mName) {
        this.nId=mId;
        this.ncom=mName;
    }

    public String getcom() {
        return ncom;
    }
    public String getid()
    {
        return nId;
    }
    public void setcom(String mName) {
        this.ncom = mName;
    }

}