package org.example;

public class NewState implements OrderState {
    public void process(OrderContext context) {
        System.out.println("Kiểm tra đơn hàng...");
        context.setState(new ProcessingState());
    }

    public void cancel(OrderContext context) {
        System.out.println("Hủy đơn từ trạng thái NEW");
        context.setState(new CancelledState());
    }
}
