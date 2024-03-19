// MainClass.java
package shopping;

import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAuthentication userAuthentication = new UserAuthentication();
        Catalog catalog = new Catalog();
        ShoppingCart shoppingCart = new ShoppingCart();
        Logger logger = FileLogger.getInstance(); // Initialize logger
        PaymentProcessor paymentProcessor = new PaymentProcessor(FileLogger.getInstance());
        // Load products into the catalog
        List<ProductFactory> products = ProductLoader.loadProducts();
        catalog.addProducts(products);

        // Start user interaction loop
        while (true) {
            // Display menu options
            displayMenu();

            // Take user input
            int choice = getUserChoice(scanner);

            // Handle user choice
            switch (choice) {
                case 1:
                    registerUser(scanner, userAuthentication, logger);
                    break;
                case 2:
                    loginUser(scanner, userAuthentication, logger);
                    break;
                case 3:
                    displayProductList(catalog);
                    break;
                case 4:
                    addProductToCart(scanner, shoppingCart, catalog);
                    break;
                case 5:
                    viewCart(shoppingCart);
                    break;
                case 6:
                    placeOrder(scanner, shoppingCart, paymentProcessor, logger);
                    break;
                case 7:
                    exitProgram(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Browse Products");
        System.out.println("4. Add Product to Cart");
        System.out.println("5. View Cart");
        System.out.println("6. Place Order");
        System.out.println("7. Exit");
        System.out.print("Select an option: ");
    }

    private static int getUserChoice(Scanner scanner) {
        return scanner.nextInt();
    }

    private static void registerUser(Scanner scanner, UserAuthentication userAuthentication, Logger logger) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();
        userAuthentication.registerUser(username, password, email);
        logger.log("User registered: " + username);
        System.out.println("User registered successfully!");
    }

    private static void loginUser(Scanner scanner, UserAuthentication userAuthentication, Logger logger) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        User currentUser = userAuthentication.login(username, password);
        if (currentUser != null) {
            logger.logUserLogin(currentUser.getUsername()); // Log the user login with a welcome message
            System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
        } else {
            logger.log("Login failed for user: " + username);
            System.out.println("Login failed. Please check your credentials.");
        }
    }



    private static void displayProductList(Catalog catalog) {
        List<ProductFactory> products = catalog.getAllProducts();
        System.out.println("Product List:");
        for (int i = 0; i < products.size(); i++) {
            ProductFactory product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());
            System.out.println("   Description: " + product.getDescription());
            System.out.println("   Quantity in Stock: " + product.getQuantityInStock());
        }
    }

    private static void addProductToCart(Scanner scanner, ShoppingCart shoppingCart, Catalog catalog) {
        System.out.print("Enter the product name to add to the cart: ");
        String productName = scanner.next();
        ProductFactory selectedProduct = findProductByName(catalog.getAllProducts(), productName);
        if (selectedProduct != null) {
            // Use CartBuilder to add item to cart
            ShoppingCart updatedCart = new CartBuilder().addItem(selectedProduct).build();
            shoppingCart.setItems(updatedCart.getItems());
            System.out.println("Product added to the cart.");
        } else {
            System.out.println("Product not found in the catalog.");
        }
    }

    private static void viewCart(ShoppingCart shoppingCart) {
        List<ProductFactory> items = shoppingCart.getItems();
        System.out.println("Cart:");
        for (ProductFactory item : items) {
            System.out.println(item.getName());
        }
        double totalPrice = calculateTotalPrice(shoppingCart);
        System.out.println("Total Price: $" + totalPrice);
    }

    private static void placeOrder(Scanner scanner, ShoppingCart shoppingCart, PaymentProcessor paymentProcessor, Logger logger) {
        System.out.println("Placing an order...");
        if (shoppingCart.getItems().isEmpty()) {
            System.out.println("Cannot place order. Shopping cart is empty.");
            return;
        }

        double totalPrice = calculateTotalPrice(shoppingCart);
        System.out.println("Total amount to be paid: $" + totalPrice);
        System.out.println("Select payment method:");
        System.out.println("1. Credit Card\n2. Debit Card\n3. Cash\n4. UPI.");
        System.out.print("Please select an option: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String paymentMethod = "";
        switch (paymentChoice) {
            case 1:
                paymentMethod = "Credit Card";
                break;
            case 2:
                paymentMethod = "Debit Card";
                break;
            case 3:
                paymentMethod = "Cash";
                break;
            case 4:
                paymentMethod = "UPI";
                break;
            default:
                System.out.println("Invalid payment option selected. Payment failed.");
                return;
        }

        boolean paymentSuccess = paymentProcessor.processPayment(totalPrice, paymentMethod);
        if (paymentSuccess) {
            logger.log("Order placed successfully.");
            shoppingCart.clearCart();
            System.out.println("Order placed successfully. Your cart is now empty.");
        } else {
            logger.log("Payment failed.");
            System.out.println("Payment failed. Please try again.");
        }
    }


    private static void exitProgram(Scanner scanner) {
        System.out.println("Exiting the program.");
        scanner.close();
        System.exit(0);
    }

    private static ProductFactory findProductByName(List<ProductFactory> catalogProducts, String productName) {
        for (ProductFactory product : catalogProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    private static double calculateTotalPrice(ShoppingCart shoppingCart) {
        double total = 0.0;
        for (ProductFactory product : shoppingCart.getItems()) {
            total += product.getPrice();
        }
        return total;
    }
}
