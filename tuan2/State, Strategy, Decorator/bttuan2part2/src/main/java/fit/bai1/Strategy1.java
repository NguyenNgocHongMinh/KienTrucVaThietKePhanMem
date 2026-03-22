package fit.bai1; // Bạn có thể xóa dòng này nếu chạy code trực tiếp không qua IDE

class Strategy1 {
    public static void main(String[] args) {
        Order order = new Order();

        System.out.println("Bắt đầu xử lý đơn hàng ");

        // Kiểm tra đơn hàng
        order.setAction(new CheckStrategy());
        order.execute();

        // Giao hàng
        order.setAction(new ShippingStrategy());
        order.execute();

        // Giao hàng thành công
        order.setAction(new DeliveredStrategy());
        order.execute();

        System.out.println(" Xử lý đơn hàng bị lỗi");

        // Khách hàng yêu cầu hoàn tiền
        order.setAction(new RefundStrategy());
        order.execute();
    }
}

interface ActionStrategy {
    void execute();
}

class CheckStrategy implements ActionStrategy {
    @Override
    public void execute() {
        System.out.println("- Kiểm tra đơn hàng...");
    }
}

class ShippingStrategy implements ActionStrategy {
    @Override
    public void execute() {
        System.out.println("- Đang giao hàng ...");
    }
}

class DeliveredStrategy implements ActionStrategy {
    @Override
    public void execute() {
        System.out.println("- Đã giao hàng thành công");
    }
}

class RefundStrategy implements ActionStrategy {
    @Override
    public void execute() {
        System.out.println("- Đang hoàn tiền cho đơn hàng ...");
    }
}

class Order {
    private ActionStrategy action;

    public void setAction(ActionStrategy action) {
        this.action = action;
    }

    public void execute() {
        if (this.action != null) {
            this.action.execute();
        } else {
            System.out.println("Lỗi: Chưa có hành động nào được thiết lập cho đơn hàng này!");
        }
    }
}

