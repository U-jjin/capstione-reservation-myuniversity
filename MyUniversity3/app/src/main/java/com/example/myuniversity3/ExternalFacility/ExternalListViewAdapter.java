package com.example.myuniversity3.ExternalFacility;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myuniversity3.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ExternalListViewAdapter extends BaseAdapter {

    private List<External> externallist;

    public  ExternalListViewAdapter(ArrayList<External> externallist){
        this.externallist =externallist;
    }

    public class ViewHolder{
        TextView type_menu,name;
        ImageView congestion_img;
    }

    @Override
    public int getCount() { return externallist.size() ; }

    @Override
    public Object getItem(int position) {
        return externallist.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder holder =new ViewHolder();
        convertView =LayoutInflater.from(parent.getContext()).inflate(R.layout.external_list_item,parent,false);

            holder.name =convertView.findViewById(R.id.external_name_tv);
            holder.type_menu =convertView.findViewById(R.id.external_type_menu_tv);
            holder.congestion_img =convertView.findViewById(R.id.external_congestion_img);

        switch (externallist.get(position).getCongestion()){
            case 0: holder.congestion_img.setImageResource(R.drawable.ic_close_black); break;
            case 1: holder.congestion_img.setImageResource(R.drawable.ic_relax_green); break;
            case 2: holder.congestion_img.setImageResource(R.drawable.ic_soso_yellow); break;
            case 3: holder.congestion_img.setImageResource(R.drawable.ic_busy_red); break;
        }

        holder.name.setText(externallist.get(position).getName());

        if("음식점".equals(externallist.get(position).getType())){
            holder.type_menu.setText(externallist.get(position).getMenu());
        }else{
            holder.type_menu.setText(externallist.get(position).getType());
        }

        Log.i("finalname",holder.name.getText().toString());
        Log.i("finalmenu",holder.type_menu.getText().toString());
        return convertView;
    }

}
