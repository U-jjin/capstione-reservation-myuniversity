package com.example.myuniversity3.Information;

public class Student {

    private String studentid;
    private String name;
    private String major;

    public Student(String studentid, String name, String major){
        this.studentid =studentid;
        this.name =name;
        this.major =major;
    }


    public void SetName(String name){
        this.name = name;
    }
    public  void SetMajor(String major){
        this.major =major;
    }
    public void setStudentid(String studentid) { this.studentid = studentid; }

    public String getStudentid() { return studentid;}
    public String getName(){
        return  name;
    }
    public String getMajor(){
        return  major;
    }


}
