package shopping;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ProductFactory> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(ProductFactory product) {
        items.add(product);
    }

    public void removeItem(ProductFactory product) {
        items.remove(product);
    }

    public List<ProductFactory> getItems() {
        return items;
    }
    public void setItems(List<ProductFactory> items) {
        this.items = items;
    }

    public void clearCart() {
        items.clear();
    }

    public double getTotalAmount() {
        double total = 0.0;
        for (ProductFactory item : items) {
            total += item.getPrice();
        }
        return total;
    }
}
