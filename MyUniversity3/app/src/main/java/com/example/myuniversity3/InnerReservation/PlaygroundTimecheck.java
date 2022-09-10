package com.example.myuniversity3.InnerReservation;

import java.io.Serializable;

public class PlaygroundTimecheck implements Serializable {

    private boolean time10;
    private boolean time11;
    private boolean time12;
    private boolean time13;
    private boolean time14;
    private boolean time15;

    public PlaygroundTimecheck(){

        time10 =false;
        time11 =false;
        time12 =false;
        time13 =false;
        time14 =false;
        time15 =false;


    }


    public boolean isTime10() {
        return time10;
    }

    public void setTime10(boolean time10) {
        this.time10 = time10;
    }

    public boolean isTime11() {
        return time11;
    }

    public void setTime11(boolean time11) {
        this.time11 = time11;
    }

    public boolean isTime12() {
        return time12;
    }

    public void setTime12(boolean time12) {
        this.time12 = time12;
    }

    public boolean isTime13() {
        return time13;
    }

    public void setTime13(boolean time13) {
        this.time13 = time13;
    }

    public boolean isTime14() {
        return time14;
    }

    public void setTime14(boolean time14) {
        this.time14 = time14;
    }

    public boolean isTime15() {
        return time15;
    }

    public void setTime15(boolean time15) {
        this.time15 = time15;
    }
}
