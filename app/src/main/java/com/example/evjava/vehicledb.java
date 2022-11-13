package com.example.evjava;

public class vehicledb {
    public String uid;
    public String modeltype;
    public String typeofmode;
    public String range;
    public String chargetym;
    public String number;
    public String evtype;

    public vehicledb(String uid, String modeltype, String typeofmode, String range, String chargetym, String number,String evtype) {
        this.uid = uid;
        this.modeltype = modeltype;
        this.typeofmode = typeofmode;
        this.range = range;
        this.chargetym = chargetym;
        this.number = number;
        this.evtype=evtype;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getModeltype() {
        return modeltype;
    }

    public void setModeltype(String modeltype) {
        this.modeltype = modeltype;
    }

    public String getTypeofmode() {
        return typeofmode;
    }

    public void setTypeofmode(String typeofmode) {
        this.typeofmode = typeofmode;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getChargetym() {
        return chargetym;
    }

    public void setChargetym(String chargetym) {
        this.chargetym = chargetym;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEvtype() {
        return evtype;
    }

    public void setEvtype(String evtype) {
        this.evtype = evtype;
    }




}
