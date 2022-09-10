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

public class BasketballFragment extends Fragment implements View.OnClickListener {
    DatabaseReference mdatabase;
    String dbdate, time, myid;
    StInnerActivity activity;
    private Context context;
    TextView operating_tv;
    TextView timelimit_tv ;
    Button b_10_btn, b_11_btn, b_12_btn, b_13_btn, b_14_btn, b_15_btn;
    TextView booking_tv ;
    String booking;
    public   BasketballFragment() {
        // Required empty public constructor
    }

    public static BasketballFragment newInstance(String dbdate){
        Bundle args =new Bundle();
        args.putString("dbdate",dbdate);
        BasketballFragment basketballfragment =new BasketballFragment();
        basketballfragment.setArguments(args);
        return  basketballfragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basketball, container, false);

        if(getArguments() !=null){
            dbdate =getArguments().getString("dbdate");
            Log.i("우주사랑",dbdate);
        }

         activity =(StInnerActivity)getActivity();
        myid =activity.getMyid();

        context =container.getContext();

        operating_tv = view.findViewById(R.id.basketball_operating_tv);
         timelimit_tv = view.findViewById(R.id.basketball_timelimit_tv);
         booking_tv =view.findViewById(R.id.basketball_booking_tv);

        b_10_btn =view.findViewById(R.id.basketball_10_btn);
        b_11_btn =view.findViewById(R.id.basketball_11_btn);
        b_12_btn =view.findViewById(R.id.basketball_12_btn);
        b_13_btn =view.findViewById(R.id.basketball_13_btn);
        b_14_btn =view.findViewById(R.id.basketball_14_btn);
        b_15_btn =view.findViewById(R.id.basketball_15_btn);

        mdatabase =FirebaseDatabase.getInstance().getReference();


        mdatabase.child("reservation").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("dbdate15","soccer");

                    DataSnapshot dataSnapshot1 =dataSnapshot.child(dbdate).child("basketball");
                    boolean  time10 = dataSnapshot1.child("time10").getValue(boolean.class);
                    boolean time11 = dataSnapshot1.child("time11").getValue(boolean.class);
                    boolean time12 = dataSnapshot1.child("time12").getValue(boolean.class);
                    boolean time13 = dataSnapshot1.child("time13").getValue(boolean.class);
                    boolean time14 = dataSnapshot1.child("time14").getValue(boolean.class);
                    boolean time15 = dataSnapshot1.child("time15").getValue(boolean.class);


                if(time10 ==true){
                    b_10_btn.setEnabled(false);
                    if(b_10_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_10_btn.setTextColor(getResources().getColor(R.color.external_gray));
                        b_10_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                    } }
                if(time11 ==true){
                    b_11_btn.setEnabled(false);
                    if(b_11_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_11_btn.setTextColor(getResources().getColor(R.color.external_gray));
                        b_11_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                    } }

                if(time12 ==true){
                    b_12_btn.setEnabled(false);
                    if(b_12_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_12_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                        b_12_btn.setTextColor(getResources().getColor(R.color.external_gray));
                    } }
                if(time13 ==true){
                    b_13_btn.setEnabled(false);
                    if(b_13_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_13_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                        b_13_btn.setTextColor(getResources().getColor(R.color.external_gray));
                    } }
                if(time14 ==true){
                    b_14_btn.setEnabled(false);
                    if(b_14_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)) {
                        b_14_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                        b_14_btn.setTextColor(getResources().getColor(R.color.external_gray));
                    } }
                if(time15 ==true){
                    b_15_btn.setEnabled(false);
                    if(b_15_btn.getCurrentTextColor()!=getResources().getColor(R.color.external_point)){
                        b_15_btn.setBackgroundResource(R.drawable.inner_btn_enable);
                        b_15_btn.setTextColor(getResources().getColor(R.color.external_gray));
                    } }
                Log.i("우주사랑2",dbdate);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mdatabase.child("facility").child("Playground").addListenerForSingleValueEvent(new ValueEventListener() {
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
                if(dataSnapshot.child(dbdate).child("basketball").exists()){
                   booking =dataSnapshot.child(dbdate).child("basketball").child("time").getValue(String.class);
                   setPink(booking);
                    Log.i("우주사랑2",booking);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



        b_10_btn.setOnClickListener(this);
        b_11_btn.setOnClickListener(this);
        b_12_btn.setOnClickListener(this);
        b_13_btn.setOnClickListener(this);
        b_14_btn.setOnClickListener(this);
        b_15_btn.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.basketball_10_btn : time ="time10"; break;
            case R.id.basketball_11_btn : time ="time11"; break;
            case R.id.basketball_12_btn : time ="time12"; break;
            case R.id.basketball_13_btn : time ="time13"; break;
            case R.id.basketball_14_btn : time ="time14"; break;
            case R.id.basketball_15_btn : time ="time15"; break;
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
                                mdatabase.child("student").child(myid).child("mybooking").child(dbdate).child("basketball").child("time").setValue(time);
                                mdatabase.child("reservation").child(dbdate).child("basketball").child(time).setValue(true);
                                Log.i("dbdate20","dbcheck");
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

    public void setPink(String booking){
        Log.i("dbdate20","dbcheck2");
        switch (booking){
            case "time10": b_10_btn.setBackgroundResource(R.drawable.inner_check_btn_shape); b_10_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time11": b_11_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_11_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time12": b_12_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_12_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time13": b_13_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_13_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time14": b_14_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_14_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
            case "time15": b_15_btn.setBackgroundResource(R.drawable.inner_check_btn_shape);b_15_btn.setTextColor(getResources().getColor(R.color.external_point)); break;
        }
        Log.i("dbdate20","dbcheck3");
        b_10_btn.setEnabled(false);
        b_11_btn.setEnabled(false);
        b_12_btn.setEnabled(false);
        b_13_btn.setEnabled(false);
        b_14_btn.setEnabled(false);
        b_15_btn.setEnabled(false);
        Log.i("dbdate20","dbcheck4");
        booking_tv.setVisibility(View.VISIBLE);
    }


}
