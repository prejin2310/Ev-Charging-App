package com.example.evjava;

public class user {
    public String uid;
    public String name;
    public String mail;
    public String phno;
    public String pass;
    public int usertype;
    public user(){

    }

    public user(String Uid,String name, String email,int usertype) {
        this.uid = uid;
        this.name = name;
        this.mail = mail;
        this.phno = phno;
        this.pass = pass;
        this.usertype=usertype;
    }


}
