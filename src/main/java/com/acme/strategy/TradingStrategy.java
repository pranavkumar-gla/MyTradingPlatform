package com.acme.strategy;

import com.acme.execution.ExecutionService;
import com.acme.price.PriceListenerImpl;
import com.acme.price.PriceListener;
import com.acme.price.PriceSource;

public class TradingStrategy {

    private PriceSource priceSource;
    private ExecutionService executionService;

    public TradingStrategy(PriceSource priceSource, ExecutionService executionService) {
        this.priceSource = priceSource;
        this.executionService = executionService;
    }

    public void processStock(String securityName, Double price, int lotSize, String tradeType) {
        PriceListener priceListener = new PriceListenerImpl(executionService, priceSource, price, lotSize, securityName, tradeType);
        priceSource.addPriceListener(priceListener);
    }

}
