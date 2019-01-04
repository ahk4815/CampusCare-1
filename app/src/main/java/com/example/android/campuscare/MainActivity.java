package com.example.android.campuscare;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.Snackbar;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private TextView signin;
    private TextView loggin;
    private TextView verify;
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
        verify=(TextView)findViewById(R.id.verify);


        //Glide.with(this)
            //    .load("https://firebasestorage.googleapis.com/v0/b/campuscare-fc6ae.appspot.com/o/LAW%20AND%20ORDER%2F1540367980550.jpg?alt=media&token=82267b46-75ff-4daf-8445-6a01ada21702")
              //  .into(immg);

    }

    protected void register(View view){
        InputMethodManager eid=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        eid.hideSoftInputFromInputMethod(email.getWindowToken(),0);

        final String email_id=email.getText().toString().trim();
        final String pass=password.getText().toString().trim();
        if((TextUtils.isEmpty(email_id))||(TextUtils.isEmpty(pass)))
        {
            Toast.makeText(this,"Please enter email and password",Toast.LENGTH_SHORT).show();
            return;
        }
        final View snackbar=findViewById(R.id.snackbar);
        progressDialog.setMessage("Registering ....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email_id,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Snackbar.make(snackbar,"Registered Successfully ☺",Snackbar.LENGTH_LONG).show();
                    progressDialog.hide();
                    firebaseAuth=FirebaseAuth.getInstance();
                    final FirebaseUser user = firebaseAuth.getCurrentUser();
                    user.sendEmailVerification().addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Snackbar.make(snackbar,"Please click on the verification link in your email",Snackbar.LENGTH_INDEFINITE).show();


                    //    Intent description=new Intent(MainActivity.this,description.class);
                    //  startActivity(description);
                }
                else
                {
                    Snackbar.make(snackbar,"Failed to send email",Snackbar.LENGTH_INDEFINITE).show();
                }
                        }
                    });
                }

            }
        });
    }
    public void log_in(View view) {
        Intent i = new Intent(MainActivity.this, logging.class);
        startActivity(i);
    }


}
