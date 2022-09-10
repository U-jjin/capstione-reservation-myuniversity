package com.example.myuniversity3.ExternalFacility;

public class ExternalInfo {

    private int congestion;
    private String menu, type;



    public ExternalInfo () {}

    public ExternalInfo(int congestion, String menu,String type ){

        this.congestion =congestion;
        this.menu =menu;
        this.type =type;

    }



    public int getCongestion() {
        return congestion;
    }

    public String getMenu() {
        return menu;
    }

    public String getType() {
        return type;
    }

}
