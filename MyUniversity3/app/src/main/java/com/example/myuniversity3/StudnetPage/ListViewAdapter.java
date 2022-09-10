package com.example.myuniversity3.StudnetPage;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myuniversity3.R;
import com.example.myuniversity3.StudnetPage.BookingList;
import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<BookingList> mdata;


    public ListViewAdapter(ArrayList<BookingList> mdata){

        this.mdata =mdata;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }


    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView =LayoutInflater.from(parent.getContext()).inflate(R.layout.st_main_item_mybooking,parent,false);

        if(position ==0) convertView.setBackgroundColor(parent.getResources().getColor(R.color.drawer_item_0));
        else if(position ==1) convertView.setBackgroundColor(parent.getResources().getColor(R.color.drawer_item_1));
        else if(position ==2) convertView.setBackgroundColor(parent.getResources().getColor(R.color.drawer_item_2));
        else if(position ==3) convertView.setBackgroundColor(parent.getResources().getColor(R.color.drawer_item_3));
        else if(position ==4) convertView.setBackgroundColor(parent.getResources().getColor(R.color.drawer_item_4));
        else  convertView.setBackgroundColor(parent.getResources().getColor(R.color.drawer_item_5));

        TextView date_tv = convertView.findViewById(R.id.booking_date_tv);
        TextView time_tv = convertView.findViewById(R.id.booking_time_tv);
        TextView classroom_tv = convertView.findViewById(R.id.booking_classroom_tv);

       BookingList bookingList =mdata.get(position);

       date_tv.setText(bookingList.getDate());
       time_tv.setText(bookingList.getTime());
       classroom_tv.setText(bookingList.getPlace());

        return convertView;
    }
}
