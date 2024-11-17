import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {
    private List<Product> products;
    private Cart cart;

    public Store() {
        this.products = new ArrayList<>();
        this.cart = new Cart();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void displayProducts(SortType sortType) {
        products.sort(sortType.getComparator());
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void addToCart(Product product, int quantity) {
        cart.addToCart(product, quantity);
    }

    public void displayCart() {
        cart.displayCart();
    }

    //обробка вибору сортування та відображення продуктів
    public void handleSortAndDisplay(Scanner scanner) {
        System.out.println("\"Оберіть тип сортування: PRICE_ASC (ціна за зростанням), " +
                "PRICE_DESC (ціна за спаданням), RATING (рейтинг), STOCK (залишок на складі)");
        String input = scanner.nextLine().toUpperCase();
        try {
            SortType sortType = SortType.valueOf(input);
            displayProducts(sortType);
        } catch (IllegalArgumentException e) {
            System.out.println("Невірний тип сортування. Будь ласка, спробуйте ще раз.");
        }
    }

    //обробка додавання продуктів до кошика
    public void handleAddToCart(Scanner scanner) {
        System.out.println("Введіть назву продукту для додавання до корзини:");
        String productName = scanner.nextLine();
        System.out.println("Введіть кількість:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product selectedProduct = null;
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                selectedProduct = product;
                break;
            }
        }
        if (selectedProduct != null) {
            try {
                addToCart(selectedProduct, quantity);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Продукт не знайдено.");
        }
        System.out.println("Ваша корзина:");
        displayCart();
    }
}
