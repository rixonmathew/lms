package com.rixon.tdd.library.domain;

/**
 * Created by IntelliJ IDEA.
 * User: rixon
 * Date: 17/6/11
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Money {

    private String currencyCode;
    private float amount;

    public Money(String currencyCode, float amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }

    public static Money createMoney(String currencyCode, float amount) {
        return new Money(currencyCode, amount);
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public float getAmount() {
        return amount;
    }
}
