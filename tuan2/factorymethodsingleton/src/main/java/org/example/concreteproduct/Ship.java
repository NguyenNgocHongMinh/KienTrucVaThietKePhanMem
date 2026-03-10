package org.example.concreteproduct;

import org.example.product.Transport;

public class Ship implements Transport {

    @Override
    public void deliver() {
        System.out.println("Deliver by ship on sea.");
    }
}
