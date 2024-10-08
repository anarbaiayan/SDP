class Product{
    private String name;
    private String id;
    private double price;

    public Product(String name, String id, double price){
        this.name = name;
        this.id = id;
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public double getPrice(){
        return price;
    }
}

class ProductCatalog{
    public Product searchProduct(String name){
        System.out.println("Searching product by name: " + name);
        return new Product(name, "101", 100.0);
    }

    public Product selectProduct(String id){
        System.out.println("Selecting product by id: " + id);
        return new Product("Sample Product", id, 100.0);
    }
}

class PaymentProcessor{
    public boolean processPayment(String paymentDetails, double amount){
        System.out.println("Payment details: " + paymentDetails + "amount: " + amount);
        return true;
    }
}

class InventoryManager{
    public boolean checkStock(String productId){
        System.out.println("Checking stock for product: " + productId);
        return true;
    }

    public void updateStock(String productId, int quantity){
        System.out.println("Updating stock for product: " + productId + " with quantity: " + quantity);
    }
}

class ShippingService{
    public double calculateShipping(String location){
        System.out.println("Calculating shipping for location: " + location);
        return 10.0;
    }

    public void shipOrder(String productId, String location){
        System.out.println("Shipping order for product: " + productId + " with location: " + location);
    }
}

class ShoppingFacade{
    private ProductCatalog productCatalog;
    private PaymentProcessor paymentProcessor;
    private InventoryManager inventoryManager;
    private ShippingService shippingService;

    public ShoppingFacade(){
        this.productCatalog = new ProductCatalog();
        this.paymentProcessor = new PaymentProcessor();
        this.inventoryManager = new InventoryManager();
        this.shippingService = new ShippingService();
    }

    public void placeOrder(String productName, String paymentDetails, String location){
        Product product = productCatalog.searchProduct(productName);
        productCatalog.selectProduct(product.getId());

        if(!inventoryManager.checkStock(product.getId())){
            System.out.println("Product not in stock");
            return;
        }

        if(!paymentProcessor.processPayment(paymentDetails, product.getPrice())){
            System.out.println("Payment failed");
            return;
        }

        inventoryManager.updateStock(product.getId(), 1);

        double shippingCost = shippingService.calculateShipping(location);
        shippingService.shipOrder(product.getId(), location);

        System.out.println("Shipping order successful. Total cost: " + (shippingCost + product.getPrice()));
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingFacade facade = new ShoppingFacade();
        facade.placeOrder("laptop", "Card", "Astana");
    }
}