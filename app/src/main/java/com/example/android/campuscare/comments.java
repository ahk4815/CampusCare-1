package com.example.android.campuscare;

/**
 * Created by user1 on 11/13/2018.
 */

public class comments {
    String nId;
    private String ncom;
    String nUser;
    public comments()
    {

    }

    public comments(String mId,String mUsername,String mName) {
        this.nId=mId;
        this.nUser=mUsername;
        this.ncom=mName;
    }

    public String getcom() {
        return ncom;
    }
    public String getid()
    {
        return nId;
    }
    public String getuser(){return nUser;   }

    public void setcom(String mName) {
        this.ncom = mName;
    }


}