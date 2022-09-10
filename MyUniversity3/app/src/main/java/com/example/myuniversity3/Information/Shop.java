package com.example.myuniversity3.Information;

public class Shop {
    private String address;
    private boolean card;
    private boolean cash;
    private int congestion;
    private String menu;
    private String name;
    private  String operatingtime;
    private boolean reservation;
    private boolean takeout;
    private String tel;
    private String toss;
    private String  type;


    public Shop(){}

    public Shop(String address, boolean card, boolean cash, int congestion, String menu ,String name, String operatingtime, boolean reservation ,boolean takeout, String tel,String toss,String type  ){

        this.address =address;
        this.card =card;
        this.cash =cash;
        this.congestion =congestion;
        this.menu =menu;
        this.name =name;
        this. operatingtime = operatingtime;
        this.reservation =reservation;
        this.takeout =takeout;
        this.tel =tel;
        this.toss =toss;
        this.type =type;
    }


    public Shop(String address, boolean card, boolean cash, int congestion, String menu ,String name, String operatingtime, boolean reservation ,boolean takeout, String tel,String type  ){

        this.address =address;
        this.card =card;
        this.cash =cash;
        this.congestion =congestion;
        this.menu =menu;
        this.name =name;
        this. operatingtime = operatingtime;
        this.reservation =reservation;
        this.takeout =takeout;
        this.tel =tel;
        this.type =type;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCard() {
        return card;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public int getCongestion() {
        return congestion;
    }

    public void setCongestion(int congestion) {
        this.congestion = congestion;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatingtime() {
        return operatingtime;
    }

    public void setOperatingtime(String operatingtime) {
        this.operatingtime = operatingtime;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public boolean isTakeout() {
        return takeout;
    }

    public void setTakeout(boolean takeout) {
        this.takeout = takeout;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getToss() {
        return toss;
    }

    public void setToss(String toss) {
        this.toss = toss;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
