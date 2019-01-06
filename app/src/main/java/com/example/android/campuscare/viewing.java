package com.example.android.campuscare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class viewing extends AppCompatActivity {
   ImageView img;
   TextView tet;
   EditText et;
   Button btn ;
   ListView lv;
   List<comments> ArtistList ;
   FirebaseUser user;
   FirebaseAuth mAuth;
    private DatabaseReference ndatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing);
        Intent comm=getIntent();
       String imageUrl=comm.getStringExtra("image_url");
        String des=comm.getStringExtra("desc");
        final String username=comm.getStringExtra("username");
        String idd=comm.getStringExtra("id");
        img=(ImageView)findViewById(R.id.pic);
        tet=(TextView)findViewById(R.id.text3);
        tet.setText(des);
      ArtistList=new ArrayList<>();
      lv=(ListView)findViewById(R.id.list);
        Target<GlideDrawable> into = Glide.with(this)
                .load(imageUrl)
                .into(img);
        System.out.println(idd);
        ndatabaseReference= FirebaseDatabase.getInstance().getReference(idd);
    et=(EditText)findViewById(R.id.comment);
    btn=(Button)findViewById(R.id.upload_com);
    btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String y=et.getText().toString().trim();
        if(y.length()!=0) {
            String uid = ndatabaseReference.push().getKey();
            // System.out.println("-------------------------"+uid+"--------------------");
            comments com = new comments(uid,username,y);
            //System.out.println("--------------------------done--------------------");
            ndatabaseReference.child(uid).setValue(com);
            Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"please enter comment",Toast.LENGTH_SHORT).show();

        }

        }
});

    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent comm=getIntent();
        String des=comm.getStringExtra("desc");
        //attaching value event listener
        ndatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                ArtistList.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    comments artist = postSnapshot.getValue(comments.class);
                    //adding artist to the list
                    ArtistList.add(artist);
                }
                Collections.reverse(ArtistList);
                //creating adapter
                adapterComm  ad = new adapterComm(viewing.this, ArtistList);
                //attaching adapter to the listview
                lv.setAdapter(ad);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
