package com.example.myuniversity3.StudnetPage;

import android.util.Log;

public class BookingList {

    private String date;
    private String time;
    private String place;

    private int year, month, day;
    public BookingList(){}
    public BookingList(String date,String place, String time ){
        int temp =Integer.parseInt(date);
         year = temp/10000;
         month = (temp-year*10000)/100;
         day =temp%100;

        this.date =( year+"."+month+"."+day+".");
        Log.i("bookinglistdate",year+"."+month+"."+day+".");


        switch (place){
            case "soccer": this.place = "축구장";  break;
            case "basketball": this.place = "농구장";  break;
            case "volleyball": this.place = "족구장";  break;
            case "woman_lounge": this.place = "여자휴게실";  break;
            case "man_lounge": this.place = "남자휴게실";  break;
        }

        switch(time){
            case "time00" :
                this.time ="09시~10시";
                break;
            case "time01" :
                this.time ="10시~11시";
                break;
            case "time02" :
                this.time ="11시~12시";
            break;
            case "time03" :
                this.time ="12시~13시";
                break;
            case "time04" :
                this.time ="13시~14시";
                break;
            case "time05" :
                this.time ="14시~15시";
                break;
            case "time06" :
                this.time ="15시~16시";
                break;
            case "time07" :
                this.time ="16시~17시";
                break;
            case "time08" :
                this.time ="17시~18시";
                break;
            case "time10" :
                this.time ="07시~09시";
                break;
            case "time11" :
                this.time ="09시~11시";
                break;
            case "time12" :
                this.time ="11시~13시";
                break;
            case "time13" :
                this.time ="13시~15시";
                break;
            case "time14" :
                this.time ="15시~17시";
                break;
            case "time15" :
                this.time ="17시~19시";
                break;
        }
    }

    public String getDate() {
        return  this.date;
    }

    public String getTime() {
        return  this.time;
        }

    public String getPlace() {
        return this.place;
    }


    public String deleteData() {
        String getdate = String.valueOf(year*10000+month*100+day);
        return getdate;}

    public String deletePlace() {
        String getplace="";
        switch (place){
            case "축구장": getplace = "soccer";  break;
            case "농구장": getplace = "basketball";  break;
            case "족구장": getplace = "volleyball";  break;
            case "여자휴게실": getplace = "woman_lounge";  break;
            case "남자휴게실": getplace = "mon_lounge";  break;
        }
        return getplace;}

    public String deleteTime() {
        String gettime ="";
        switch(time) {
            case "09시~10시":
                gettime = "time00";
                break;
            case "10시~11시":
                gettime = "time01";
                break;
            case "11시~12시":
                gettime = "time02";
                break;
            case "12시~13시":
                gettime = "time03";
                break;
            case "13시~14시":
                gettime = "time04";
                break;
            case "14시~15시":
                gettime = "time05";
                break;
            case "15시~16시":
                gettime = "time06";
                break;
            case "16시~17시":
                gettime = "time07";
                break;
            case "17시~18시":
                gettime = "time08";
                break;
            case "07시~09시":
                gettime = "time10";
                break;
            case "09시~11시":
                gettime = "time11";
                break;
            case "11시~13시":
                gettime = "time12";
                break;
            case "13시~15시":
                gettime = "time13";
                break;
            case "15시~17시":
                gettime = "time14";
                break;
            case "17시~19시":
                gettime = "time15";
                break;
        }
        return gettime; }

    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setPlace(String place) { this.place = place; }


}
