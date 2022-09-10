package com.example.myuniversity3.Login_Join;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myuniversity3.Information.Student;
import com.example.myuniversity3.R;
import com.example.myuniversity3.UseMethod.DBIDSearch;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StJoinActivity extends AppCompatActivity {


    EditText semail_et, sname_et, studentid_et,spwd_et;
    Button sjoin_btn;
    Spinner major_spinner;

    Student student;
    DBIDSearch dbidSearch;  //디비에 저장할 디렉토리 이름을 이메일의 아이디 값으로 하기위한 객체

    FirebaseAuth mAuth;
    DatabaseReference mDatabase ;

    //입력받은 값을 데이터베이스에 넘기고  Student 아이디와 비밀번호 승인 받는 이벤트
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_join);

        semail_et=findViewById(R.id.semail_et);
        sname_et =findViewById(R.id.sname_et);
        studentid_et =findViewById(R.id.studentid_et);
        spwd_et =findViewById(R.id.spwd_et);
        major_spinner =findViewById(R.id.major_spinner);

        sjoin_btn =findViewById(R.id.sjoin_btn);

        mAuth =FirebaseAuth.getInstance();
        mDatabase =FirebaseDatabase.getInstance().getReference();

        sjoin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail =semail_et.getText().toString().trim();
                String spwd = spwd_et.getText().toString().trim();
                String sname =sname_et.getText().toString().trim();
                String studentid =studentid_et.getText().toString().trim();
                String major =major_spinner.getSelectedItem().toString().trim();

                dbidSearch = new DBIDSearch(semail);

                student = new Student(studentid,sname,major);


                    mAuth.createUserWithEmailAndPassword(semail, spwd).addOnCompleteListener(StJoinActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    try {
                                        mDatabase.child("student").child(dbidSearch.getID()).child("name").setValue(student.getName());
                                        mDatabase.child("student").child(dbidSearch.getID()).child("major").setValue(student.getMajor());
                                        mDatabase.child("student").child(dbidSearch.getID()).child("studentid").setValue(student.getStudentid());

                                    }catch (Exception e){Toast.makeText(StJoinActivity.this, "데이터 넣기 실패",Toast.LENGTH_SHORT).show();}

                                    Intent intent = new Intent(StJoinActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(StJoinActivity.this, "계정 가입성공", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{ Toast.makeText(StJoinActivity.this, "이메일 또는 비밀번호를 잘못 입력하였습니다.", Toast.LENGTH_SHORT).show(); }

                            }

                    });
            }
        });
    }
}
