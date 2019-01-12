package com.example.android.campuscare;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by user1 on 1/11/2019.
 */

public class home extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser !=null)
        {
            startActivity(new Intent(home.this,domain.class));
        }
    }
}
