package com.example.android.campuscare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class domain extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain);
        Intent dom=getIntent();
        lv=(ListView)findViewById(R.id.list_view);
       final String domain_name[]={"ADMINISTRATION","POLLUTION","RAGGING","LAW AND ORDER","WOMAN SAFETY","MISCONDUCT"};
        ArrayAdapter<String> arr = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Arrays.asList(domain_name)) ;
        lv.setAdapter(arr);
        Toast.makeText(this,"click on any domain",Toast.LENGTH_SHORT).show();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),domain_name[position],Toast.LENGTH_SHORT).show();
                Intent new_feed =new Intent(domain.this,posts.class);
                new_feed.putExtra("type",domain_name[position]) ;
                startActivity(new_feed);
            }
        });
    }

}
