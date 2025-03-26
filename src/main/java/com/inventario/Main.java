package main.java.com.inventario;

import java.io.*;
import java.util.*;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // Instancias
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree arbol = new BinarySearchTree();

        // Intentar varias rutas posibles para el archivo CSV
        String[] possiblePaths = {
            "data.csv",                 // Directorio raíz
            "src/main/resources/data.csv",  // Segunda opción
             
        };

        String filePath = null;
        File file = null;

        // Buscar el archivo en las posibles rutas
        for (String path : possiblePaths) {
            file = new File(path);
            if (file.exists()) {
                filePath = path;
                System.out.println("Archivo CSV encontrado en: " + file.getAbsolutePath());
                break;
            }
        }

        // Verificar si se encontró el archivo
        if (filePath == null) {
            System.err.println("No se pudo encontrar el archivo CSV");
            return;
        }

        // Lectura del archivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Saltar encabezado
            br.readLine(); 

            String linea;
            String separador = ",";

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(separador);
                
                // Validar longitud de datos
                if (datos.length < 11) {
                    System.out.println("Línea incompleta: " + linea);
                    continue;
                }

                try {
                    String sku = datos[6].trim();
                    String nombreProducto = datos[0].trim();
                    String categoria = datos[4].trim();
                    
                    // Parseo de precios con manejo de errores
                    double precioR = parsePrecio(datos[9]);
                    double precioC = parsePrecio(datos[10]);

                    // Agregar al árbol
                    arbol.add(sku, nombreProducto, categoria, precioR, precioC);

                } catch (Exception e) {
                    System.out.println("Error procesando línea: " + linea);
                    System.out.println("Detalle: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Interfaz de control de parte del usuario
        String op = "si";
        while(op.equalsIgnoreCase("si")) {
            System.out.println("---Interfaz de control de inventario---");
            System.out.println("Ingrese el codigo Sku del producto que busca: ");
            String skuUsuario = scanner.nextLine();
            
            Node resultado = arbol.Buscar(skuUsuario);
            
            if(resultado != null) {
                System.out.println("Producto encontrado:");
                System.out.println("SKU: " + resultado.Sku);
                System.out.println("Nombre: " + resultado.Nombre_producto);
                System.out.println("Categoría: " + resultado.Categoria);
                System.out.println("Precio Retail: $" + resultado.Precio_r);
                System.out.println("Precio Actual: $" + resultado.Precio_c);
            } else {
                System.out.println("El producto no se encuentra disponible en el inventario");
            }

            System.out.println("¿Desea buscar otro producto? (si/no): ");
            op = scanner.nextLine();
        }
    }

    // Método auxiliar para parsear precios
    private static double parsePrecio(String precioStr) {
        try {
            return precioStr == null || precioStr.trim().isEmpty() ? 0.0 : Double.parseDouble(precioStr.trim());
        } catch (NumberFormatException e) {
            System.out.println("Error parseando precio: " + precioStr);
            return 0.0;
        }
    }
}