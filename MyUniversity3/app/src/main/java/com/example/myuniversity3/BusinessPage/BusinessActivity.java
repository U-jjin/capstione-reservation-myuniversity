package com.example.myuniversity3.BusinessPage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myuniversity3.Information.Shop;
import com.example.myuniversity3.R;
import com.example.myuniversity3.StudnetPage.StMainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BusinessActivity extends AppCompatActivity implements View.OnClickListener {

     RadioButton green, black, yellow, red;

     private TextView operating_tv;
    private  ImageView congestion_img;
    private   String myid, name;
   private DatabaseReference dbr, myinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        Toolbar mytoolbar =findViewById(R.id.stmain_toolbar);
        setSupportActionBar(mytoolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_logout);

        green =findViewById(R.id.greenbtn);
        red =findViewById(R.id.redwbtn);
        black =findViewById(R.id.blackbtn);
        yellow =findViewById(R.id.yellowbtn);

        congestion_img =findViewById(R.id.congestion_img);
        operating_tv =findViewById(R.id.setoperating_tv);
        Intent intent =getIntent();
        myid = intent.getStringExtra("myid");

        dbr =FirebaseDatabase.getInstance().getReference();
        myinfo= dbr.child("restaurant").child(myid);

        myinfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setcondition(dataSnapshot.child("congestion").getValue(int.class));
                operating_tv.setText(dataSnapshot.child("operatingtime").getValue(String.class));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        green.setOnClickListener(this);
        yellow.setOnClickListener(this);
        red.setOnClickListener(this);
        black.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final View view =v;
        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("restaurant").child(myid).child("name").getValue(String.class);

                switch (view.getId()){
                    case R.id.blackbtn:
                        myinfo.child("congestion").setValue(0);

                        if(dataSnapshot.child("external_list").child(name).exists()) dbr.child("external_list").child(name).child("congestion").setValue(0);
                        else dbr.child("othershop_list").child(name).child("congestion").setValue(0);

                        congestion_img.setImageResource(R.drawable.ic_close_black);

                        break;
                    case R.id.greenbtn:
                        myinfo.child("congestion").setValue(1);
                        if(dataSnapshot.child("external_list").child(name).exists()) dbr.child("external_list").child(name).child("congestion").setValue(1);
                        else dbr.child("othershop_list").child(name).child("congestion").setValue(1);

                        congestion_img.setImageResource(R.drawable.ic_relax_green);
                        break;
                    case R.id.yellowbtn:
                        myinfo.child("congestion").setValue(2);

                        if(dataSnapshot.child("external_list").child(name).exists()) dbr.child("external_list").child(name).child("congestion").setValue(2);
                        else dbr.child("othershop_list").child(name).child("congestion").setValue(2);

                        congestion_img.setImageResource(R.drawable.ic_soso_yellow);

                        break;
                    case R.id.redwbtn:
                        myinfo.child("congestion").setValue(3);

                        if(dataSnapshot.child("external_list").child(name).exists()) dbr.child("external_list").child(name).child("congestion").setValue(3);
                        else dbr.child("othershop_list").child(name).child("congestion").setValue(3);

                        congestion_img.setImageResource(R.drawable.ic_busy_red);
                        break;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void setcondition(int congestion){

        switch (congestion) {
            case 0:
                congestion_img.setImageResource(R.drawable.ic_close_black);
                black.setChecked(true);
                break;
            case 1:
                congestion_img.setImageResource(R.drawable.ic_relax_green);
                green.setChecked(true);
                break;
            case 2:
                congestion_img.setImageResource(R.drawable.ic_soso_yellow);
                yellow.setChecked(true);
                break;
            case 3:
                congestion_img.setImageResource(R.drawable.ic_busy_red);
                red.setChecked(true);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bus_actionbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.bus_info:
                Intent intent =new Intent(BusinessActivity.this, BusAlterActivity.class );
                intent.putExtra("myid",myid);
                startActivity(intent);
                break;
            case android.R.id.home :
                Toast.makeText(BusinessActivity.this,"로그아웃되었습니다.",Toast.LENGTH_SHORT).show();
                finish();
        }

        return super.onOptionsItemSelected(item);
    }





}
