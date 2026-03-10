package org.example.concreteproduct;
import org.example.product.Transport;
public class Truck implements Transport {

    @Override
    public void deliver() {
        System.out.println("Deliver by truck on road.");
    }
}