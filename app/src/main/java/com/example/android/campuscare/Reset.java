package com.example.android.campuscare;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset extends AppCompatActivity {
    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.reset);
        email=(EditText)findViewById(R.id.email1);
    }
    public void reset(View view){
        FirebaseAuth auth=FirebaseAuth.getInstance();
        final View snack=(View)findViewById(R.id.snack);
        String email_id= email.getText().toString().trim();
        if(TextUtils.isEmpty(email_id))
        {
            Snackbar.make(snack,"Please enter email Id",Snackbar.LENGTH_SHORT).show();
            return;
        }
        auth.sendPasswordResetEmail(email_id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Snackbar.make(snack, "Check Email to Reset your Password", Snackbar.LENGTH_INDEFINITE).show();
                    finish();
                }
                else
                    Snackbar.make(snack,"Failed to send reset password email",Snackbar.LENGTH_INDEFINITE).show();
            }
        });
    }
}
