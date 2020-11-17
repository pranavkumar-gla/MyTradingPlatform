##user story
As a trader I want to be able to monitor stock prices such 
that when they breach a trigger level orders can be executed 
automatically.

Q1. How do i continously monitor?

##Note
The implementation of PriceSource and ExecutionService is out of scope,
assuming it will be provided by third party. 

You need to listen to price update from PriceSource and act accordingly.

##Exercise
Given the following interface defination provided  

public interface ExecutionService{
    void buy(String security, double price, int volume);
    void sell(String security, double price, int volume);
}

public interface PriceListener{
    void priceUpdate(String security, double price);
}

public interface PriceSource{
    void addPriceListener(PriceListener listener);
    void removePriceListener(PriceListener listener);
}

Develop a basic implementation of the PriceListener 
interface that provides the following behaviour:

1. connects to a PriceSource instance.
2. Monitors price movement on a specified single stock (e.g. IBM).
3. Execute a single "buy" instruction for a specified number 
   of lots (e.g. 100) as soon as the price of that stock 
   is below a specified price (e.g. 55.0) Don't worry what unit that it is in. 
