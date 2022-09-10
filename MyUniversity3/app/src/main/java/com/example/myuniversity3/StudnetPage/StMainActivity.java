package com.example.myuniversity3.StudnetPage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myuniversity3.ExternalFacility.StExternalActivity;
import com.example.myuniversity3.InnerReservation.StInnerActivity;
import com.example.myuniversity3.Login_Join.MainActivity;
import com.example.myuniversity3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class StMainActivity extends AppCompatActivity  implements View.OnClickListener {

    final Context context =this;

    Button inner_btn, external_btn;

    DatabaseReference mdatabase;
    ArrayList<BookingList> bookingLists =new ArrayList<BookingList>();
    ArrayList<String>date_list =new ArrayList<String>();
    String myid;

    DrawerLayout drawerLayout;
    TextView drawer_name, drawer_major, drawer_studentid;
    ListView drawer_reservation_list;

        ListViewAdapter mybooking_adapter;

        String name, major, studentid;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_main);

        drawerLayout =findViewById(R.id.stmain_drawerlayout);
        drawer_name =findViewById(R.id.stmain_drawer_name_tv);
        drawer_major =findViewById(R.id.stmain_drawer_major_tv);
        drawer_studentid =findViewById(R.id.stmain_drawer_studentid_tv);
        drawer_reservation_list =findViewById(R.id.main_drawer_reservationlist);


        inner_btn =findViewById(R.id.innergo_btn);
        external_btn =findViewById(R.id.externalgo_btn);

        Toolbar mytoolbar =findViewById(R.id.stmain_toolbar);
        setSupportActionBar(mytoolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_logout);

        Intent intent =getIntent();
        myid = intent.getStringExtra("myid");

        mdatabase = FirebaseDatabase.getInstance().getReference();

        getlistiteminfo();
        drawer_reservation_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        drawer_reservation_list.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // 제목셋팅
                alertDialogBuilder.setTitle("예약취소");
                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("예약을 취소하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("예",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        mdatabase.child("student").child(myid).child("mybooking").child(bookingLists.get(position).deleteData()).child(bookingLists.get(position).deletePlace()).removeValue();
                                        mdatabase.child("reservation").child(bookingLists.get(position).deleteData()).child(bookingLists.get(position).deletePlace()).child(bookingLists.get(position).deleteTime()).setValue(false);
                                        drawer_reservation_list.clearChoices();
                                        mybooking_adapter.notifyDataSetChanged();

                                    }
                                })
                        .setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });
                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();
                // 다이얼로그 보여주기
                alertDialog.show();

            }
        });

        inner_btn.setOnClickListener(this);
        external_btn.setOnClickListener(this);

    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent intent =new Intent();
        switch(v.getId()){
            case R.id.innergo_btn:
                intent = new Intent(StMainActivity.this, StInnerActivity.class);

                break;
            case R.id.externalgo_btn:

                intent = new Intent(StMainActivity.this, StExternalActivity.class);
                break;
        }
        intent.putExtra("myid",myid);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actionbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.main_info :
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case android.R.id.home :
                Toast.makeText(StMainActivity.this,"로그아웃되었습니다.",Toast.LENGTH_SHORT).show();
                finish();
        }

        return super.onOptionsItemSelected(item);
    }




    public void getlistiteminfo (){
        final Calendar c = Calendar.getInstance();      // 오늘 날짜로 디폴트값을 설정하기 위해 캘린더 객체 선언
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);            // MONTH : 0~11
        int day = c.get(Calendar.DAY_OF_MONTH);
        final int today =year*10000+(month+1)*100+day;
        mdatabase.child("student").child(myid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                name =dataSnapshot.child("name").getValue(String.class);
                studentid = dataSnapshot.child("studentid").getValue(String.class);
                major = dataSnapshot.child("major").getValue(String.class);

                drawer_name.setText(name);
                drawer_major.setText(major);
                drawer_studentid.setText(studentid);

                date_list.clear();
                bookingLists.clear();
                if(dataSnapshot.child("mybooking").exists()) {
                    for (DataSnapshot ds : dataSnapshot.child("mybooking").getChildren()) {
                        String date = ds.getKey();
                        date_list.add(date);
                    }
                    for(int i=0; i<date_list.size(); i++){
                        String date =date_list.get(i);
                        if(today>Integer.parseInt(date)){
                            mdatabase.child("student").child(myid).child(date).removeValue();
                            mdatabase.child("reservation").child(date).removeValue();
                            continue;
                        }
                        for(DataSnapshot ds2: dataSnapshot.child("mybooking").child(date).getChildren()){
                            String place = ds2.getKey();
                            String time = dataSnapshot.child("mybooking").child(date).child(place).child("time").getValue(String.class);
                            BookingList bl = new BookingList(date_list.get(i),place,time);
                            Log.i("changetime", bl.getTime());
                            bookingLists.add(bl);
                        }
                    }
                }
                mybooking_adapter =new ListViewAdapter(bookingLists);
                drawer_reservation_list.setAdapter(mybooking_adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}

