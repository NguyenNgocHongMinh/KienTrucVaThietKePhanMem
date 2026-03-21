package fit.bai3;

public class Decorator3 {
    public static void main(String[] args) {
        System.out.println("--- Thanh toán có thêm phí và giảm giá ---");

        PaymentComponent payment = new BasicPayment();

        payment = new FeeDecorator(payment);
        payment = new DiscountDecorator(payment);

        double finalAmount = payment.pay(100);
        System.out.println("Số tiền cuối: " + finalAmount);
    }
}

// Component
interface PaymentComponent {
    double pay(double amount);
}

// Concrete
class BasicPayment implements PaymentComponent {
    public double pay(double amount) {
        System.out.println("- Thanh toán cơ bản: " + amount);
        return amount;
    }
}

// Decorator base
abstract class PaymentDecorator implements PaymentComponent {
    protected PaymentComponent component;

    public PaymentDecorator(PaymentComponent component) {
        this.component = component;
    }

    public double pay(double amount) {
        return component.pay(amount);
    }
}

// Fee
class FeeDecorator extends PaymentDecorator {

    public FeeDecorator(PaymentComponent component) {
        super(component);
    }

    public double pay(double amount) {
        amount = super.pay(amount);
        System.out.println("- Thêm phí xử lý 10%");
        return amount * 1.1;
    }
}

// Discount
class DiscountDecorator extends PaymentDecorator {

    public DiscountDecorator(PaymentComponent component) {
        super(component);
    }

    public double pay(double amount) {
        amount = super.pay(amount);
        System.out.println("- Giảm giá 20%");
        return amount * 0.8;
    }
}