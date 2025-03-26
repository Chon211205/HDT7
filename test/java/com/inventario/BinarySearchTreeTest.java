package test.java.com.inventario;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    private BinarySearchTreeTest tree;

    
    @Test
    public void testAddAndSearchSingleProduct() {
        // Add a product
        tree.add("SKU001", "Laptop", "Electrónica", 1000.0, 850.0);
        
        // Search for the adPoded product
        Node result = tree.Buscar("SKU001");
        
        // Verify the product details
        assertNotNull("Product should be found", result);
        assertEquals("SKU should match", "SKU001", result.Sku);
        assertEquals("Product name should match", "Laptop", result.Nombre_producto);
        assertEquals("Category should match", "Electrónica", result.Categoria);
        assertEquals("Retail price should match", 1000.0, result.Precio_r, 0.001);
        assertEquals("Current price should match", 850.0, result.Precio_c, 0.001);
    }

    @Test
    public void testAddMultipleProductsAndSearch() {
        // Add multiple products
        tree.add("SKU001", "Laptop", "Electrónica", 1000.0, 850.0);
        tree.add("SKU002", "Smartphone", "Electrónica", 800.0, 700.0);
        tree.add("SKU003", "Tablet", "Electrónica", 500.0, 450.0);

        // Search for different products
        Node laptop = tree.Buscar("SKU001");
        Node smartphone = tree.Buscar("SKU002");
        Node tablet = tree.Buscar("SKU003");

        // Verify each product
        assertNotNull("Laptop should be found", laptop);
        assertNotNull("Smartphone should be found", smartphone);
        assertNotNull("Tablet should be found", tablet);

        assertEquals("Laptop SKU should match", "SKU001", laptop.Sku);
        assertEquals("Smartphone SKU should match", "SKU002", smartphone.Sku);
        assertEquals("Tablet SKU should match", "SKU003", tablet.Sku);
    }

    @Test
    public void testSearchNonExistentProduct() {
        // Add a product
        tree.add("SKU001", "Laptop", "Electrónica", 1000.0, 850.0);

        // Search for a non-existent product
        Node result = tree.Buscar("SKU999");

        // Verify that no product is found
        assertNull("Non-existent product should return null", result);
    }

    @Test
    public void testAddProductsInOrder() {
        // Add products in ascending order
        tree.add("SKU001", "Product A", "Category A", 100.0, 90.0);
        tree.add("SKU002", "Product B", "Category B", 200.0, 180.0);
        tree.add("SKU003", "Product C", "Category C", 300.0, 270.0);

        // Verify each product can be found
        Node productA = tree.Buscar("SKU001");
        Node productB = tree.Buscar("SKU002");
        Node productC = tree.Buscar("SKU003");

        assertNotNull("Product A should be found", productA);
        assertNotNull("Product B should be found", productB);
        assertNotNull("Product C should be found", productC);
    }

    @Test
    public void testAddProductsInReverseOrder() {
        // Add products in descending order
        tree.add("SKU003", "Product C", "Category C", 300.0, 270.0);
        tree.add("SKU002", "Product B", "Category B", 200.0, 180.0);
        tree.add("SKU001", "Product A", "Category A", 100.0, 90.0);

        // Verify each product can be found
        Node productA = tree.Buscar("SKU001");
        Node productB = tree.Buscar("SKU002");
        Node productC = tree.Buscar("SKU003");

        assertNotNull("Product A should be found", productA);
        assertNotNull("Product B should be found", productB);
        assertNotNull("Product C should be found", productC);
    }

    @Test
    public void testAddDuplicateSKU() {
        // First addition
        tree.add("SKU001", "Original Product", "Category A", 100.0, 90.0);
        
        // Second addition with same SKU
        tree.add("SKU001", "Duplicate Product", "Category B", 200.0, 180.0);

        // Verify that the first added product remains
        Node result = tree.Buscar("SKU001");
        
        assertNotNull("Product should be found", result);
        assertEquals("Original product details should remain", "Original Product", result.Nombre_producto);
        assertEquals("Original category should remain", "Category A", result.Categoria);
    }
}