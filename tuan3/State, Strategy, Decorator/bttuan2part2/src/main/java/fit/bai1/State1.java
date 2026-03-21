package fit.bai1;

public class State1 {
    public static void main(String[] args) {
        System.out.println("--- 1: Luồng xử lý đơn hàng thành công ---");
        OrderContext order1 = new OrderContext(); // Bắt đầu là NewState
        order1.process(); // Chuyển sang ProcessingState
        order1.process(); // Chuyển sang DeliveredState
        order1.process(); // Thử cố tình process tiếp khi đã giao hàng
        order1.cancel();  // Thử hủy khi đã giao hàng

        System.out.println("\n--- 2: Khách hàng hủy đơn khi đang xử lý ---");
        OrderContext order2 = new OrderContext(); // Bắt đầu là NewState
        order2.process(); // Chuyển sang ProcessingState
        order2.cancel();  // Chuyển sang CancelledState
        order2.process(); // Thử cố tình process sau khi đã hủy
    }
}

interface OrderState {
    void process(OrderContext context);
    void cancel(OrderContext context);
}

class OrderContext {
    private OrderState state;

    public OrderContext() {
        this.state = new NewState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void process() {
        state.process(this);
    }

    public void cancel() {
        state.cancel(this);
    }
}

// Trạng thái đơn hàng mới
class NewState implements OrderState {
    @Override
    public void process(OrderContext context) {
        System.out.println("- Trạng thái mới: đang chuẩn bị đơn hàng. Chuyển sang trạng thái ĐANG XỬ LÝ.");
        // Thay đổi trạng thái của Context sang Processing
        context.setState(new ProcessingState());
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("- Trạng thái mới: khách hàng yêu cầu hủy. Chuyển sang trạng thái ĐÃ HỦY.");
        context.setState(new CancelledState());
    }
}

// Trạng thái đang xử lý
class ProcessingState implements OrderState {
    @Override
    public void process(OrderContext context) {
        System.out.println("- Trạng thái đang xử lý vì đã đóng gói và vận chuyển. Chuyển sang trạng thái ĐÃ GIAO HÀNG.");
        context.setState(new DeliveredState());
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("- Trạng thái đang xử lý vì đơn hàng đang đi trên đường, tiến hành thu hồi. Chuyển sang trạng thái ĐÃ HỦY.");
        context.setState(new CancelledState());
    }
}

// Trạng thái đã giao hàng
class DeliveredState implements OrderState {
    @Override
    public void process(OrderContext context) {
        System.out.println("- Trạng thái đã giao hàng vì đơn hàng đã hoàn tất. Không thể xử lý thêm.");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("- Trạng thái đã giao hàng vì không thể hủy đơn hàng vì đã được giao thành công.");
    }
}

// Trạng thái đã hủy
class CancelledState implements OrderState {
    @Override
    public void process(OrderContext context) {
        System.out.println("- Trạng thái hủy vì đơn hàng đã bị hủy, không thể tiếp tục xử lý.");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("- Trạng thái hủy vì đơn hàng đã bị hủy rồi.");
    }
}

