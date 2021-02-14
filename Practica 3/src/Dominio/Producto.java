package Dominio;

import Persistencia.ProductDao;

import java.io.IOException;
import java.util.List;

public class Producto {
    private int codigo;
    private String nombre;
    private double precio;
    private static ProductDao prodDao = new ProductDao();

    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto() {

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("Producto: [" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio=%.2f]",precio);
    }

    public List<Producto> leerProductos() throws IOException {
        return prodDao.leerProductos();
    }

    public  void escribirProductos(List<Producto> productos) throws IOException {
        prodDao.escribirProductos(productos);
    }
}
