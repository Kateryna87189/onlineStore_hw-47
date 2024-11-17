import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class StoreTest {
    private Store store;
    private List<Product> originalProducts;



    @BeforeEach
    public void setUp(){
        store = new Store();
        store.addProduct(new Product("Яблуко", 1.2, 4.5, 150, Unit.PIECE));
        store.addProduct(new Product("Молоко", 0.8, 4.8, 50, Unit.LITER));
        store.addProduct(new Product("Борошно", 0.5, 4.2, 100, Unit.KILOGRAM));
        store.addProduct(new Product("Печиво", 2.0, 4.9, 200, Unit.PACK));

        originalProducts = new ArrayList<>(store.getProducts());
    }
    @ParameterizedTest
    @EnumSource(SortType.class)
    public void testDisplayProducts(SortType sortType){
        store.displayProducts(sortType);

        List<Product> expectedProducts = new ArrayList<>(originalProducts);
        expectedProducts.sort(sortType.getComparator());

        Assertions.assertEquals( expectedProducts, store.getProducts());
    }
    @ParameterizedTest
    @MethodSource("provideAddToCartData")
    public void testAddToCart(String productName, int quantity, int expectedStock){
       Product product = store.getProducts().stream()
               .filter(p->p.getName().equals(productName))
               .findFirst()
               .orElseThrow(()-> new IllegalArgumentException("Продукт не знайдено: "+ productName));

       store.addToCart(product, quantity);
       Assertions.assertEquals(expectedStock, product.getStock());

    }
    private static Stream<Arguments> provideAddToCartData(){
        return Stream.of(
                Arguments.of("Яблуко", 10, 140),
                Arguments.of("Молоко", 25, 25),
                Arguments.of("Борошно", 50, 50),
                Arguments.of("Печиво", 200, 0),
                Arguments.of("Печиво", 300, 200)
        );
    }
    @Test
    public void testAddZeroQuantity(){
        Product apple= store.getProducts().get(0);
        store.addToCart(apple, 0);
        Assertions.assertEquals(150, apple.getStock());
    }
    @Test
    public void testAddNewProduct(){
        Product newProduct = new Product("Новий продукт", 3.0, 5.0, 100, Unit.PIECE);
        store.addProduct(newProduct);

        List<Product> products = store.getProducts();
        Assertions.assertTrue(products.contains(newProduct));
    }

}
