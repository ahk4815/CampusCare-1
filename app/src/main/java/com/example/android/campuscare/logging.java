package com.example.android.campuscare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class logging extends AppCompatActivity {

    private EditText password;
    private EditText email;
    private TextView b;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        getSupportActionBar().hide();
        Intent i=getIntent();
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        password=(EditText)findViewById(R.id.password1);
        email=(EditText)findViewById(R.id.email1);
        b=(TextView) findViewById(R.id.login);
    }

    public void loginUser(View view)
    {
        String email_id=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if(TextUtils.isEmpty(email_id)||TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"Please enter Email Id and Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging In...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email_id,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressDialog.hide();
                    user=firebaseAuth.getCurrentUser();
                    if(user.isEmailVerified()) {
                        Intent dom = new Intent(logging.this, domain.class);
                        startActivity(dom);
                    }
                    else
                    {
                        Intent newuser=new Intent(logging.this, newuser.class);
                        startActivity(newuser);
                        Toast.makeText(logging.this,"Please verify Email Id",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                else
                {
                    Toast.makeText(logging.this,"incorrect",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
    public void forgot_password(View view){
        Intent forgot=new Intent(this,Reset.class);
        startActivity(forgot);
    }
}

