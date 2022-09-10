package com.example.myuniversity3.ExternalFacility;

public class External {

    private int congestion;
    private String menu;
    private String name;
    private String type;


    public External(String name,int congestion,String menu, String type ) {
        this.congestion =congestion;
        this.menu =menu;
        this.name =name;
        this.type =type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
