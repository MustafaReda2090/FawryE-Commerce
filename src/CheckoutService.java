import java.util.*;

public class CheckoutService {
    private static final double SHIPPING_FEE = 30.0;

    public void checkout(Customer customer, Cart cart, ShippingService shippingService) {
        if (cart.isEmpty()) throw new IllegalArgumentException("Cart is empty.");

        double subtotal = 0;
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product instanceof ExpirableProduct && ((ExpirableProduct) product).isExpired()) {
                throw new IllegalStateException(product.getName() + " is expired.");
            }
            if (item.getQuantity() > product.getQuantity()) {
                throw new IllegalStateException(product.getName() + " is out of stock.");
            }
            subtotal += product.getPrice() * item.getQuantity();
            if (product instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        }
        double shipping = shippableItems.isEmpty() ? 0 : SHIPPING_FEE;
        double total = subtotal + shipping;
        if (customer.getBalance() < total) throw new IllegalStateException("Insufficient balance.");

        customer.decrementBalance(total);
        for (CartItem item : cart.getItems()) {
            item.getProduct().decrementQuantity(item.getQuantity());
        }
        if (!shippableItems.isEmpty()) shippingService.ship(shippableItems);

        // Print receipt
        System.out.println("- * Checkout receipt **\n");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %-12s %d\n",
                item.getQuantity(),
                item.getProduct().getName(),
                (int)(item.getProduct().getPrice() * item.getQuantity()));
        }
        System.out.println("\n---------------------\n");
        System.out.printf("Subtotal         %d\n", (int)subtotal);
        System.out.printf("Shipping         %d\n", (int)shipping);
        System.out.printf("Amount           %d\n", (int)total);
    }
}