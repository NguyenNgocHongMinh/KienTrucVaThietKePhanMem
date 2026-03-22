package fit.bai3;

public class State3 {
        public static void main(String[] args) {
            System.out.println("--- Thanh toán thành công ---");

            PaymentContext1 payment = new PaymentContext1();
            payment.pay();   // New -> Processing
            payment.pay();   // Processing -> Completed
            payment.cancel();// thử cancel sau khi hoàn tất

            System.out.println("\n--- Hủy khi đang xử lý ---");

            PaymentContext1 payment2 = new PaymentContext1();
            payment2.pay();
            payment2.cancel();
            payment2.pay();
        }
    }

    // State
    interface PaymentState {
        void pay(PaymentContext1 context);
        void cancel(PaymentContext1 context);
    }

    // Context
    class PaymentContext1 {
        private PaymentState state;

        public PaymentContext1() {
            state = new NewState();
        }

        public void setState(PaymentState state) {
            this.state = state;
        }

        public void pay() {
            state.pay(this);
        }

        public void cancel() {
            state.cancel(this);
        }
    }

    // States
    class NewState implements PaymentState {
        public void pay(PaymentContext1 context) {
            System.out.println("- Bắt đầu thanh toán → Processing");
            context.setState(new ProcessingState());
        }

        public void cancel(PaymentContext1 context) {
            System.out.println("- Hủy thanh toán → Cancelled");
            context.setState(new CancelledState());
        }
    }

    class ProcessingState implements PaymentState {
        public void pay(PaymentContext1 context) {
            System.out.println("- Đang xử lý → Completed");
            context.setState(new CompletedState());
        }

        public void cancel(PaymentContext1 context) {
            System.out.println("- Hủy khi đang xử lý → Cancelled");
            context.setState(new CancelledState());
        }
    }

    class CompletedState implements PaymentState {
        public void pay(PaymentContext1 context) {
            System.out.println("- Đã thanh toán xong");
        }

        public void cancel(PaymentContext1 context) {
            System.out.println("- Không thể hủy vì đã hoàn tất");
        }
    }

    class CancelledState implements PaymentState {
        public void pay(PaymentContext1 context) {
            System.out.println("- Đã hủy, không thể thanh toán");
        }

        public void cancel(PaymentContext1 context) {
            System.out.println("- Đã hủy trước đó");
        }
    }