package fit.bai2;

public class Decorator2 {
    public static void main(String[] args) {
        System.out.println("--- Áp dụng nhiều loại thuế cùng lúc ---");

        ProductComponent product = new BaseProduct("Laptop", 1000);

        product = new VatDecorator(product);
        product = new LuxuryTaxDecorator(product);

        System.out.println("Mô tả: " + product.getDescription());
        System.out.println("Giá cuối: " + product.getPrice());
    }
}

// Component
interface ProductComponent {
    double getPrice();
    String getDescription();
}

// Concrete
class BaseProduct implements ProductComponent {
    private double price;
    private String name;

    public BaseProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return name;
    }
}

// Decorator base
abstract class TaxDecorator implements ProductComponent {
    protected ProductComponent product;

    public TaxDecorator(ProductComponent product) {
        this.product = product;
    }

    public double getPrice() {
        return product.getPrice();
    }

    public String getDescription() {
        return product.getDescription();
    }
}

// Decorator VAT
class VatDecorator extends TaxDecorator {

    public VatDecorator(ProductComponent product) {
        super(product);
    }

    public double getPrice() {
        System.out.println("- Thêm VAT 10%");
        return super.getPrice() * 1.1;
    }

    public String getDescription() {
        return super.getDescription() + " + VAT";
    }
}

// Decorator Luxury
class LuxuryTaxDecorator extends TaxDecorator {

    public LuxuryTaxDecorator(ProductComponent product) {
        super(product);
    }

    public double getPrice() {
        System.out.println("- Thêm Luxury Tax 20%");
        return super.getPrice() * 1.2;
    }

    public String getDescription() {
        return super.getDescription() + " + Luxury Tax";
    }
}