package com.example.myuniversity3.InnerReservation;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myuniversity3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManFragment extends Fragment implements View.OnClickListener{
    DatabaseReference mdatabase;
    String dbdate, time, myid;
    StInnerActivity activity;
    private Context context;
    TextView operating_tv;
    TextView timelimit_tv ;
    Button b_00_btn, b_01_btn, b_02_btn, b_03_btn, b_04_btn, b_05_btn, b_06_btn,b_07_btn,b_08_btn;
    TextView booking_tv ;
    String booking;


    public ManFragment() {
        // Required empty public constructor
    }

    public static ManFragment newInstance(String dbdate){
        Bundle args =new Bundle();
        args.putString("dbdate",dbdate);
       ManFragment fragment = new ManFragment();
        fragment.setArguments(args);
        return  fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_man, container, false);

        if(getArguments() !=null){
            dbdate =getArguments().getString("dbdate");
            Log.i("우주사랑",dbdate);
        }


        activity =(StInnerActivity)getActivity();
        myid =activity.getMyid();

        context =container.getContext();

        operating_tv = view.findViewById(R.id.man_operating_tv);
        timelimit_tv = view.findViewById(R.id.man_timelimit_tv);
        booking_tv =view.findViewById(R.id.man_booking_tv);

        b_00_btn =view.findViewById(R.id.man_00_btn);
        b_01_btn =view.findViewById(R.id.man_01_btn);
        b_02_btn =view.findViewById(R.id.man_02_btn);
        b_03_btn =view.findViewById(R.id.man_03_btn);
        b_04_btn =view.findViewById(R.id.man_04_btn);
        b_05_btn =view.findViewById(R.id.man_05_btn);
        b_06_btn =view.findViewById(R.id.man_06_btn);
        b_07_btn =view.findViewById(R.id.man_07_btn);
        b_08_btn =view.findViewById(R.id.man_08_btn);

        mdatabase = FirebaseDatabase.getInstance().getReference();

        mdatabase.child("reservation").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataSnapshot dataSnapshot1 =dataSnapshot.child(dbdate).child("man_lounge");
                boolean  time00 = dataSnapshot1.child("time00").getValue(boolean.class);
                boolean time01 = dataSnapshot1.child("time01").getValue(boolean.class);
                boolean time02 = dataSnapshot1.child("time02").getValue(boolean.class);
                boolean time03 = dataSnapshot1.child("time03").getValue(boolean.class);
                boolean time04 = dataSnapshot1.child("time04").getValue(boolean.class);
                boolean time05 = dataSnapshot1.child("time05").getValue(boolean.class);
                boolean time06 = dataSnapshot1.child("time06").getValue(boolean.class);
                boolean time07 = dataSnapshot1.child("time07").getValue(boolean.class);
                boolean time08 = dataSnapshot1.child("time08").getValue(boolean.class);

                if(time00 ==true){
                    b_00_btn.setEnabled(false);
                    if(b_00_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_00_btn.setTextColor(getResources().getColor(R.color.external_gray));
                        b_00_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                    }}
                if(time01 ==true){
                    b_01_btn.setEnabled(false);
                    if(b_01_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_01_btn.setTextColor(getResources().getColor(R.color.external_gray));
                        b_01_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                    }}
                if(time02 ==true){
                    b_02_btn.setEnabled(false);
                    if(b_02_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_02_btn.setTextColor(getResources().getColor(R.color.external_gray));
                        b_02_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                    }}
                if(time03 ==true){
                    b_03_btn.setEnabled(false);
                    if(b_03_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_03_btn.setTextColor(getResources().getColor(R.color.external_gray));
                        b_03_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                    }}
                if(time04 ==true){
                    b_04_btn.setEnabled(false);
                    if(b_04_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_04_btn.setTextColor(getResources().getColor(R.color.external_gray));
                        b_04_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                    }}
                if(time05 ==true){
                    b_05_btn.setEnabled(false);
                    if(b_05_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_05_btn.setTextColor(getResources().getColor(R.color.external_gray));
                        b_05_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                    }}
                if(time06 ==true){
                    b_06_btn.setEnabled(false);
                    if(b_06_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_06_btn.setTextColor(getResources().getColor(R.color.external_gray));
                        b_06_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                    }}
                if(time07 ==true){
                    b_07_btn.setEnabled(false);
                    if(b_07_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_07_btn.setTextColor(getResources().getColor(R.color.external_gray));
                        b_07_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                    }}
                if(time08==true){
                    b_08_btn.setEnabled(false);
                    if(b_08_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_08_btn.setTextColor(getResources().getColor(R.color.external_gray));
                        b_08_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                    }}
                Log.i("우주사랑2",dbdate);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mdatabase.child("facility").child("Lounge").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                operating_tv.setText(dataSnapshot.child("operating_time").getValue(String.class));
                timelimit_tv.setText(dataSnapshot.child("time_limit").getValue(String.class));
                Log.i("우주사랑2",dbdate);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        mdatabase.child("student").child(myid).child("mybooking").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(dbdate).child("man_lounge").exists()){
                    booking =dataSnapshot.child(dbdate).child("man_lounge").child("time").getValue(String.class);
                    setPink(booking);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        b_00_btn.setOnClickListener(this);
        b_01_btn.setOnClickListener(this);
        b_02_btn.setOnClickListener(this);
        b_03_btn.setOnClickListener(this);
        b_04_btn.setOnClickListener(this);
        b_05_btn.setOnClickListener(this);
        b_06_btn.setOnClickListener(this);
        b_07_btn.setOnClickListener(this);
        b_08_btn.setOnClickListener(this);


        return  view;
    }


    public void setPink(String booking){
        Log.i("dbdate20","dbcheck2");
        switch (booking){
            case "time00": b_00_btn.setBackgroundResource(R.drawable.inner_check_btn_shape); b_00_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time01": b_01_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_01_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time02": b_02_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_02_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time03": b_03_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_03_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time04": b_04_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_04_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time05": b_05_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_05_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time06": b_06_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_06_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time07": b_07_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_07_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time08": b_08_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_08_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
        }
        Log.i("dbdate20","dbcheck3");
        b_00_btn.setEnabled(false);
        b_01_btn.setEnabled(false);
        b_02_btn.setEnabled(false);
        b_03_btn.setEnabled(false);
        b_04_btn.setEnabled(false);
        b_05_btn.setEnabled(false);
        b_06_btn.setEnabled(false);
        b_07_btn.setEnabled(false);
        b_08_btn.setEnabled(false);
        Log.i("dbdate20","dbcheck4");
        booking_tv.setVisibility(View.VISIBLE);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.man_00_btn : time ="time00"; break;
            case R.id.man_01_btn : time ="time01"; break;
            case R.id.man_02_btn : time ="time02"; break;
            case R.id.man_03_btn : time ="time03"; break;
            case R.id.man_04_btn : time ="time04"; break;
            case R.id.man_05_btn : time ="time05"; break;
            case R.id.man_06_btn : time ="time06"; break;
            case R.id.man_07_btn : time ="time07"; break;
            case R.id.man_08_btn : time ="time08"; break;
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // 제목셋팅
        alertDialogBuilder.setTitle("예약");
        // AlertDialog 셋팅
        alertDialogBuilder
                .setMessage("예약 하시겠습니까??")
                .setCancelable(false)
                .setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mdatabase.child("student").child(myid).child("mybooking").child(dbdate).child("man_lounge").child("time").setValue(time);
                                mdatabase.child("reservation").child(dbdate).child("man_lounge").child(time).setValue(true);
                                setPink(time);
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
}
