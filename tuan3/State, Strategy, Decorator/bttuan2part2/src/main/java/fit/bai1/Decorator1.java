package fit.bai1;

public class Decorator1 {
    public static void main(String[] args) {
        System.out.println("--- 1. Xử lý đơn hàng cơ bản ---");
        OrderComponent basicOrder = new BasicOrder();
        basicOrder.process();

        System.out.println("\n--- 2. Đơn hàng có thêm tính năng Ghi log ---");
        OrderComponent loggedOrder = new LoggingDecorator(new BasicOrder());
        loggedOrder.process();

        System.out.println("\n--- 3. Đơn hàng có cả tính năng Ghi log và Gửi thông báo ---");
        // Bọc lớp Notification bên ngoài lớp Logging, lớp Logging lại bọc BasicOrder
        OrderComponent fullyDecoratedOrder = new NotificationDecorator(
                new LoggingDecorator(
                        new BasicOrder()
                )
        );
        fullyDecoratedOrder.process();
    }
}

interface OrderComponent {
    void process();
}

class BasicOrder implements OrderComponent {
    @Override
    public void process() {
        System.out.println("Đang xử lý đơn hàng cơ bản (Basic Order)...");
    }
}

// Lớp Decorator gốc
abstract class OrderDecorator implements OrderComponent {
    private OrderComponent component;

    public OrderDecorator(OrderComponent component) {
        this.component = component;
    }

    @Override
    public void process() {
        if (this.component != null) {
            this.component.process();
        }
    }
}

// Lớp trang trí 1: Thêm tính năng ghi log
class LoggingDecorator extends OrderDecorator {
    public LoggingDecorator(OrderComponent component) {
        super(component);
    }

    @Override
    public void process() {
        System.out.println("- Bắt đầu quá trình xử lý đơn hàng");

        super.process();

        System.out.println("- Đã hoàn tất quá trình xử lý đơn hàng");
    }
}

// Lớp trang trí 2: Thêm tính năng gửi thông báo
class NotificationDecorator extends OrderDecorator {
    public NotificationDecorator(OrderComponent component) {
        super(component);
    }

    @Override
    public void process() {
        super.process();

        System.out.println("Đang gửi email thông báo cho khách hàng...");
    }
}
