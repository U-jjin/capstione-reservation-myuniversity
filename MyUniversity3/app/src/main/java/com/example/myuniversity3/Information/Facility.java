package com.example.myuniversity3.Information;

import java.io.Serializable;

public class Facility implements Serializable {


    private String operating_time, time_limit;
    private int empty_seat;
    private int total_seat;


    public int getEmpty_seat() {
        return empty_seat;
    }

    public void setEmpty_seat(int empty_seat) {
        this.empty_seat = empty_seat;
    }



    public String getOperating_time() {
        return operating_time;
    }

    public void setOperating_time(String operating_time) {
        this.operating_time = operating_time;
    }

    public String getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(String time_limit) {
        this.time_limit = time_limit;
    }

    public int getTotal_seat() {
        return total_seat;
    }

    public void setTotal_seat(int total_seat) {
        this.total_seat = total_seat;
    }


}
