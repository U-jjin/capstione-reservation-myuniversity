package com.example.myuniversity3.UseMethod;

//디비 디렉토리의 이름을 이메일의 아이디값으로  하기위해 아이디값을 구해주는 클래스

public class DBIDSearch {

    private String email;
    private String ID;

    //생성자
    public DBIDSearch (String email){
        this.email = email;
    }

    public String getID() {
       String tmp_email =this.email;

       int index = tmp_email.indexOf("@");
       this.ID =tmp_email.substring(0, index);

       return ID;
    }





}
