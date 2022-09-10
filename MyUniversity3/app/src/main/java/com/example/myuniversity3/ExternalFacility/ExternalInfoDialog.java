package com.example.myuniversity3.ExternalFacility;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myuniversity3.Information.Shop;
import com.example.myuniversity3.R;

public class ExternalInfoDialog extends Dialog {

    private  TextView name,  address, tel, operatiing, card, cash, reseravtion, takeout, toss, bank;
    private Button ok_btn;
    private ImageButton map_btn;
    private Context context;
    private  Shop shopInfo;

    Intent i;

    public  String intent_name, intent_addr;

//    store = nameformap.getstorename().getText().toString();
//    addr = nameformap.getstorename().getText().toString();

    public ExternalInfoDialog(@NonNull Context context, Shop shopinfo) {
        super(context);
        this.shopInfo =shopinfo;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_info_dialog);

        name =findViewById(R.id.externaldialog_name_tv);
        address= findViewById(R.id.externaldialog_address_tv);
        tel = findViewById(R.id.externaldialog_tel_tv);
        operatiing = findViewById(R.id.externaldialog_operating_tv);
        card = findViewById(R.id.externaldialog_card_tv);
        cash =findViewById(R.id.externaldialog_cash_tv);
        reseravtion = findViewById(R.id.externaldialog_reservation_tv);
        takeout = findViewById(R.id.externaldialog_takeout_tv);
        bank =findViewById(R.id.externaldialog_bank_tv);
        toss =findViewById(R.id.externaldialog_toss_tv);
        ok_btn = findViewById(R.id.externaldialog_ok_btn);
        map_btn = findViewById(R.id.externaldialog_map_btn);

        name.setText(shopInfo.getName());
        address.setText(shopInfo.getAddress());

        intent_name = name.getText().toString();
        intent_addr = name.getText().toString();



        tel.setText(shopInfo.getTel());
        operatiing.setText(shopInfo.getOperatingtime());

        if(shopInfo.isCard()) card.setVisibility(View.VISIBLE);
        else card.setVisibility(View.GONE);

        if(shopInfo.isCash()) cash.setVisibility(View.VISIBLE);
        else cash.setVisibility(View.GONE);

        if(shopInfo.isReservation()) reseravtion.setVisibility(View.VISIBLE);
        else reseravtion.setVisibility(View.GONE);

        if (shopInfo.isTakeout())  takeout.setVisibility(View.VISIBLE);
        else takeout.setVisibility(View.GONE);

        if("not".equals(shopInfo.getToss())) {
            toss.setVisibility(View.GONE);
            bank.setVisibility(View.GONE);
        }else{
            toss.setVisibility(View.VISIBLE);
            bank.setText(shopInfo.getToss());
        }

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx= getContext();
                 i = new Intent(ctx,ExternalInfomap.class);
                //    i.putExtra(intent_name,"intent_name");
                dismiss();
                ctx.startActivity(i);
            }
        });




    }







}