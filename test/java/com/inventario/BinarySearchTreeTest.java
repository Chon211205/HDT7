package test.java.com.inventario;

import main.java.com.inventario.BinarySearchTree;
import main.java.com.inventario.Node;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    private BinarySearchTree tree;

    @Before
    public void setUp() {
        tree = new BinarySearchTree();
    }
    
    @Test
    public void testAddAndSearchSingleProduct() {
        // Agregar un producto
        tree.add("SKU001", "Laptop", "Electrónica", 1000.0, 850.0);
        
        // Buscar el producto agregado
        Node result = tree.Buscar("SKU001");
        
        // Verificar los detalles del producto
        assertNotNull("Producto debe ser encontrado", result);
        assertEquals("SKU debe coincidir", "SKU001", result.Sku);
        assertEquals("Nombre del producto debe coincidir", "Laptop", result.Nombre_producto);
        assertEquals("Categoría debe coincidir", "Electrónica", result.Categoria);
        assertEquals("Precio retail debe coincidir", 1000.0, result.Precio_r, 0.001);
        assertEquals("Precio actual debe coincidir", 850.0, result.Precio_c, 0.001);
    }

    @Test
    public void testAddMultipleProductsAndSearch() {
        // Agregar múltiples productos
        tree.add("SKU001", "Laptop", "Electrónica", 1000.0, 850.0);
        tree.add("SKU002", "Smartphone", "Electrónica", 800.0, 700.0);
        tree.add("SKU003", "Tablet", "Electrónica", 500.0, 450.0);

        // Buscar diferentes productos
        Node laptop = tree.Buscar("SKU001");
        Node smartphone = tree.Buscar("SKU002");
        Node tablet = tree.Buscar("SKU003");

        // Verificar cada producto
        assertNotNull("Laptop debe ser encontrado", laptop);
        assertNotNull("Smartphone debe ser encontrado", smartphone);
        assertNotNull("Tablet debe ser encontrado", tablet);

        assertEquals("SKU de Laptop debe coincidir", "SKU001", laptop.Sku);
        assertEquals("SKU de Smartphone debe coincidir", "SKU002", smartphone.Sku);
        assertEquals("SKU de Tablet debe coincidir", "SKU003", tablet.Sku);
    }

    @Test
    public void testSearchNonExistentProduct() {
        // Agregar un producto
        tree.add("SKU001", "Laptop", "Electrónica", 1000.0, 850.0);

        // Buscar un producto inexistente
        Node result = tree.Buscar("SKU999");

        // Verificar que no se encuentre ningún producto
        assertNull("Producto inexistente debe retornar null", result);
    }

    @Test
    public void testAddProductsInOrder() {
        // Agregar productos en orden ascendente
        tree.add("SKU001", "Producto A", "Categoría A", 100.0, 90.0);
        tree.add("SKU002", "Producto B", "Categoría B", 200.0, 180.0);
        tree.add("SKU003", "Producto C", "Categoría C", 300.0, 270.0);

        // Verificar que cada producto se pueda encontrar
        Node productoA = tree.Buscar("SKU001");
        Node productoB = tree.Buscar("SKU002");
        Node productoC = tree.Buscar("SKU003");

        assertNotNull("Producto A debe ser encontrado", productoA);
        assertNotNull("Producto B debe ser encontrado", productoB);
        assertNotNull("Producto C debe ser encontrado", productoC);
    }

    @Test
    public void testAddProductsInReverseOrder() {
        // Agregar productos en orden descendente
        tree.add("SKU003", "Producto C", "Categoría C", 300.0, 270.0);
        tree.add("SKU002", "Producto B", "Categoría B", 200.0, 180.0);
        tree.add("SKU001", "Producto A", "Categoría A", 100.0, 90.0);

        // Verificar que cada producto se pueda encontrar
        Node productoA = tree.Buscar("SKU001");
        Node productoB = tree.Buscar("SKU002");
        Node productoC = tree.Buscar("SKU003");

        assertNotNull("Producto A debe ser encontrado", productoA);
        assertNotNull("Producto B debe ser encontrado", productoB);
        assertNotNull("Producto C debe ser encontrado", productoC);
    }

    @Test
    public void testAddDuplicateSKU() {
        // Primera adición
        tree.add("SKU001", "Producto Original", "Categoría A", 100.0, 90.0);
        
        // Segunda adición con el mismo SKU
        tree.add("SKU001", "Producto Duplicado", "Categoría B", 200.0, 180.0);

        // Verificar que el primer producto agregado permanezca
        Node result = tree.Buscar("SKU001");
        
        assertNotNull("Producto debe ser encontrado", result);
        assertEquals("Los detalles del producto original deben permanecer", "Producto Original", result.Nombre_producto);
        assertEquals("La categoría original debe permanecer", "Categoría A", result.Categoria);
    }
}