package Persistencia;

import Dominio.Empleado;
import Dominio.Producto;

import java.io.*;
import java.util.*;

public class ProductDao {

    public ProductDao() {

    }

    public List<Producto> leerProductos() throws IOException {
        List<Producto> productos = new ArrayList<Producto>();
        Scanner in = new Scanner(new FileReader("productos.txt"));
        in.next();
        int contador = in.nextInt();
        //read
        for (int i = 0; i < contador; i++) {
            in.next();
            int codigo = in.nextInt();
            in.next();
            in.nextLine();
            String nombre = in.nextLine();
            in.next();
            double precio = Double.parseDouble(in.next());
            Producto prod = new Producto(codigo, nombre, precio);
            productos.add(prod);
        }

        return productos;
    }

    public void escribirProductos(List<Producto> productos) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("productos.txt"));
        out.println("Productos:");
        out.println(productos.size());
        for (int i = 0; i < productos.size(); i++) {
            out.println("Codigo:");
            out.println(productos.get(i).getCodigo());
            out.println("Nombre:");
            out.println(productos.get(i).getNombre());
            out.println("Precio:");
            out.printf("%.2f\n",productos.get(i).getPrecio());
        }
        out.close();
    }
}
