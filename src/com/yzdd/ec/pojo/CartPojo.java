package com.yzdd.ec.pojo;

public class CartPojo {
    private int id;
    private  String name;
    private int num;
    private int sumMoney;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(int sumMoney) {
        this.sumMoney = sumMoney;
    }

    public CartPojo() {
    }

    public CartPojo(int id, String name, int num, int sumMoney) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.sumMoney = sumMoney;
    }
}
