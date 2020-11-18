package com.acme.price;

import com.acme.execution.ExecutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PriceListenerImplTest {

    @Mock
    ExecutionService executionService;
    @Mock
    PriceSource priceSource;
    PriceListener priceListener;


    @Test
    public void shouldExecuteBuy_UpdatedPriceIsLessThanPricePointSet() {
        double updatePriceFromSCB = 50;
        priceListener =
                new PriceListenerImpl(executionService, priceSource, 55,
                        100, "IBM", "BUY");

        priceListener.priceUpdate("IBM", updatePriceFromSCB); //price update from SCB is 50 for IBM
        Mockito.verify(executionService).buy("IBM", updatePriceFromSCB, 100);
        Mockito.verify(priceSource).removePriceListener(priceListener);
    }

    @Test
    public void shouldNotExecuteBuy_UpdatedPriceIsGreaterThanPricePointSet() {
        double updatePriceFromSCB = 70;
        priceListener =
                new PriceListenerImpl(executionService, priceSource, 55,
                        100, "IBM", "BUY");

        priceListener.priceUpdate("IBM", updatePriceFromSCB);//price update from SCB is 70 for IBM
        Mockito.verify(executionService,
                Mockito.times(0)).buy("IBM", updatePriceFromSCB, 100);

        Mockito.verify(priceSource,
                Mockito.times(0)).removePriceListener(priceListener);
    }

    @Test
    public void shouldExecuteSell_UpdatedPriceIsGreaterThanPricePointSet() {
        double updatePriceFromSCB = 70;
        priceListener =
                new PriceListenerImpl(executionService, priceSource, 50,
                        100, "IBM", "SELL");

        priceListener.priceUpdate("IBM", updatePriceFromSCB); //price update from SCB is 70 for IBM
        Mockito.verify(executionService).sell("IBM", updatePriceFromSCB, 100);
        Mockito.verify(priceSource).removePriceListener(priceListener);
    }

    @Test
    public void shouldNotExecuteSell_UpdatedPriceIsLessThanPricePointSet() {
        double updatePriceFromSCB = 50;
        priceListener =
                new PriceListenerImpl(executionService, priceSource, 55,
                        100, "IBM", "SELL");

        priceListener.priceUpdate("IBM", updatePriceFromSCB);//price update from SCB is 50 for IBM
        Mockito.verify(executionService,
                Mockito.times(0)).sell("IBM", updatePriceFromSCB, 100);

        Mockito.verify(priceSource,
                Mockito.times(0)).removePriceListener(priceListener);
    }
}