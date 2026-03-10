package org.example.concretecreator;

import org.example.creator.Logistics;
import org.example.concreteproduct.Ship;
import org.example.product.Transport;

public class SeaLogistics extends Logistics {

    private static SeaLogistics instance;

    private SeaLogistics() {}

    public static SeaLogistics getInstance() {
        if (instance == null) {
            instance = new SeaLogistics();
        }
        return instance;
    }

    @Override
    protected Transport createTransport() {
        return new Ship();
    }
}