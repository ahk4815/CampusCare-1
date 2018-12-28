package com.example.android.campuscare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class logging extends AppCompatActivity {

    private EditText password;
    private EditText email;
    private TextView b;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
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
        progressDialog.setMessage("Logging In...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email_id,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(logging.this,"valid user",Toast.LENGTH_SHORT).show();
                    progressDialog.hide();
                    Intent dom=new Intent(logging.this,domain.class);
                    startActivity(dom);
                }
                else
                {
                    Toast.makeText(logging.this,"incorrect",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}

