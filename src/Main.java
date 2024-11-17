import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();


        store.addProduct(new Product("Яблуко", 1.2, 4.5, 150, Unit.PIECE));
        store.addProduct(new Product("Молоко", 0.8, 4.8, 50, Unit.LITER));
        store.addProduct(new Product("Борошно", 0.5, 4.2, 100, Unit.KILOGRAM));
        store.addProduct(new Product("Печиво", 2.0, 4.9, 200, Unit.PACK));


        Scanner scanner = new Scanner(System.in);
        boolean running = true;


        while (running) {

            System.out.println("\nМеню:");
            System.out.println("1. Сортувати та відображати продукти");
            System.out.println("2. Додати продукт до корзини");
            System.out.println("3. Вийти");
            System.out.println("Введіть свій вибір: ");
            String choice = scanner.nextLine();


            switch (choice) {
                case "1":
                    store.handleSortAndDisplay(scanner);
                    break;
                case "2":
                    store.handleAddToCart(scanner);
                    break;
                case "3":
                    running = false;
                    System.out.println("Дякуємо за користування нашим магазином!");
                    break;
                default:
                    System.out.println("Невірний вибір. Будь ласка, спробуйте ще раз.");
                    break;

            }
        }
    }
}