import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    //додавання в корзину
    public void addToCart(Product product, int quantity) {
        if (quantity > product.getStock()) {
            System.out.println("Не можна додати більше продуктів, чим є на складі");
            return;
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
        product.setStock(product.getStock() - quantity);
    }

    //відображення вмісту корзини
    public void displayCart() {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey() + " - Кількість: " + entry.getValue());
        }
    }
}
