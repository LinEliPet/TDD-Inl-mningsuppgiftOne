package org.example;

public class PaymentService {
    private boolean paid = false;

    public String pay(int price){

        return "paid" + price;
    }
}
