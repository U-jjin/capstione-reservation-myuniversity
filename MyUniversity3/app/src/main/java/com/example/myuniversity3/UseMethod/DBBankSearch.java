package com.example.myuniversity3.UseMethod;

import android.widget.Switch;

public class DBBankSearch {

    private  String toss, bank, account;
    private  int position;

    public  DBBankSearch(String toss){
        this.toss =toss;

        int index = toss.indexOf(":");
        this.bank =toss.substring(0,index);
        this.account =toss.substring(index+1,toss.length()).trim();
    }


    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getPosition() {
        switch(this.bank){
            case "국민은행": return 1;
            case "기업은행": return 2;
            case "농협": return  3;
            case "수협": return  4;
            case "우리은행": return 5;
            case "하나은행": return 6;
        }
        return 0;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
