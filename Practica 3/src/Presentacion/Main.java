package Presentacion;

import Dominio.*;

import java.io.*;
import java.util.*;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static String codigoLogin;

    public static void main(String[] args) throws IOException { //Añadimos objetos a la programa
        List<Empleado> empleados = new ArrayList<>();           //y escribimos objetos en fichero
        Empleado empleado = new Empleado("1", "Oleh", "123");
        Empleado empleado1 = new Empleado("2", "Alex", "1234");
        Empleado empleado2 = new Empleado("3", "Daniel", "12345");

        empleados.add(empleado);
        empleados.add(empleado1);
        empleados.add(empleado2);

        empleado.escribirEmpleados(empleados);

        Empleado loggedIn = new Empleado();

        List<Producto> productos = new ArrayList<>();
        Producto producto = new Producto(1, "soap", 2.43);
        Producto producto1 = new Producto(2, "banana", 5);
        Producto producto2 = new Producto(3, "book", 25.5);
        Producto producto3 = new Producto(4, "phone", 225.5);
        Producto producto4 = new Producto(5, "laptop", 2199.0);

        productos.add(producto);
        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
        productos.add(producto4);

        producto.escribirProductos(productos);

        RandomAccessFile file = new RandomAccessFile("empleados.txt", "rw");
        RandomAccessFile file1 = new RandomAccessFile("productos.txt", "rw");

        boolean logOut;
        boolean existe = false;
        do {                                                       //inicializamos el bloque general de programa
            System.out.print("Introduzca su nombre de usuario: "); //con login y su menu principal
            codigoLogin = in.next();
            System.out.print("Intrduzca su contraseña: ");
            String password = in.next();
            if (userValidation(password, empleados, loggedIn)) {
                logOut = false;
                boolean returnMenu;
                System.out.println("\nBienvenido a la sesion");
                do {                                        //menu principal con switch-case
                    returnMenu = false;
                    System.out.println("\n[1] Make order");
                    System.out.println("[2] Modificar producto");
                    System.out.println("[3] Cambiar contraseña empleado");
                    System.out.println("[4] Log out");

                    System.out.print("\nInsert selection: ");
                    int function = in.nextInt();
                    switch (function) {
                        case 1: // CASE 1 HACER PEDIDO
                            List<Producto> basket = new ArrayList<>(); //Creamos la cesta
                            boolean modmenu = true;
                            while (modmenu) {
                                modmenu = false;

                                System.out.println("\n[1] Add product");
                                System.out.println("[2] Visualise precio total");
                                System.out.println("[3] Imprimir factura");
                                System.out.println("[4] Terminar pedido");

                                System.out.print("\nInsert selection: ");

                                int subMenuRedactor = in.nextInt();
                                in.nextLine();
                                switch (subMenuRedactor) {
                                    case 1: // Añadir producto
                                        for (int i = 0; i < productos.size(); i++) { //visualizamos todos los productos
                                            System.out.println("Producto numero " + (i + 1) + " " + productos.get(i));
                                        }
                                        basketAdd(basket, productos);
                                        System.out.println("\nProductos en cesta: ");  // visualizamos productos en cesta
                                        for (int j = 0; j < basket.size(); j++) {
                                            System.out.println(basket.get(j));
                                        }
                                        modmenu = true;
                                        break;
                                    case 2: // Visualizar precio total
                                        totalPrice(basket);
                                        System.out.printf("Precio de pedido es %.2f: ", totalPrice(basket));
                                        modmenu = true;
                                        break;
                                    case 3: // Imprimir factura
                                        double finalPrice = totalPrice(basket);
                                        checkVisual(basket, loggedIn, finalPrice);
                                        modmenu = true;
                                        break;
                                    case 4: // Terminar pedido
                                        returnMenu = true;
                                        basket.clear();
                                        break;
                                    default:
                                        System.out.println("\nThe selection was invalid!");
                                        modmenu = true;
                                }
                            }
                            break;
                        case 2: // CASE 2 MOD PRODUCT
                            boolean submenu = true;
                            for (int i = 0; i < productos.size(); i++) { // Mostramos todos los productos y comprobamos que ese producto es existente
                                System.out.println("Producto numero " + (i + 1) + " " + productos.get(i));
                            }
                            System.out.print("\nIntroduzca el codigo de producto que quieres modificar: "); // elegimos producto para modificar
                            int productoMod = in.nextInt();
                            Producto toModProduct = elegirProducto(productos, productoMod);
                            while (toModProduct.getCodigo() == 0) {
                                System.out.println("\nEste producto no existe, introduzca codigo de nuevo: ");
                                productoMod = in.nextInt();
                                toModProduct = elegirProducto(productos, productoMod);
                            }
                            while (submenu) {
                                submenu = false;

                                System.out.println("\n[1] Modificar nombre");
                                System.out.println("[2] Modificar precio");
                                System.out.println("[3] Modificar código");
                                System.out.println("[4] Return to the main menu");

                                System.out.print("\nInsert selection: ");

                                int subMenuRedactor = in.nextInt();
                                in.nextLine();
                                switch (subMenuRedactor) {
                                    case 1: //case 1 Modificar nombre
                                        productNameChanger(productos, toModProduct);
                                        producto.escribirProductos(productos);
                                        submenu = true;
                                        break;
                                    case 2: //case 2 Modificar precio
                                        productPriceChanger(productos, toModProduct);
                                        producto.escribirProductos(productos);
                                        submenu = true;
                                        break;
                                    case 3: // case 3 Modificar código
                                        productCodigoChanger(productos, toModProduct);
                                        producto.escribirProductos(productos);
                                        submenu = true;
                                        break;
                                    case 4: // case 4 Return to the main menu
                                        returnMenu = true;
                                        break;
                                    default:
                                        System.out.println("\nThe selection was invalid!");
                                        submenu = true;
                                }
                            }
                            break;
                        case 3: //CAMBIAR CONTRASEÑA
                            passChange(empleados, loggedIn);
                            empleado.escribirEmpleados(empleados);
                            System.out.println("\nHas cambiado contraseña");
                            returnMenu = true;
                            break;
                        case 4: // LOGOUT
                            System.out.println("\nGracias por usar nuestra programa - " + loggedIn.getNombreUsuario());
                            System.out.println("--------------SESSION WAS TERMINATED SUCCESSFULLY--------------");
                            logOut = true;
                            break;
                        default:
                            System.out.println("\nThe selection was invalid!");
                            returnMenu = true;
                    }
                }
                while (returnMenu);
            } else {
                System.out.println("\nHas introducido mal el codigo de acceso o la contraseña. ");
                logOut = true;
            }
        } while (logOut);
    }

    private static void checkVisual(List<Producto> basket, Empleado loggedIn, double finalPrice) { // Metodo de factura
        if (basket.size() == 0) {
            System.out.println("\nNo tienes productos en la cesta para obtener factura");
        } else {
            System.out.println("-------------Factura-------------");
            System.out.println("\nDatos de la persona que hace el pedido: " + loggedIn.getNombreUsuario() + "\n");
            for (int i = 0; i < basket.size(); i++) {
                System.out.println("Producto en la cesta con numero " + (i + 1) + " y con nombre " + basket.get(i).getNombre()
                        + " tiene precio " + basket.get(i).getPrecio());
            }
            System.out.printf("\nPrecio de pedido es - %.2f", finalPrice);
        }
    }

    private static double totalPrice(List<Producto> basket) { // Calculo de precio total de pedido
        double totalPrice = 0;
        for (int i = 0; i < basket.size(); i++) {
            totalPrice += basket.get(i).getPrecio();
        }
        return totalPrice;
    }

    private static void basketAdd(List<Producto> basket, List<Producto> productos) { // Metodo para añadir producto a la cesta
        if (productos.size() == basket.size()) {
            System.out.println("Ya tienes todos productos existentes en la cesta.");
        }
        Producto savedProduct = new Producto();
        String prodName = null;
        boolean exist = false;
        while (exist == false) {                       // Comprobamos que productos existe
            System.out.print("\nIntroduzca nombre del producto que quires comprar: ");
            prodName = in.nextLine();
            while (prodName == null || prodName.trim().length() == 0) { // comprobamos que no sea nombre nulo o vacio
                System.out.println("No puedes tener nombre vacio,\n" +
                        "Intoduzca nombre del producto que quires comprar: ");
                prodName = in.nextLine();
            }
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getNombre().equalsIgnoreCase(prodName)) {
                    savedProduct = productos.get(i);
                    exist = true;
                    break;
                }
                exist = false;
            }
            if (exist == false) {
                System.out.print("\nNo hay producto en stock.");
            }
            if (exist == true) {
                boolean prodExist = false;
                if (basket.size() < 1) {
                    basket.add(savedProduct);
                } else {                            // Verificamos que no haya 2 iguales productos en la cesta
                    while (prodExist == false) {
                        for (int i = 0; i < basket.size(); i++) {
                            if (basket.get(i).getNombre().equalsIgnoreCase(prodName)) {
                                prodExist = true;
                                System.out.println("\nEste producto ya esta en la cesta.");
                                break;
                            } else {
                                prodExist = false;
                            }
                        }
                        if (prodExist == false) {
                            basket.add(savedProduct);
                        }
                        prodExist = true;
                    }
                }
            }
        }
    }

    private static void productNameChanger(List<Producto> productos, Producto toModProduct) throws IOException { // Metodo para cambiar nombre de producto
        String newName = null;
        boolean exist = true;
        while (exist == true) {                 // comprobamos que no haya 2 nombres iguales
            System.out.print("\nIntoduzca nuevo nombre del producto: ");
            newName = in.nextLine();
            while (newName == null || newName.trim().length() == 0) { // comprobamos que no sea nombre nulo o vacio
                System.out.println("No puedes tener nombre vacio,\n" +
                        "Intoduzca nuevo nombre del producto: ");
                newName = in.nextLine();
            }
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getNombre().equalsIgnoreCase(newName)) {
                    exist = true;
                    System.out.print("\nEse nombre ya existe.");
                    break;
                }
                exist = false;
            }
        }
        for (int i = 0; i < productos.size(); i++) {
            if (toModProduct.getCodigo() == productos.get(i).getCodigo()) {
                productos.get(i).setNombre(newName);
            }
        }
    }

    private static void productPriceChanger(List<Producto> productos, Producto toModProduct) throws IOException { // Metodo para cambiar precio de producto
        System.out.print("Introduzca nuevo precio para el producto: ");
        double newPrice = in.nextDouble();
        while (newPrice <= 0) {
            System.out.println("No puedes tener precio negativo o nulo,\n" +
                    "Introduzca nuevo precio para el producto: ");
            newPrice = in.nextDouble();
        }
        for (int i = 0; i < productos.size(); i++) {
            if (toModProduct.getCodigo() == productos.get(i).getCodigo()) {
                productos.get(i).setPrecio(newPrice);
            }
        }
    }

    private static void productCodigoChanger(List<Producto> productos, Producto toModProduct) throws IOException { // Metodo para cambiar codigo de producto
        int newCode = 0;
        boolean exist = true;
        while (exist == true) {             // Comprobamos que no sean 2 codigos iguales o codigos negativos
            System.out.print("\nIntoduzca nuevo codigo del producto: ");
            newCode = in.nextInt();
            while (newCode < 0) {
                System.out.println("No puedes tener codigo negativo,\n" +
                        "Introduzca nuevo codigo para el producto: ");
                newCode = in.nextInt();
            }
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getCodigo() == newCode) {
                    exist = true;
                    System.out.print("\nEse codigo ya existe.");
                    break;
                }
                exist = false;
            }
        }
        for (int i = 0; i < productos.size(); i++) {
            if (toModProduct.getCodigo() == productos.get(i).getCodigo()) {
                productos.get(i).setCodigo(newCode);
            }
        }
    }

    private static Producto elegirProducto(List<Producto> productos, int productoMod) { //metodo para elegir producto que quremos modificar
        Producto object0 = new Producto();
        for (int j = 0; j < productos.size(); j++) {
            if (productos.get(j).getCodigo() == productoMod) {
                return productos.get(j);
            }
        }
        return object0;
    }


    public static boolean userValidation(String passwrod, List<Empleado> empleados, Empleado loggedIn) { // metodo para comprobar datos de usuario
        boolean verdadero = false;
        for (int i = 0; i < empleados.size(); i++) {
            if ((codigoLogin.equals(empleados.get(i).getCodigo_acceso())) && (passwrod.equals(empleados.get(i).getPassword()))) {
                verdadero = true;
                loggedIn.setCodigo_acceso(empleados.get(i).getCodigo_acceso()); //guardamos datos de usuario que esta en sistema
                loggedIn.setNombreUsuario(empleados.get(i).getNombreUsuario());
                loggedIn.setPassword(empleados.get(i).getPassword());
            }
        }
        return verdadero;
    }

    public static void passChange(List<Empleado> empleados, Empleado loggedIn) { // Metodo para cambiar contraseña
        System.out.print("Introduzca nueva contraseña de usuario: ");
        String newPass = in.next();
        for (int i = 0; i < empleados.size(); i++) {
            if (loggedIn.getPassword().equals(empleados.get(i).getPassword()) && (loggedIn.getCodigo_acceso() == empleados.get(i).getCodigo_acceso())) {
                empleados.get(i).setPassword(newPass);
            }
        }
    }
}
