package com.example.evjava;

public class bookdb {
    public String uid;
    public String sname;
    public String stloc;
    public String stcity;
    public String sttype;
    public String time;
    public String date;
    public String pend;

    public bookdb(){
    }

    public bookdb(String uid,String sname, String stloc, String stcity, String sttype, String time, String date,String pend){
        this.uid = uid;
        this.sname=sname;
        this.stloc=stloc;
        this.stcity=stcity;
        this.sttype=sttype;
        this.time=time;
        this.date=date;
        this.pend=pend;

    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getStloc() {
        return stloc;
    }

    public void setStloc(String stloc) {
        this.stloc = stloc;
    }

    public String getStcity() {
        return stcity;
    }

    public void setStcity(String stcity) {
        this.stcity = stcity;
    }

    public String getSttype() {
        return sttype;
    }

    public void setSttype(String sttype) {
        this.sttype = sttype;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPend() {
        return pend;
    }

    public void setPend(String pend) {
        this.pend = pend;
    }


}
