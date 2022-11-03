package com.example.evjava;

public class userdetails {
    public String uid;
    public String name;
    public String mail;
    public String phno;
    public String pass;
    public int usertype;


    public userdetails(String uid, String name, String mail, String phno, String pass, int i) {
        this.uid = uid;
        this.name = name;
        this.mail = mail;
        this.phno = phno;
        this.pass = pass;
        this.usertype=usertype;
    }

    //    getter and setter

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}

