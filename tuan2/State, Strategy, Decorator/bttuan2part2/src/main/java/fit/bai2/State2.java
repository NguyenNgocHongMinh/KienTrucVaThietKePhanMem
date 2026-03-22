package fit.bai2;

public class State2 {
    public static void main(String[] args) {
        System.out.println("--- 1: Sản phẩm nội địa ---");
        ProductContext p1 = new ProductContext(100);
        p1.setState(new DomesticState());
        System.out.println("Giá cuối: " + p1.calculateFinalPrice());

        System.out.println("\n--- 2: Sản phẩm nhập khẩu ---");
        ProductContext p2 = new ProductContext(100);
        p2.setState(new ImportState());
        System.out.println("Giá cuối: " + p2.calculateFinalPrice());
    }
}

// State
interface TaxState {
    double applyTax(double price);
}

// Context
class ProductContext {
    private TaxState state;
    private double basePrice;

    public ProductContext(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setState(TaxState state) {
        this.state = state;
    }

    public double calculateFinalPrice() {
        return state.applyTax(basePrice);
    }
}

// State nội địa
class DomesticState implements TaxState {
    public double applyTax(double price) {
        System.out.println("- Sản phẩm nội địa: áp dụng VAT 10%");
        return price * 1.1;
    }
}

// State nhập khẩu
class ImportState implements TaxState {
    public double applyTax(double price) {
        System.out.println("- Sản phẩm nhập khẩu: áp dụng thuế 25%");
        return price * 1.25;
    }
}