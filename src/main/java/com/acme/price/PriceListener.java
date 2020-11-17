package com.acme.price;

public interface PriceListener {

    /*
    This method listen to price provider Ex SCB and get the
    updated price of a security ex Equity/Bonds
    */
    void priceUpdate(String security, double price);
}
