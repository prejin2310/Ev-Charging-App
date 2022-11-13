package com.example.evjava;

import android.widget.ImageView;

public class dbstation {
   public String stat_name;
    public String stat_desc;
    public String stat_loc;
    public String stat_city;
    public String stat_map;
    public String stat_avail;
    public String stat_type;
    public String stat_kw;
    public String stat_pic;
    public String stat_code;


    public dbstation(){}


    public dbstation(String stat_name, String stat_desc, String stat_loc, String stat_city, String stat_map, String stat_avail,String stat_type,String stat_kw, String stat_pic,String stat_code) {
        this.stat_name = stat_name;
        this.stat_desc = stat_desc;
        this.stat_loc = stat_loc;
        this.stat_city = stat_city;
        this.stat_map = stat_map;
        this.stat_avail = stat_avail;
        this.stat_type = stat_type;
        this.stat_kw = stat_kw;
        this.stat_pic = stat_pic;
        this.stat_code=stat_code;
    }

    public String getStat_name() {
        return stat_name;
    }

    public void setStat_name(String stat_name) {
        this.stat_name = stat_name;
    }

    public String getStat_desc() {
        return stat_desc;
    }

    public void setStat_desc(String stat_desc) {
        this.stat_desc = stat_desc;
    }

    public String getStat_loc() {
        return stat_loc;
    }

    public void setStat_loc(String stat_loc) {
        this.stat_loc = stat_loc;
    }

    public String getStat_city() {
        return stat_city;
    }

    public void setStat_city(String stat_city) {
        this.stat_city = stat_city;
    }

    public String getStat_map() {
        return stat_map;
    }

    public void setStat_map(String stat_map) {
        this.stat_map = stat_map;
    }

    public String getStat_avail() {
        return stat_avail;
    }

    public void setStat_avail(String stat_avail) {
        this.stat_avail = stat_avail;
    }

    public String getStat_pic() {
        return stat_pic;
    }

    public void setStat_pic(String stat_pic) {
        this.stat_pic = stat_pic;
    }

    public String getStat_type() {
        return stat_type;
    }

    public void setStat_type(String stat_type) {
        this.stat_type = stat_type;
    }

    public String getStat_kw() {
        return stat_kw;
    }

    public void setStat_kw(String stat_kw) {
        this.stat_kw = stat_kw;
    }
    public String getStat_code() {
        return stat_code;
    }

    public void setStat_code(String stat_code) {
        this.stat_code = stat_code;
    }




}
