import java.util.*;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {;
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                item = new CartItem(product, item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }
}
