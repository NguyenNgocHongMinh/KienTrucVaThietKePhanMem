package fit.bai3;

public class Strategy3 {
    public static void main(String[] args) {
        System.out.println("--- Thanh toán bằng nhiều phương thức ---");

        PaymentContext context = new PaymentContext();

        context.setStrategy(new CreditCardPayment());
        context.pay(100);

        context.setStrategy(new PaypalPayment());
        context.pay(200);
    }
}

// Strategy
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategy
class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("- Thanh toán bằng thẻ tín dụng: " + amount);
    }
}

class PaypalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("- Thanh toán bằng PayPal: " + amount);
    }
}

// Context
class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(double amount) {
        strategy.pay(amount);
    }
}