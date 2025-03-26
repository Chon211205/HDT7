package test.java.com.inventario;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnotherBinarySearchTreeTest {

    private BinarySearchTree bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree();
    }

    @Test
    void testInsert() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertTrue(bst.contains(10));
        assertTrue(bst.contains(5));
        assertTrue(bst.contains(15));
    }

    @Test
    void testRemove() {
        bst.insert(10);
        bst.insert(5);
        bst.remove(5);
        assertFalse(bst.contains(5));
    }

    @Test
    void testFindMin() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertEquals(5, bst.findMin());
    }

    // Additional test cases can be added here
}
