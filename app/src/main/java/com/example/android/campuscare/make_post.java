package com.example.android.campuscare;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;

public class make_post extends AppCompatActivity {
    Button bc,bu;
    ProgressBar mProgresBar;
    private static final int PICK_IMAGE_REQUEST=1;
    private Uri mImageUri;
    private StorageReference mStorageRef ;
    private DatabaseReference mdatabaseReference;
    ImageView mimageView;
    EditText e;
    CheckBox anon;
    String usname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_post);
        getSupportActionBar().setTitle("Post...");
        Intent post=getIntent();
        final String ndomain = post.getStringExtra("name");
        bc=(Button)findViewById(R.id.choose_img);
        bu=(Button)findViewById(R.id.upload_img);
        e=(EditText)findViewById(R.id.desc);
        anon=findViewById(R.id.anon);
        FirebaseAuth Auth=FirebaseAuth.getInstance();
        FirebaseUser a=Auth.getCurrentUser();
        usname=a.getDisplayName();
        anon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked())
                    usname="Anonymous";
            }
        });

        mProgresBar =(ProgressBar)findViewById(R.id.mprogress);
       mimageView=(ImageView)findViewById(R.id.imgv);
        mStorageRef= FirebaseStorage.getInstance().getReference(ndomain);
        mdatabaseReference=FirebaseDatabase.getInstance().getReference(ndomain);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*
                String uploadid=mdatabaseReference.push().getKey();
                Upload upload =new Upload(uploadid,e.getText().toString().trim(),"kuch nhi");
                mdatabaseReference.child(uploadid).setValue(upload);
                */

                uploadfile();
            }
        });
       bc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openFileChooser();
           }
       });
    }
    private void openFileChooser()
    {
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            mImageUri = data.getData();
         try{
             Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),mImageUri);
             mimageView.setImageBitmap(bitmap);
         }
         catch(IOException e)
         {
             Toast.makeText(getApplicationContext(),"error in choosing",Toast.LENGTH_SHORT);
         }
        }
    }
    private String getFileExtension(Uri uri)
    {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    private void uploadfile()
    {
     if(mImageUri!=null)
     {
         StorageReference fileReference =mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
         fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
              Handler handler =new Handler();
              handler.postDelayed(new Runnable (){
                   @Override
                  public void run() {
                 mProgresBar.setProgress(0);
                  }
              },1000);
              Toast.makeText(getApplicationContext(),"Uploaded Successfully",Toast.LENGTH_SHORT).show();
                 Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                 while (!urlTask.isSuccessful());
                 Uri downloadUrl = urlTask.getResult();
                 final String sdownload_url = String.valueOf(downloadUrl);
              //chu(taskSnapshot.getUploadSessionUri().toString());
             chu(sdownload_url);
                 /*
                 String uploadid=mdatabaseReference.push().getKey();
              Upload upload =new Upload(uploadid,e.getText().toString().trim(),taskSnapshot.getUploadSessionUri().toString());
              mdatabaseReference.child(uploadid).setValue(upload);
             */
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
             }
         }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                 double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                 mProgresBar.setProgress((int)progress);
             }
         }) ;

     }
     else
     {
            Toast.makeText(getApplicationContext(),"Please type something or choose a file to make a post",Toast.LENGTH_SHORT).show();
     }
    }

    public void chu(String x)
    {
        System.out.println("-------------------------------chu-----------------------------");
        System.out.println("-----------------------"+x+"----------------------------------") ;
        String uid=mdatabaseReference.push().getKey();
        System.out.println("------  -------------------"+uid+"--------------------");
        Upload upload =new Upload(uid,usname,e.getText().toString().trim(),x);
        System.out.println("--------------------------done--------------------");
        mdatabaseReference.child(uid).setValue(upload);
    }
}
