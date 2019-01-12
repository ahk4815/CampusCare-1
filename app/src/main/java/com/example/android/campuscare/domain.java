package com.example.android.campuscare;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.load.engine.Resource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class domain extends AppCompatActivity {
    ListView gv;
    Button btn;
    FirebaseAuth fb;
    FirebaseUser fu;
    private long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain);
        Intent dom=getIntent();
        gv=(ListView)findViewById(R.id.list_view);
        btn=(Button)findViewById(R.id.logout);

        fb=FirebaseAuth.getInstance();
        fu=fb.getCurrentUser();

        getSupportActionBar().setTitle("Hello, "+FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        final String domain_name[]={"ADMINISTRATION","POLLUTION","RAGGING","LAW AND ORDER","WOMAN SAFETY","MISCONDUCT"};
        ArrayAdapter<String> arr = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Arrays.asList(domain_name)) ;
        gv.setAdapter(arr);
        ImageView a;
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent new_feed =new Intent(domain.this,posts.class);
                new_feed.putExtra("type",domain_name[position]) ;
                startActivity(new_feed);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              FirebaseAuth.getInstance().signOut();
              Intent intent=new Intent(domain.this,logging.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
              startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
      /* if(backPressedTime+2000>System.currentTimeMillis()){
            super.onBackPressed();
             return;
        }
        else{
            Toast.makeText(this,"press back twice to exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime=System.currentTimeMillis() ;
    */
    // finishAffinity();
        moveTaskToBack(true);

    }
}
