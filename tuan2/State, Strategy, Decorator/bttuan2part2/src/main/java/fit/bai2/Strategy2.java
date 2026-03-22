package fit.bai2;

public class Strategy2 {
    public static void main(String[] args) {
        System.out.println("--- Áp dụng các loại thuế khác nhau ---");

        Product product = new Product(100);

        product.setTaxStrategy(new VatTax());
        System.out.println("Giá sau VAT: " + product.getFinalPrice());

        product.setTaxStrategy(new LuxuryTax());
        System.out.println("Giá sau Luxury Tax: " + product.getFinalPrice());

        product.setTaxStrategy(new ConsumptionTax());
        System.out.println("Giá sau Consumption Tax: " + product.getFinalPrice());
    }
}

// Strategy
interface TaxStrategy {
    double calculateTax(double price);
}

// Các strategy cụ thể
class VatTax implements TaxStrategy {
    public double calculateTax(double price) {
        System.out.println("- Áp dụng VAT 10%");
        return price * 0.1;
    }
}

class LuxuryTax implements TaxStrategy {
    public double calculateTax(double price) {
        System.out.println("- Áp dụng Luxury Tax 20%");
        return price * 0.2;
    }
}

class ConsumptionTax implements TaxStrategy {
    public double calculateTax(double price) {
        System.out.println("- Áp dụng Consumption Tax 15%");
        return price * 0.15;
    }
}

// Context
class Product {
    private double price;
    private TaxStrategy taxStrategy;

    public Product(double price) {
        this.price = price;
    }

    public void setTaxStrategy(TaxStrategy strategy) {
        this.taxStrategy = strategy;
    }

    public double getFinalPrice() {
        return price + taxStrategy.calculateTax(price);
    }
}