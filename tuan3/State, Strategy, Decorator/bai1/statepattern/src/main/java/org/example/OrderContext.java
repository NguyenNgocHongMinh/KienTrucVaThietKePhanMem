package org.example;

public class OrderContext {
    private OrderState state;

    public OrderContext() {
        state = new NewState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void process() {
        state.process(state);
    }

    public void cancel() {
        state.cancel(state);
    }
}
