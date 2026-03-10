package org.example.creator;

import org.example.product.Transport;

public abstract  class Logistics {
    // business logic
    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }

    // factory method
    protected abstract Transport createTransport();
}
