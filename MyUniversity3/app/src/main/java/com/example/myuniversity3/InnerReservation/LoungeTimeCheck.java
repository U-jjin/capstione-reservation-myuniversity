package com.example.myuniversity3.InnerReservation;

import java.io.Serializable;

public class LoungeTimeCheck implements Serializable {

    private  boolean time00, time01,time02, time03, time04, time05, time06, time07, time08;

    public LoungeTimeCheck(){

        time00 =false;
        time01 =false;
        time02 =false;
        time03 =false;
        time04 =false;
        time05 =false;
        time06 =false;
        time07 =false;
        time08 =false;



    }


    public boolean isTime00() {
        return time00;
    }

    public void setTime00(boolean time00) {
        this.time00 = time00;
    }

    public boolean isTime01() {
        return time01;
    }

    public void setTime01(boolean time01) {
        this.time01 = time01;
    }

    public boolean isTime02() {
        return time02;
    }

    public void setTime02(boolean time02) {
        this.time02 = time02;
    }

    public boolean isTime03() {
        return time03;
    }

    public void setTime03(boolean time03) {
        this.time03 = time03;
    }

    public boolean isTime04() {
        return time04;
    }

    public void setTime04(boolean time04) {
        this.time04 = time04;
    }

    public boolean isTime05() {
        return time05;
    }

    public void setTime05(boolean time05) {
        this.time05 = time05;
    }

    public boolean isTime06() {
        return time06;
    }

    public void setTime06(boolean time06) {
        this.time06 = time06;
    }

    public boolean isTime07() {
        return time07;
    }

    public void setTime07(boolean time07) {
        this.time07 = time07;
    }

    public boolean isTime08() {
        return time08;
    }

    public void setTime08(boolean time08) {
        this.time08 = time08;
    }
}
