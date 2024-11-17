public class Product {

    private String name;
    private double price;
    private double rating;
    private int stock;
    private Unit unit;

    public Product(String name, double price, double rating, int stock, Unit unit) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.stock = stock;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public int getStock() {
        return stock;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f %s) - Рейтинг: %.1f, Залишок на складі: %d %s",
                name, price, unit.toString().toLowerCase(), rating, stock, unit.toString().toLowerCase());
    }
}
