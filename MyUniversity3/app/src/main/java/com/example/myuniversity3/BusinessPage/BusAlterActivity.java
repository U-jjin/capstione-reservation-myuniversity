package com.example.myuniversity3.BusinessPage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myuniversity3.Information.Shop;
import com.example.myuniversity3.R;
import com.example.myuniversity3.UseMethod.DBBankSearch;
import com.example.myuniversity3.UseMethod.DBtimeSearch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BusAlterActivity extends AppCompatActivity {

    private  EditText address, tel , menu, account;
    private   CheckBox takeout, reservation, card, cash, toss;
    private   Spinner open, close, bank;

    private  LinearLayout check_linear , bank_linear ,menu_linear;
    private  Shop shop;
    private  String myid;
    String name;
    DatabaseReference mdatabase;
    private  Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_alter);
        Intent intent =getIntent();
        myid =intent.getStringExtra("myid");

        address = findViewById(R.id.address_alter_et);
        tel =findViewById(R.id.telnum_alter_et);
        menu =findViewById(R.id.menu_alter_et);
        account = findViewById(R.id.alter_account);
        takeout =findViewById(R.id.takeout_alter_btn);
        reservation =findViewById(R.id.reservation_alter_btn);
        card =findViewById(R.id.card_alter_btn);
        cash = findViewById(R.id.cash_alter_btn);
        toss= findViewById(R.id.toss_alter_btn);
        open =findViewById(R.id.open_alter_spinner);
        close=findViewById(R.id.close_alter_spinner);
        bank =findViewById(R.id.alter_bank);

        change= findViewById(R.id.bus_alter_btn);

        check_linear =findViewById(R.id.linear_alter_check);
        bank_linear =findViewById(R.id.linear_alter_bank);
        menu_linear =findViewById(R.id.alter_menu_linear);

        mdatabase = FirebaseDatabase.getInstance().getReference();

        mdatabase.child("restaurant").child(myid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                shop =dataSnapshot.getValue(Shop.class);
                name = shop.getName();
                if( !"음식점".equals(shop.getType())){
                    menu_linear.setVisibility(View.GONE);
                    check_linear.setVisibility(View.GONE);
                    Toast.makeText(BusAlterActivity.this, "ok", Toast.LENGTH_SHORT).show();
                }


                address.setText(shop.getAddress());
                tel.setText(shop.getTel());
                menu.setText(shop.getMenu());

                if(shop.isTakeout()) takeout.setChecked(true);
                if(shop.isReservation()) reservation.setChecked(true);
                if(shop.isCash()) cash.setChecked(true);
                if(shop.isCard()) card.setChecked(true);

                if("not".equals(shop.getToss())) toss.setChecked(false);
                else{  toss.setChecked(true);
                    bank_linear.setVisibility(View.VISIBLE);
                    DBBankSearch dbBankSearch =new DBBankSearch(shop.getToss());
                    bank.setSelection(dbBankSearch.getPosition());
                    account.setText(dbBankSearch.getAccount());
                }

                DBtimeSearch dBtimeSearch = new DBtimeSearch(shop.getOperatingtime());
                open.setSelection(dBtimeSearch.getOpen_pos());
                close.setSelection(dBtimeSearch.getClose_pos());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        toss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toss.isChecked()){
                    bank_linear.setVisibility(View.VISIBLE);
                }else bank_linear.setVisibility(View.GONE);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mdatabase.child("restaurant").child(myid).child("address").setValue(address.getText().toString().trim());
                mdatabase.child("restaurant").child(myid).child("tel").setValue(tel.getText().toString().trim());
                mdatabase.child("restaurant").child(myid).child("operatingtime").setValue((open.getSelectedItem()+"~"+close.getSelectedItem()));
                mdatabase.child("restaurant").child(myid).child("card").setValue(card.isChecked());
                mdatabase.child("restaurant").child(myid).child("cash").setValue(cash.isChecked());


                if(check_linear.getVisibility() == View.VISIBLE){
                    mdatabase.child("restaurant").child(myid).child("menu").setValue(menu.getText().toString().trim());
                    mdatabase.child("external_list").child(name).child("menu").setValue(menu.getText().toString().trim());
                    mdatabase.child("restaurant").child(myid).child("takeout").setValue(takeout.isChecked());
                    mdatabase.child("restaurant").child(myid).child("reservation").setValue(reservation.isChecked());
                } else mdatabase.child("other_list").child(name).child("menu").setValue(menu.getText().toString().trim());


                if(toss.isChecked()){
                    if(bank.isSelected()) mdatabase.child("restaurant").child(myid).child("toss").setValue(bank.getSelectedItem()+":"+account.getText().toString().trim());
                }else{
                    mdatabase.child("restaurant").child(myid).child("toss").setValue("not");
                }


                Toast.makeText(BusAlterActivity.this, "회원정보가 변경되었습니다.",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }
}
