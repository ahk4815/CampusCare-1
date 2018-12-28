package com.example.android.campuscare;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private TextView signin;
    private TextView loggin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private ImageView immg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ConnectivityManager cm=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
        boolean isConnected=activeNetwork!=null &&activeNetwork.isConnectedOrConnecting();
        if(isConnected)
            setContentView(R.layout.activity_main);
        else
        {
            setContentView(R.layout.activity_main);
            Toast.makeText(getApplicationContext(),"Please connect to internet",Toast.LENGTH_LONG).show();
        }
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        loggin=(TextView)findViewById(R.id.login);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        signin=(TextView) findViewById(R.id.signup);

        //Glide.with(this)
            //    .load("https://firebasestorage.googleapis.com/v0/b/campuscare-fc6ae.appspot.com/o/LAW%20AND%20ORDER%2F1540367980550.jpg?alt=media&token=82267b46-75ff-4daf-8445-6a01ada21702")
              //  .into(immg);

    }

    public void register(View view){
        String email_id=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if((TextUtils.isEmpty(email_id))||(TextUtils.isEmpty(pass)))
        {
            Toast.makeText(this,"Please enter email and password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering ....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email_id,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                    progressDialog.hide();
                    Intent newuser=new Intent(MainActivity.this,newuser.class);
                    startActivity(newuser);
                    //    Intent description=new Intent(MainActivity.this,description.class);
                    //  startActivity(description);
                }
               /* else
                {
                    progressDialog.hide();
                    Toast.makeText(MainActivity.this,"Sorry!!\nTry again with new ID.",Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }
    public void log_in(View view) {
        Intent i = new Intent(MainActivity.this, logging.class);
        startActivity(i);
    }
}
