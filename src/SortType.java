import java.util.Comparator;

public enum SortType {
    PRICE_ASC(Comparator.comparingDouble(Product::getPrice)),
    PRICE_DESC(Comparator.comparingDouble(Product::getPrice)),
    RATING(Comparator.comparingDouble(Product::getRating).reversed()),
    STOCK(Comparator.comparingInt(Product::getStock).reversed());

    private final Comparator<Product> comparator;

    SortType(Comparator<Product> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Product> getComparator() {
        return comparator;
    }
}
