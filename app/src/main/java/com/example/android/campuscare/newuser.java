package com.example.android.campuscare;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class newuser extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

    }
    public void Hop(View view)
    {
        Intent domain=new Intent(newuser.this, domain.class);
        startActivity(domain);
    }
}
