// HARD PROBLEM
package EXP_6;

import java.util.*;
import java.util.stream.Collectors;

class Product {
    private String name;
    private String category;
    private double price;

    // Constructor
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getters
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class ProductStreamProcessor {
    public static void main(String[] args) {
        // Sample dataset of products
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200.99),
            new Product("Smartphone", "Electronics", 999.49),
            new Product("Headphones", "Electronics", 199.99),
            new Product("Tablet", "Electronics", 499.00),
            new Product("Sofa", "Furniture", 850.00),
            new Product("Chair", "Furniture", 120.50),
            new Product("Dining Table", "Furniture", 450.75),
            new Product("T-Shirt", "Clothing", 29.99),
            new Product("Jeans", "Clothing", 49.99),
            new Product("Jacket", "Clothing", 89.99)
        );

        // 1. Group products by category
        Map<String, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("\n📌 Products grouped by category:");
        productsByCategory.forEach((category, productList) -> {
            System.out.println(category + " -> " + productList);
        });

        // 2. Find the most expensive product in each category
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
                ));

        System.out.println("\n🏆 Most expensive product in each category:");
        mostExpensiveByCategory.forEach((category, product) -> 
            System.out.println(category + " -> " + product.get().getName() + " ($" + product.get().getPrice() + ")")
        );

        // 3. Calculate the average price of all products
        double averagePrice = products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);

        System.out.println("\n💰 Average price of all products: $" + String.format("%.2f", averagePrice));
    }
}
