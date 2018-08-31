package com.pactera.customercenter.domain;


import java.io.Serializable;

public class Staff implements Serializable {

          private String cid;
          private String name;
          private int age;
          private String sex;
          private String telno;   //手机号
          private String headPortrait;   //头像地址
          private String pushTuken;     //推送标识
          private String channel;       //推荐渠道
          private String pass;       //密码
          private  String refereeID;//推荐人

    public String getRefereeID() {
        return refereeID;
    }

    public void setRefereeID(String refereeID) {
        this.refereeID = refereeID;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getPushTuken() {
        return pushTuken;
    }

    public void setPushTuken(String pushTuken) {
        this.pushTuken = pushTuken;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int  getAge() {
        return age;
    }

    public void setAge(int  age) {
        this.age = age;
    }




}
