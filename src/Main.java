import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Instancias
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree arbol = new BinarySearchTree();

        //Lectura del archivo .csv.
        String Filepath = "data.csv";
        String Linea;
        String Separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(Filepath))) {
            br.readLine(); 
            while ((Linea = br.readLine()) != null) {
                String[] datos = Linea.split(Separador);
                if (datos.length < 5) continue;

                String Sku = datos[6].trim();
                String Nombre_Producto = datos[0].trim();
                String Categoria = datos[4].trim();
                double Precio_r = 0.0;
                double Precio_c = 0.0;

                try {
                    Precio_r = datos[9].trim().isEmpty() ? 0.0 : Double.parseDouble(datos[9].trim());
                    Precio_c = datos[10].trim().isEmpty() ? 0.0 : Double.parseDouble(datos[10].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir los valores numéricos en la línea: ");
                }

                arbol.add(Sku, Nombre_Producto, Categoria, Precio_r, Precio_c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Interfaz de control de parte del usuario.
        String Op = "si";
        while(Op.equals("si")) {
            System.out.println("---Interfaz de control de inventario---");
            System.out.println("Ingrese el codigo Sku del producto que busca: ");
            String Sku_usuario = scanner.nextLine();
            Node resultado = arbol.Buscar(Sku_usuario);
            
            if(resultado != null) {
                System.out.println("Producto encontrado:");
                System.out.println("SKU: " + resultado.Sku);
                System.out.println("Nombre: " + resultado.Nombre_producto);
                System.out.println("Categoría: " + resultado.Categoria);
                System.out.println("Precio Retail: $" + resultado.Precio_r);
                System.out.println("Precio Actual: $" + resultado.Precio_c);

                System.out.println("¿Desea buscar otro producto? (si/no): ");
                Op = scanner.nextLine();
            } else {
                System.out.println("El producto no se encuentra disponible en el inventario");
                System.out.println("¿Desea buscar otro producto? (si/no): ");
                Op = scanner.nextLine();

            }
        }
    }
}
