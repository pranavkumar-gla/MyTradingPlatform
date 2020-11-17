package com.acme.strategy;

import com.acme.execution.ExecutionService;
import com.acme.price.PriceListenerImpl;
import com.acme.price.PriceSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {

    @Mock
    private PriceSource priceSource;
    @Mock
    private ExecutionService executionService;

    private TradingStrategy tradingStrategy;

    @Before
    public void setUp() {
        tradingStrategy = new TradingStrategy(priceSource,executionService);
    }

    @Test
    public void shouldRegisterBuyStrategyRegister() {
        String security = "IBM";
        double stockPrice = 60.0;
        tradingStrategy.processStock(security, stockPrice, 55, "BUY");
        verify(priceSource).addPriceListener(any(PriceListenerImpl.class));
    }

    @Test
    public void shouldRegisterSellStrategyRegister() {
        String security = "IBM";
        double stockPrice = 60.0;
        tradingStrategy.processStock(security, stockPrice, 55, "SELL");
        verify(priceSource).addPriceListener(any(PriceListenerImpl.class));
    }

}