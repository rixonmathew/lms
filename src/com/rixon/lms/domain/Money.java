package com.rixon.lms.domain;

/**
 * Created by IntelliJ IDEA.
 * User: rixon
 * Date: 17/6/11
 * Time: 11:19 AM
 * Domain class representing Money
 */
public class Money {

    private final String currencyCode;
    private final float amount;

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
