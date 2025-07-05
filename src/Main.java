import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create products
        Product cheese = new ExpirableProduct("Cheese", 100, 10, LocalDate.now().plusDays(5), 0.4);
        Product biscuits = new ExpirableProduct("Biscuits", 150, 5, LocalDate.now().plusDays(2), 0.7);
        Product tv = new ShippableProduct("TV", 5000, 3, 10.0);
        Product scratchCard = new Product("Scratch Card", 50, 20);


        // Create customer
        Customer customer = new Customer("Mustafa", 1000);

        // Create cart and add items
        Cart cart = new Cart();
        cart.addProduct(cheese, 2);
        cart.addProduct(biscuits, 1);

        // Create services
        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService();

        // Perform checkout
        try {
            checkoutService.checkout(customer, cart, shippingService);
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }
}