package com.example.myuniversity3.Login_Join;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myuniversity3.ExternalFacility.ExternalInfo;
import com.example.myuniversity3.Information.Shop;
import com.example.myuniversity3.R;
import com.example.myuniversity3.UseMethod.DBIDSearch;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BusJoinActivity extends AppCompatActivity {

    EditText email_et, pwd_et, name_et, address_et, menu_et, tel_et, input_account;
    Button join_btn;
    RadioButton type_rest, type_etc;
    CheckBox getTakeout_btn, getReservation_btn;
    CheckBox pay_card_btn, pay_cash_btn, pay_toss_btn;
    Spinner etcshop_type, open_spinner, close_spinner, bank_spinner;
    LinearLayout linearLayout_1, linearLayout_2, menu_linear;

    FirebaseAuth Auth;
    DatabaseReference mDatabase;

    ExternalInfo externalInfo;
    Shop shop;
    DBIDSearch dbidSearch;  //디비에 저장할 디렉토리 이름을 이메일의 아이디 값으로 하기위한 객체
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_bus_join);


        email_et=findViewById(R.id.email_join_et);
        pwd_et =findViewById(R.id.pwd_join_et);
        name_et =findViewById(R.id.name_join_et);
        address_et = findViewById(R.id.address_join_et);
        menu_et = findViewById(R.id.menu_join_et);
        tel_et = findViewById(R.id.telnum_join_et);
        linearLayout_1 =findViewById(R.id.linear_check);
        linearLayout_2 =findViewById(R.id.linear_bank);
        menu_linear = findViewById(R.id.bus_join_menu_linear);

        join_btn =findViewById(R.id.join_join_btn);

        getTakeout_btn =findViewById(R.id.takeout_join_btn);
        getReservation_btn =findViewById(R.id.reservation_join_btn);
        type_rest =findViewById(R.id.type_restaurant_btn);
        type_etc =findViewById(R.id. type_etcshop_btn);

        etcshop_type =findViewById(R.id.type_spinner);
        open_spinner =findViewById(R.id.open_spinner);
        close_spinner = findViewById(R.id.close_spinner);
        bank_spinner =findViewById(R.id.bank);

        pay_card_btn = findViewById(R.id.pay_card_btn);
        pay_cash_btn  = findViewById(R.id.pay_cash_btn);
        pay_toss_btn  = findViewById(R.id.pay_toss_btn);
        input_account = findViewById(R.id.input_account);

        Auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //음식점 , 또는 레스토랑 선택시 입력해야하는 사항만 표시하기 위해
        //기타 업종을 선택할 시에

        type_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type_etc.isChecked() ==true){
                    etcshop_type.setVisibility(View.VISIBLE);
                    menu_linear.setVisibility(View.GONE);
                    linearLayout_1.setVisibility(View.GONE);
                }
            }
        });
        //음식점을 선택할시에
        type_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type_rest.isChecked() ==true){
                    etcshop_type.setVisibility(View.INVISIBLE);
                    menu_linear.setVisibility(View.VISIBLE);
                    linearLayout_1.setVisibility(View.VISIBLE);
                }
            }
        });

        pay_toss_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pay_toss_btn.isChecked() ==true) linearLayout_2.setVisibility(View.VISIBLE);
                else if(pay_toss_btn.isChecked() ==false) linearLayout_2.setVisibility(View.INVISIBLE);
            }
        });

        //회원 가입 버튼 클릭시!
        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //데이터베이스의 데이터를 넣기 위해 shop클래스에 각 값을 넣어주기
                String email = email_et.getText().toString().trim();
                String pwd = pwd_et.getText().toString().trim();
                String address = address_et.getText().toString().trim();
                String menu = menu_et.getText().toString().trim();
                String tel = tel_et.getText().toString().trim();
                String name = name_et.getText().toString().trim();
                final String type =etcshop_type.getSelectedItem().toString().trim();

                String opertationgtime =open_spinner.getSelectedItem().toString()+" ~ "+close_spinner.getSelectedItem().toString();

                boolean reservation = getReservation_btn.isChecked();
                boolean takeout =getTakeout_btn.isChecked();

                boolean rest_check =type_rest.isChecked();
                boolean etc_check = type_etc.isChecked();
                boolean card =pay_card_btn.isChecked();
                boolean cash =pay_cash_btn.isChecked();
                boolean toss = pay_toss_btn.isChecked();

                //디비에 넣을 객체 생성
                if(rest_check ==true) shop = new Shop(address, card, cash, 0, menu, name, opertationgtime, reservation,takeout,tel,"음식점");
                else if(etc_check ==true) shop = new Shop(address,card, cash, 0,"not",name, opertationgtime, false, false, tel, type  );

                //계좌이체 체크 시 은행 과 계좌번호 값 입력하도록
                if(toss==true) shop.setToss(bank_spinner.getSelectedItem().toString().trim()+":"+input_account.getText().toString().trim());
                else shop.setToss("not");

                 externalInfo = new ExternalInfo(shop.getCongestion(),shop.getMenu(),shop.getType());

                dbidSearch = new DBIDSearch(email);

                //db에 회원 이메일과 비밀번호 넣기
                Auth.createUserWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(BusJoinActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(BusJoinActivity.this, "계정 입력 성공",Toast.LENGTH_SHORT).show();

                                     mDatabase.child("restaurant").child(dbidSearch.getID()).setValue(shop);
                                     if(shop.getType() =="음식점") mDatabase.child("external_list").child(shop.getName()).setValue(externalInfo);
                                     else mDatabase.child("othershop_list").child(shop.getName()).setValue(externalInfo);

                                    Intent intent =new Intent(BusJoinActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(BusJoinActivity.this,"비밀번호 또는 이메일을 잘못 입력하셨습니다.",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
            }
        });









    }
}
