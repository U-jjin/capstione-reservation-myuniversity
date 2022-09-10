package com.example.myuniversity3.ExternalFacility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myuniversity3.Information.Shop;
import com.example.myuniversity3.R;
import com.example.myuniversity3.StudnetPage.BookingList;
import com.example.myuniversity3.StudnetPage.ListViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class StExternalActivity extends AppCompatActivity  implements ListView.OnItemClickListener{

    String myid;

    ListView external_lv, othershop_lv;
    ExternalListViewAdapter adapter, othershop_adapter;
    ArrayList<External> externallist = new ArrayList<External>();
    ArrayList<External> othershoplist = new ArrayList<External>();

    ArrayList<String> other_name_array = new ArrayList<String>();
    ArrayList<String> res_name_array = new ArrayList<String>();
    ArrayList<String> res_id_array = new ArrayList<String>();
    Shop shop_info;
    DatabaseReference externaldb;

    DrawerLayout drawerLayout;
    TextView drawer_name, drawer_major, drawer_studentid;
    ListView drawer_reservation_list;
    ListViewAdapter mybooking_adapter;
    ArrayList<BookingList> bookingLists =new ArrayList<BookingList>();
    ArrayList<String> date_list = new ArrayList<String>();
    Context context =this;

    TextView res_tv, other_tv;
    int cnt;
    LinearLayout res_linear, other_linear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_external);

        Intent intent =getIntent();
        myid = intent.getStringExtra("myid");

        Toolbar mytoolbar =findViewById(R.id.stexternal_toolbar);
        setSupportActionBar(mytoolbar);
        ActionBar actionBar =getSupportActionBar();
       actionBar.setDisplayShowTitleEnabled(false);
       actionBar.setDisplayHomeAsUpEnabled(true);
       actionBar.setHomeAsUpIndicator(R.drawable.ic_home);

        drawer_name =findViewById(R.id.stmain_drawer_name_tv);
        drawer_major =findViewById(R.id.stmain_drawer_major_tv);
        drawer_studentid =findViewById(R.id.stmain_drawer_studentid_tv);
        drawer_reservation_list =findViewById(R.id.main_drawer_reservationlist);
        drawerLayout = findViewById(R.id.st_external_drawerlayout);

        externaldb = FirebaseDatabase.getInstance().getReference();

        externaldb.child("external_list").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String child = ds.getKey();
                    res_name_array.add(child);
                }
                for(int i=0; i< res_name_array.size();i++){
                    ExternalInfo ei = dataSnapshot.child(res_name_array.get(i)).getValue(ExternalInfo.class);
                    External ex = new External(res_name_array.get(i),ei.getCongestion(),ei.getMenu(),ei.getType());
                    externallist.add(ex);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        external_lv = findViewById(R.id.external_listview);
        adapter = new ExternalListViewAdapter(externallist);
        external_lv.setAdapter(adapter);
        external_lv.setOnItemClickListener(this);

        externaldb.child("othershop_list").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String child = ds.getKey();
                    other_name_array.add(child);
                }
                for(int i=0; i< other_name_array.size();i++){
                    ExternalInfo ei = dataSnapshot.child(other_name_array.get(i)).getValue(ExternalInfo.class);
                    External ex = new External(other_name_array.get(i),ei.getCongestion(),ei.getMenu(),ei.getType());
                    othershoplist.add(ex);
                }
                othershop_adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        othershop_lv =findViewById(R.id.external_othershops_listview);
        othershop_adapter =new ExternalListViewAdapter(othershoplist);
        othershop_lv.setAdapter(othershop_adapter);
        othershop_lv.setOnItemClickListener(this);

        cnt =0;
        res_tv =findViewById(R.id.external_restaurant_tv);
        other_tv =findViewById(R.id.external_othershop_tv);
        res_linear =findViewById(R.id.external_reslist_linear);
        other_linear =findViewById(R.id.external_otherlist_linear);

        res_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams resposition;
                LinearLayout.LayoutParams otherposition;
                if (cnt != 1) {
                    cnt=1;
                    resposition = new LinearLayout.LayoutParams(res_linear.getWidth(), 1130);
                    otherposition = new LinearLayout.LayoutParams(other_linear.getWidth(), 410);
                    res_linear.setLayoutParams(resposition);
                    other_linear.setLayoutParams(otherposition);
                } else if (cnt == 1) {
                         cnt=0;
                    resposition = new LinearLayout.LayoutParams(res_linear.getWidth(), 770);
                    otherposition = new LinearLayout.LayoutParams(other_linear.getWidth(), 770);
                    res_linear.setLayoutParams(resposition);
                    other_linear.setLayoutParams(otherposition);
                }
            }
        });

        other_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams resposition;
                LinearLayout.LayoutParams otherposition;
                if(cnt !=2){
                    cnt= 2;
                    resposition = new LinearLayout.LayoutParams(res_linear.getWidth(), 410);
                    otherposition = new LinearLayout.LayoutParams(other_linear.getWidth(), 1130);
                    res_linear.setLayoutParams(resposition);
                    other_linear.setLayoutParams(otherposition);
                }else if (cnt==2){
                    cnt=0;
                    resposition = new LinearLayout.LayoutParams(res_linear.getWidth(), 770);
                    otherposition = new LinearLayout.LayoutParams(other_linear.getWidth(), 770);
                    res_linear.setLayoutParams(resposition);
                    other_linear.setLayoutParams(otherposition);
                }
            }
        });


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
                                        externaldb.child("student").child(myid).child("mybooking").child(bookingLists.get(position).deleteData()).child(bookingLists.get(position).deletePlace()).removeValue();
                                        externaldb.child("reservation").child(bookingLists.get(position).deleteData()).child(bookingLists.get(position).deletePlace()).child(bookingLists.get(position).deleteTime()).setValue(false);
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.external_actionbar, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.external_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("검색");

        ImageView icon = searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        icon.setImageDrawable(getDrawable(R.drawable.ic_search));
//        icon.setColorFilter(getResources().getColor(R.color.colorAccent));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.external_search :
                return true;
            case R.id.external_info:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case android.R.id.home:  finish();
        }
        return super.onOptionsItemSelected(item);
    }






    public void getlistiteminfo (){

        final Calendar c = Calendar.getInstance();      // 오늘 날짜로 디폴트값을 설정하기 위해 캘린더 객체 선언
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);            // MONTH : 0~11
        int day = c.get(Calendar.DAY_OF_MONTH);
        final int today =year*10000+(month+1)*100+day;
        externaldb.child("student").child(myid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name =dataSnapshot.child("name").getValue(String.class);
               String  studentid = dataSnapshot.child("studentid").getValue(String.class);
                String major = dataSnapshot.child("major").getValue(String.class);

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
                            externaldb.child("student").child(myid).child(date).removeValue();
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final External item;
        if(parent.getId()==R.id.external_listview){
            item = (External)adapter.getItem(position);
        }else if (parent.getId() ==R.id.external_othershops_listview){
            item = (External)othershop_adapter.getItem(position);
        }else item =(External) adapter.getItem(position);

        final String itemname = item.getName();
        Log.i("Tag itemname",itemname);

        externaldb.child("restaurant").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String id = ds.getKey();
                    res_id_array.add(id);
                }
                for(int i=0; i<res_id_array.size(); i++){
                    String name = dataSnapshot.child(res_id_array.get(i)).child("name").getValue(String.class);
                    Log.i("Tag name",name);

                    if(name.equals(itemname)){
                        shop_info = dataSnapshot.child(res_id_array.get(i)).getValue(Shop.class);
                        Log.i("Tag tel",shop_info.getTel());
                        break;
                    }
                }
                ExternalInfoDialog infoDialog = new ExternalInfoDialog(StExternalActivity.this,shop_info);
                infoDialog.show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
