package shopping;



import org.junit.Test;



import static org.junit.Assert.assertEquals;



import java.util.List;



public class ShoppingCartTest {



 @Test

 public void testGetTotalAmount() {


 List<ProductFactory> products = ProductLoader.loadProducts();

 ShoppingCart cart = new ShoppingCart();




 cart.addItem(products.get(0)); 

 cart.addItem(products.get(1)); 

 cart.addItem(products.get(5)); 

 cart.addItem(products.get(8)); 




 double expectedTotalAmount = products.get(0).getPrice() + products.get(1).getPrice()

 + products.get(5).getPrice() + products.get(8).getPrice();
 assertEquals(expectedTotalAmount, cart.getTotalAmount(), 0.01);

 }



 @Test

 public void testAddItem() {

 List<ProductFactory> products = ProductLoader.loadProducts();

 ShoppingCart cart = new ShoppingCart();

 cart.addItem(products.get(2)); 

 assertEquals(1, cart.getItems().size());

 assertEquals(products.get(2), cart.getItems().get(0));

 }



 @Test

 public void testRemoveItem() {

 List<ProductFactory> products = ProductLoader.loadProducts();

 ShoppingCart cart = new ShoppingCart();
 
 ProductFactory product = products.get(3); 

 cart.addItem(product);

 cart.removeItem(product);

 assertEquals(0, cart.getItems().size());

 }

}

