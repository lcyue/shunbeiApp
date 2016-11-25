package com.example.administrator.instantaneousbeiapp.mvc;

/**
 * Created by Administrator on 2016/11/25.
 */
public class WalletTypeItem {
    String type;//类型
    String balance;//余额
    double money;//钱
    String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
