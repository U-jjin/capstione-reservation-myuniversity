package com.example.myuniversity3.InnerReservation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myuniversity3.Information.Facility;
import com.example.myuniversity3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadingroomFragment extends Fragment {

    DatabaseReference mdatabase;
    Facility r1, r2, multi;
    TextView r1_operating, r1_timelimit, r1_totalseat, r1_emptyseat;
    TextView r2_operating, r2_timelimit, r2_totalseat, r2_emptyseat;
    TextView mul_operating, mul_timelimit, mul_totalseat, mul_emptyseat;


    public ReadingroomFragment() {
        // Required empty public constructor
    }
    public static ReadingroomFragment newInstance(){
        Bundle args =new Bundle();
        ReadingroomFragment fragment = new ReadingroomFragment();
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_readingroom, container, false);

       r1_operating =view.findViewById(R.id.r1_operating_tv);
       r1_timelimit =view.findViewById(R.id.r1_timelimit_tv);
       r1_totalseat =view.findViewById(R.id.r1_totalseat_tv);
       r1_emptyseat =view.findViewById(R.id.r1_emptyseat_tv);

        r2_operating =view.findViewById(R.id.r2_operating_tv);
        r2_timelimit =view.findViewById(R.id.r2_timelimit_tv);
        r2_totalseat =view.findViewById(R.id.r2_totalseat_tv);
        r2_emptyseat =view.findViewById(R.id.r2_emptyseat_tv);

        mul_operating =view.findViewById(R.id.mul_operating_tv);
        mul_timelimit =view.findViewById(R.id.mul_timelimit_tv);
        mul_totalseat =view.findViewById(R.id.mul_totalseat_tv);
        mul_emptyseat =view.findViewById(R.id.mul_emptyseat_tv);

        mdatabase = FirebaseDatabase.getInstance().getReference();
       mdatabase.child("facility").addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               r1 =dataSnapshot.child("Readingroom1").getValue(Facility.class);
               r2 =dataSnapshot.child("Readingroom2").getValue(Facility.class);
               multi =dataSnapshot.child("Multimediaroom").getValue(Facility.class);

               r1_operating.setText(r1.getOperating_time());
               r1_timelimit.setText(r1.getTime_limit());
               r1_totalseat.setText(String.valueOf(r1.getTotal_seat()));
               r1_emptyseat.setText(String.valueOf(r1.getEmpty_seat()));

               r2_operating.setText(r2.getOperating_time());
               r2_timelimit.setText(r2.getTime_limit());
               r2_totalseat.setText(String.valueOf(r2.getTotal_seat()));
               r2_emptyseat.setText(String.valueOf(r2.getEmpty_seat()));

               mul_operating.setText(multi.getOperating_time());
               mul_timelimit.setText(multi.getTime_limit());
               mul_totalseat.setText(String.valueOf(multi.getTotal_seat()));
               mul_emptyseat.setText(String.valueOf(multi.getTotal_seat()));
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });


       return view;
    }




}
