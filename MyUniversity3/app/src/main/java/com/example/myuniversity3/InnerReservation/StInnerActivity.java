package com.example.myuniversity3.InnerReservation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myuniversity3.R;
import com.example.myuniversity3.StudnetPage.BookingList;
import com.example.myuniversity3.StudnetPage.ListViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class StInnerActivity extends AppCompatActivity {

    DatabaseReference mdatabase, reservationdb;

    final Context context =this;
    ArrayList<BookingList> bookingLists =new ArrayList<BookingList>();
    ArrayList<String>date_list =new ArrayList<String>();
    DrawerLayout drawerLayout;
    TextView drawer_name, drawer_major, drawer_studentid;
    ListView drawer_reservation_list;

    ListViewAdapter mybooking_adapter;
    String name, major, studentid;

    public String getMyid() {
        return myid;
    }

    public void setMyid(String myid) {
        this.myid = myid;
    }
    public String getDbdate() { return dbdate; }

    public void setDbdate(String dbdate) { this.dbdate = dbdate; }

    private String myid;

    TabLayout tabLayout;
    ViewPager viewPager;
    InnerPagerAdapter innerPagerAdapter;

    ImageButton date_btn;
    TextView date_tv;
    private  String dbdate;
    String check_today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_inner);

        Intent intent =getIntent();
        myid =intent.getStringExtra("myid");

        Toolbar mytoolbar =findViewById(R.id.stinner_toolbar);
        setSupportActionBar(mytoolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home);

        drawerLayout =findViewById(R.id.st_inner_drawerlayout);
        drawer_name =findViewById(R.id.stmain_drawer_name_tv);
        drawer_major =findViewById(R.id.stmain_drawer_major_tv);
        drawer_studentid =findViewById(R.id.stmain_drawer_studentid_tv);
        drawer_reservation_list =findViewById(R.id.main_drawer_reservationlist);

        mdatabase = FirebaseDatabase.getInstance().getReference();
        reservationdb = mdatabase.child("reservation");

        //datepicker
        date_btn =findViewById(R.id.date_btn);
        date_tv =findViewById(R.id.date_tv);

        final Calendar c = Calendar.getInstance();      // 오늘 날짜로 디폴트값을 설정하기 위해 캘린더 객체 선언
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);            // MONTH : 0~11
        int day = c.get(Calendar.DAY_OF_MONTH);
        final SimpleDateFormat format =new SimpleDateFormat("yyyymmdd");
        check_today =String.valueOf(year*10000+(month+1)*100+day);
        String print_today = year+"년 "+(month+1)+"월 "+day+"일";
        dbdate =check_today;
        date_tv.setText(print_today);
        dbcheck(check_today);

        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker"); }
        });
        innerPagerAdapter =new InnerPagerAdapter(getSupportFragmentManager(),dbdate);
        viewPager =findViewById(R.id.inner_viewpager);
        viewPager.setAdapter(innerPagerAdapter);
        tabLayout= findViewById(R.id.inner_layout_tab);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_football_p);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_basketball);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_volleyball);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_man);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_woman);
        tabLayout.getTabAt(5).setIcon(R.drawable.ic_readingroom);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {

                tabLayout.getTabAt(0).setIcon(R.drawable.ic_football);
                tabLayout.getTabAt(1).setIcon(R.drawable.ic_basketball);
                tabLayout.getTabAt(2).setIcon(R.drawable.ic_volleyball);
                tabLayout.getTabAt(3).setIcon(R.drawable.ic_man);
                tabLayout.getTabAt(4).setIcon(R.drawable.ic_woman);
                tabLayout.getTabAt(5).setIcon(R.drawable.ic_readingroom);

                switch(i){
                    case 0:  tabLayout.getTabAt(0).setIcon(R.drawable.ic_football_p); break;
                    case 1:   tabLayout.getTabAt(1).setIcon(R.drawable.ic_basketball_p); break;
                    case 2:   tabLayout.getTabAt(2).setIcon(R.drawable.ic_volleyball_p); break;
                    case 3:   tabLayout.getTabAt(3).setIcon(R.drawable.ic_man_p); break;
                    case 4:   tabLayout.getTabAt(4).setIcon(R.drawable.ic_woman_p); break;
                    case 5:    tabLayout.getTabAt(5).setIcon(R.drawable.ic_readingroom_p); break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
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
                                        mdatabase.child("student").child(myid).child("mybooking").child(bookingLists.get(position).deleteData()).child(bookingLists.get(position).deletePlace()).removeValue();
                                        mdatabase.child("reservation").child(bookingLists.get(position).deleteData()).child(bookingLists.get(position).deletePlace()).child(bookingLists.get(position).deleteTime()).setValue(false);
                                        drawer_reservation_list.clearChoices();
                                        mybooking_adapter.notifyDataSetChanged();
                                       frame_refresh();
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
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void dbcheck(String date) {
        this.dbdate =date;

        Log.i("dbdate2",dbdate);
        reservationdb.child(dbdate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(! dataSnapshot.exists()){
                    PlaygroundTimecheck pgtc = new PlaygroundTimecheck();
                    LoungeTimeCheck ltc = new LoungeTimeCheck();
                    reservationdb.child(dbdate).child("soccer").setValue(pgtc);
                    reservationdb.child(dbdate).child("basketball").setValue(pgtc);
                    reservationdb.child(dbdate).child("volleyball").setValue(pgtc);
                    reservationdb.child(dbdate).child("man_lounge").setValue(ltc);
                    reservationdb.child(dbdate).child("woman_lounge").setValue(ltc);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

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

    public void refresh(){
        innerPagerAdapter=new InnerPagerAdapter(getSupportFragmentManager(),dbdate);
                viewPager.setAdapter(innerPagerAdapter);
    }

    public void frame_refresh(){
        innerPagerAdapter.notifyDataSetChanged();
    }

}

