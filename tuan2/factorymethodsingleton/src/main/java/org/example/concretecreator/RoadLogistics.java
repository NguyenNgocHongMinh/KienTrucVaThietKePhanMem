package org.example.concretecreator;

import org.example.concreteproduct.Truck;
import org.example.creator.Logistics;
import org.example.product.Transport;

public class RoadLogistics extends Logistics {

    private static RoadLogistics instance;

    private RoadLogistics() {}

    public static RoadLogistics getInstance() {
        if (instance == null) {
            instance = new RoadLogistics();
        }
        return instance;
    }

    @Override
    protected Transport createTransport() {
        return new Truck();
    }
}