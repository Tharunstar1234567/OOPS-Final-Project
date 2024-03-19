package shopping;



import java.util.ArrayList;

import java.util.List;



public class ProductLoader {

 public static List<ProductFactory> loadProducts() {

 List<ProductFactory> products = new ArrayList<>();




 ElectronicProductFactory laptop = new ElectronicProductFactory("Laptop", 2200.0, "Apple Laptop", 10);

 ElectronicProductFactory smartwatch = new ElectronicProductFactory("Smartwatch", 249.99, " Apple Smart Watch", 15);

 ElectronicProductFactory headphones = new ElectronicProductFactory("Headphones", 299.99, "Apple Airpods", 20);

 ElectronicProductFactory smartphone = new ElectronicProductFactory("Smartphone", 1199.99, "Apple Iphones", 12);

 ElectronicProductFactory tablet = new ElectronicProductFactory("Tablet", 699.99, "Apple Tab", 8);
 
 ElectronicProductFactory playstation = new ElectronicProductFactory("Playstation", 2499.99, "Sony PS5", 13);


 ClothingProductFactory tShirt = new ClothingProductFactory("T-Shirt", 10.0, "Sports T-shirt", 50);

 ClothingProductFactory jeans = new ClothingProductFactory("Jeans", 44.99, "Ripped Jeans", 30);

 ClothingProductFactory sneakers = new ClothingProductFactory("Sneakers", 119.99, "Casual sneakers", 25);

 ClothingProductFactory dress = new ClothingProductFactory("Dress", 99.99, " Wedding dress", 15);

 ClothingProductFactory hoodie = new ClothingProductFactory("Hoodie", 59.99, "Hoodie", 40);
 
 ClothingProductFactory shirt = new ClothingProductFactory("Shirt", 29.99, "Cotton Shirts", 40);
 
 KitchenProductFactory cooker = new KitchenProductFactory("Cooker", 119.99, "Prestige", 10);
 
 KitchenProductFactory pan = new KitchenProductFactory("Pan", 39.99, "Non Stick Pan", 20);
 
 KitchenProductFactory stove = new KitchenProductFactory("Stove", 149.99, "Induction Stove", 15);
 



 products.add(laptop);

 products.add(smartwatch);

 products.add(headphones);

 products.add(smartphone);

 products.add(tablet);
 
 products.add(playstation);
 

 products.add(tShirt);

 products.add(jeans);

 products.add(sneakers);

 products.add(dress);

 products.add(hoodie);
 
 products.add(shirt);

 
 products.add(cooker);
 
 products.add(pan);
 
 products.add(stove);

 return products;

 }

}

