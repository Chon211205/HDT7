package main.java.com.inventario;

public class BinarySearchTree {
    private Node root;

    //Metodo para agregar un producto.
    /**
     * @param Sku codigo producto
     * @param Nombre_producto nombre del producto
     * @param Categoria categoria del producto
     * @param Precio_r precio al publico
     * @param Precio_c precio actual
     */
     public void add(String Sku, String Nombre_producto, String Categoria, double Precio_r, double Precio_c) {
        //Almacena la informacion del prodcuto para buscarla en los arboles o datos.
        root = addRecursion(root, Sku, Nombre_producto, Categoria, Precio_r, Precio_c);
     }

    //Metodo de recursion
     private Node addRecursion(Node root, String Sku, String Nombre_producto, String Categoria, double Precio_r, double Precio_c) {
        //Evalua si la rama esta vacio; si cumple, agrega un primer nodo.
        if (root ==null) {
            return new Node(Sku, Nombre_producto, Categoria, Precio_r, Precio_c);
        }
        if (Sku.compareTo(root.Sku) < 0) {
            root.left = addRecursion(root.left, Sku, Nombre_producto, Categoria, Precio_r, Precio_c);
        } else if (Sku.compareTo(root.Sku) > 0) {
            root.right = addRecursion(root.right, Sku, Nombre_producto, Categoria, Precio_r, Precio_c);
        }
        return root;
    }

    //Metodo de busqueda recursiva.
    public Node BusquedaRecursiva(Node root, String Sku) {
        //Evalua si la rama esta vacia y que el codigo Sku sea el mismo que busca el usuario.
        if (root == null || root.Sku.equals(Sku)) {
            return root;
        }
        if (Sku.compareTo(root.Sku) < 0) {
            return BusquedaRecursiva(root.left, Sku);
        }
        return BusquedaRecursiva(root.right, Sku);
    }

    //Metodo para buscar un producto por su codigo.
    public Node Buscar(String Sku) {
        return BusquedaRecursiva(root, Sku);
    }


    
}
