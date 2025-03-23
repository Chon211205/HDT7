public class Node {
    //Atributos
    String Sku;
    String Nombre_producto;
    String Categoria;
    double Precio_r;
    double Precio_c;
    Node left, right;
    
    //Constructor
    public Node(String Sku, String Nombre_producto, String Categoria, double Precio_r, double Precio_c) {
        this.Sku = Sku;
        this.Nombre_producto = Nombre_producto;
        this.Categoria = Categoria;
        this.Precio_r = Precio_r;
        this.Precio_c = Precio_c;
        this.left = this.right = null;
    }
}
