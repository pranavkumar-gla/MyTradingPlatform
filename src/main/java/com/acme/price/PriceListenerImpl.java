package com.acme.price;

import com.acme.execution.ExecutionService;

public class PriceListenerImpl implements PriceListener{

    private ExecutionService executionService;
    private PriceSource priceSource;

    private double pricePoint;
    private int lotSize;
    private String securityName;
    private String tradeType;

    public PriceListenerImpl(ExecutionService executionService,
                             PriceSource priceSource,
                             double pricePoint,
                             int lotSize,
                             String securityName,
                             String tradeType
                                ) {
        this.executionService = executionService;
        this.priceSource = priceSource;
        this.pricePoint = pricePoint; /*price point set by system*/
        this.lotSize = lotSize;
        this.securityName = securityName;
        this.tradeType = tradeType;

    }

    @Override
    public void priceUpdate(String security, double price) {

        if ( "Buy".equalsIgnoreCase(tradeType) && security.equals(securityName) && price <= this.pricePoint ) {
            executionService.buy(securityName, price, lotSize);
            priceSource.removePriceListener(this);

        }else if ( "Sell".equalsIgnoreCase(tradeType) && security.equals(securityName) && price >= this.pricePoint ) {
            executionService.sell(securityName, price, lotSize);
            priceSource.removePriceListener(this);
        }
    }
}
