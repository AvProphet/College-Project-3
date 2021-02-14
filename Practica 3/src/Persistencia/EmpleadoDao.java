package Persistencia;

import Dominio.Empleado;

import java.io.*;
import java.util.*;

public class EmpleadoDao {

    public EmpleadoDao() {
    }

    public List<Empleado> leerEmpleados() throws IOException {
        List<Empleado> empleados = new ArrayList<Empleado>();
        Scanner in = new Scanner(new FileReader("empleados.txt"));
        in.next();
        int contador = in.nextInt();
        // read
        for (int i = 0; i < contador; i++) {
            in.next();
            String codigo = in.next();
            in.next();
            in.nextLine();
            String nombre = in.nextLine();
            in.next();
            String password = in.next();
            Empleado emp = new Empleado(codigo, nombre, password);
            empleados.add(emp);
        }
        return empleados;
    }

    public void escribirEmpleados(List<Empleado> empleados) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("empleados.txt"));
        out.println("Empleados:");
        out.println(empleados.size());
        for (int i = 0; i < empleados.size(); i++) {
            out.println("Codigo:");
            out.println(empleados.get(i).getCodigo_acceso());
            out.println("Nombre:");
            out.println(empleados.get(i).getNombreUsuario());
            out.println("Password:");
            out.println(empleados.get(i).getPassword());
        }
        out.close();
    }
}