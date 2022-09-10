package com.example.myuniversity3.Login_Join;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myuniversity3.BusinessPage.BusinessActivity;
import com.example.myuniversity3.ExternalFacility.StExternalActivity;
import com.example.myuniversity3.R;
import com.example.myuniversity3.StudnetPage.StMainActivity;
import com.example.myuniversity3.UseMethod.DBIDSearch;
import com.example.myuniversity3.UseMethod.DBtimeSearch;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button join_btn, login_btn ,stjoin_btn;
    EditText id_et, pwd_et;
    Intent login_intent;
    RadioGroup rg;
    RadioButton st_rb, bus_rb;

    DBIDSearch dbidSearch;
    ArrayList<String> id_array = new ArrayList<String>();
    ArrayList<String> name_array = new ArrayList<String>();
    ArrayList<String>type_array = new ArrayList<String>();
    ArrayList<String> operating_array = new ArrayList<String>();

    private FirebaseAuth firebaseAuth;

    private DatabaseReference checkiddb;
    String idtype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        join_btn =findViewById(R.id.join_btn);
        login_btn = findViewById(R.id.login_btn);
        stjoin_btn =findViewById(R.id.stjoin_btn);
        id_et =findViewById(R.id.id_et);
        pwd_et = findViewById(R.id.pwd_et);

        st_rb = findViewById(R.id.st_rb);
        bus_rb =findViewById(R.id.bus_rb);
        rg =findViewById(R.id.people_type_rg);

        firebaseAuth = FirebaseAuth.getInstance();
        checkiddb = FirebaseDatabase.getInstance().getReference();

        setcongestion();

        stjoin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stjoin_intent =new Intent(MainActivity.this, StJoinActivity.class);
                startActivity(stjoin_intent);
            }
        });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent join_intent =new Intent(MainActivity.this, BusJoinActivity.class);
                startActivity(join_intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =id_et.getText().toString().trim();
                String pwd =pwd_et.getText().toString().trim();

                if(email ==null || pwd==null) {
                    Toast.makeText(MainActivity.this,"이메일 또는 비밀번호를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else{
                    dbidSearch=new DBIDSearch(email);

                    final String myid = dbidSearch.getID();

                    checkiddb.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("student").child(myid).exists()) idtype ="student";
                            else if(dataSnapshot.child("restaurant").child(myid).exists()) idtype ="business";
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    firebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                id_et.setText(null);
                                pwd_et.setText(null);
                                if (st_rb.isChecked()==true) {
                                    if(idtype =="student") {
                                        login_intent = new Intent(MainActivity.this, StMainActivity.class);
                                        login_intent.putExtra("myid",myid);
                                        startActivity(login_intent);
                                    }
                                    else Toast.makeText(MainActivity.this, "학생회원이 아닙니다.",Toast.LENGTH_SHORT).show();

                                } else if (bus_rb.isChecked() ==true) {
                                    if(idtype=="business"){
                                        login_intent = new Intent(MainActivity.this, BusinessActivity.class);
                                        login_intent.putExtra("myid",myid);
                                        startActivity(login_intent);
                                    } else  Toast.makeText(MainActivity.this, "업주회원이 아닙니다.",Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }

    public void setcongestion(){
        final Calendar c = Calendar.getInstance();      // 오늘 날짜로 디폴트값을 설정하기 위해 캘린더 객체 선언
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);            // MONTH : 0~11
        int day = c.get(Calendar.DAY_OF_MONTH);
        final int check_today =year*10000+(month+1)*100+day;

         final int hour =c.get(Calendar.HOUR);

        checkiddb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds1: dataSnapshot.child("reservation").getChildren()) {
                        String indate =ds1.getKey();
                        if(Integer.parseInt(indate)<check_today) checkiddb.child("reservation").child(indate).removeValue();
                }




                영업시간별로 영업준비중 표시를 위한 소스
                for(DataSnapshot ds: dataSnapshot.child("restaurant").getChildren()){
                    id_array.add(ds.getKey());
                    name_array.add(dataSnapshot.child(ds.getKey()).child("name").getValue(String.class));
                    operating_array.add(dataSnapshot.child(ds.getKey()).child("operatingtime").getValue(String.class));
                    type_array.add(dataSnapshot.child(ds.getKey()).child("type").getValue(String.class));
                }

                for(int i=0; i<id_array.size(); i++) {
                    int check =0;
                    DBtimeSearch dBtimeSearch = new DBtimeSearch(operating_array.get(i));
                    int close =Integer.valueOf(dBtimeSearch.getClose()) ;
                    int open =Integer.valueOf(dBtimeSearch.getOpen()) ;

                    if (close < 12 ) {
                        if ((open > hour) && (close <= hour)) check = 1;
                    } else if (close >= 12) {
                        if ((open>hour)||(close <= hour)) check = 1;
                    }

                    if (check ==1){
                        checkiddb.child("restaurant").child(id_array.get(i)).child("congestion").setValue(0);
                        if (type_array.get(i) == "음식점")
                            checkiddb.child("external_list").child(name_array.get(i)).child("congestion").setValue(0);
                        else
                            checkiddb.child("othershop_list").child(name_array.get(i)).child("congestion").setValue(0);
                    }
                }



            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
