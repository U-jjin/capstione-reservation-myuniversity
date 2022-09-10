package com.example.myuniversity3.UseMethod;

public class DBtimeSearch {

    private String operating;
    private String open;
    private String close;
    private int open_pos;
    private int close_pos;

    public  DBtimeSearch(String operating){
        this.operating = operating;
        int index = operating.indexOf("~");
        this.open =operating.substring(0, index).trim();
        this.close=operating.substring(index+1,operating.length()).trim();
    }

    public String getOpen() {
        String temp = this.open;
        int index = temp.indexOf(":");
        String time = temp.substring(0,index);
        return time;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        String temp = this.close;
        int index = temp.indexOf(":");
        String time = temp.substring(0,index);
        return time;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public int getOpen_pos() {
        switch (open){
            case "00:00" :return 0;
            case "01:00" :return 1;
            case "02:00" :return 2;
            case "03:00" :return 3;
            case "04:00" :return 4;
            case "05:00" :return 5;
            case "06:00" :return 6;
            case "07:00" :return 7;
            case "08:00" :return 8;
            case "09:00" :return 9;
            case "10:00" :return 10;
            case "11:00" :return 11;
            case "12:00" :return 12;
            case "13:00" :return 13;
            case "14:00" :return 14;
            case "15:00" :return 15;
            case "16:00" :return 16;
            case "17:00" :return 17;
            case "18:00" :return 18;
            case "19:00" :return 19;
            case "20:00" :return 20;
            case "21:00" :return 21;
            case "22:00" :return 22;
            case "23:00" :return 23;
            case "24:00" :return 24;
        }


        return 0;
    }

    public void setOpen_pos(int open_pos) {
        this.open_pos = open_pos;
    }

    public int getClose_pos() {
        switch (close){
            case "00:00" :return 0;
            case "01:00" :return 1;
            case "02:00" :return 2;
            case "03:00" :return 3;
            case "04:00" :return 4;
            case "05:00" :return 5;
            case "06:00" :return 6;
            case "07:00" :return 7;
            case "08:00" :return 8;
            case "09:00" :return 9;
            case "10:00" :return 10;
            case "11:00" :return 11;
            case "12:00" :return 12;
            case "13:00" :return 13;
            case "14:00" :return 14;
            case "15:00" :return 15;
            case "16:00" :return 16;
            case "17:00" :return 17;
            case "18:00" :return 18;
            case "19:00" :return 19;
            case "20:00" :return 20;
            case "21:00" :return 21;
            case "22:00" :return 22;
            case "23:00" :return 23;
            case "24:00" :return 24;
        }

        return 0;
    }

    public void setClose_pos(int close_pos) {
        this.close_pos = close_pos;
    }




}
