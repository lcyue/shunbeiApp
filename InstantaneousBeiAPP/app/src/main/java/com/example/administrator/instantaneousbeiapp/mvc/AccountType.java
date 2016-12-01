package com.example.administrator.instantaneousbeiapp.mvc;

/**
 * Created by Administrator on 2016/11/29.
 */
public class AccountType {
    String typeImge;
    String typeText;
    double balanceMoney;
    boolean gou;

    public String getTypeImge() {
        return typeImge;
    }

    public void setTypeImge(String typeImge) {
        this.typeImge = typeImge;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public double getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(double balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    public boolean isGou() {
        return gou;
    }

    public void setGou(boolean gou) {
        this.gou = gou;
    }
}
