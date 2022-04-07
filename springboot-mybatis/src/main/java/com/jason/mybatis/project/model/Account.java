package com.jason.mybatis.project.model;

/**
 * @author WongChenHoll
 * @date 2022-4-7 16:06
 **/
public class Account {
    private int id;
    private String name;
    private double money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
