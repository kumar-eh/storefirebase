package com.example.storefirebase;

public class UserHelper{

    String name , voterid , pwd , phone , mail;

    public UserHelper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVoterid() {
        return voterid;
    }

    public void setVoterid(String voterid) {
        this.voterid = voterid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserHelper(String name, String voterid, String pwd, String phone, String mail) {
        this.name = name;
        this.voterid = voterid;
        this.pwd = pwd;
        this.phone = phone;
        this.mail = mail;
    }
}
