package com.example.android.campuscare;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class newuser extends AppCompatActivity{
    private TextView verify;
    private TextView cont;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        verify=(TextView)findViewById(R.id.verify);
        cont=(TextView)findViewById(R.id.cont);
        cont.setVisibility(TextView.INVISIBLE);
        mAuth=FirebaseAuth.getInstance();
        String text=verify.getText().toString();
        verify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FirebaseUser user=mAuth.getCurrentUser();
                user.sendEmailVerification().addOnCompleteListener(newuser.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(newuser.this,"To verify, click on link given in mail",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

            cont.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent dom=new Intent(newuser.this,domain.class);
                startActivity(dom);
                finish();
            }
        });
            FloatingActionButton refresh=(FloatingActionButton)findViewById(R.id.refresh);
            refresh.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    mAuth=FirebaseAuth.getInstance();
                        FirebaseUser user1=mAuth.getCurrentUser();
                    if(user1.isEmailVerified()) {
                        Toast.makeText(newuser.this,"Refreshed", Toast.LENGTH_SHORT).show();
                        verify.setVisibility(View.INVISIBLE);
                        cont.setVisibility(View.VISIBLE);
                    }
                }
            });
    }

}
