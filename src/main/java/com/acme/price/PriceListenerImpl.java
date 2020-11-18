package com.acme.price;

import com.acme.execution.ExecutionService;

import java.util.Objects;

public class PriceListenerImpl implements PriceListener{

    private final String BUY="BUY";
    private final String SELL="SELL";

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

        if ( BUY.equalsIgnoreCase(tradeType) && security.equals(securityName) && price <= this.pricePoint ) {
            executionService.buy(securityName, price, lotSize);
            priceSource.removePriceListener(this);

        }else if ( SELL.equalsIgnoreCase(tradeType) && security.equals(securityName) && price >= this.pricePoint ) {
            executionService.sell(securityName, price, lotSize);
            priceSource.removePriceListener(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceListenerImpl that = (PriceListenerImpl) o;
        return Double.compare(that.pricePoint, pricePoint) == 0 &&
                lotSize == that.lotSize &&
                Objects.equals(BUY, that.BUY) &&
                Objects.equals(SELL, that.SELL) &&
                Objects.equals(executionService, that.executionService) &&
                Objects.equals(priceSource, that.priceSource) &&
                Objects.equals(securityName, that.securityName) &&
                Objects.equals(tradeType, that.tradeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BUY, SELL, executionService, priceSource, pricePoint, lotSize, securityName, tradeType);
    }
}
