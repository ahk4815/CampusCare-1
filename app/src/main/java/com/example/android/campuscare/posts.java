package com.example.android.campuscare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class posts extends AppCompatActivity {
     Button f;
     TextView t;
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    //private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        f=(Button)findViewById(R.id.buttonpost);
        Intent new_feed =getIntent();
        final String n=new_feed.getStringExtra("type");
        //t=(TextView)findViewById(R.id.textt);
        //t.setText(n);

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent post =new Intent(posts.this,make_post.class);
                post.putExtra("name",n);
                startActivity(post);
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

       // mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference(n);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                    System.out.println("--------------here--------------------"+upload.getImageUrl()+"-----------------------------------------");
                }
                Collections.reverse(mUploads);
                mAdapter = new ImageAdapter(posts.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
                //  mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(posts.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
        //        mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        CreateMenu(menu);
        return true;
    }
    private void CreateMenu(Menu menu){
        MenuItem mnu= menu.add(0,0,0,"Anonymous");
        {
            mnu.setIcon(R.mipmap.ic_launcher);
            mnu.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }
    private boolean MenuChoice(MenuItem item){
        switch(item.getItemId()){
            case 0:Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
                //Intent anon=new Intent(this,anonymous.class);
                //startActivity(anon);
                return true;
        }
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return MenuChoice(item);
    }

}
