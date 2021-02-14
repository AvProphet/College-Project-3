package Dominio;

import Persistencia.EmpleadoDao;

import java.io.IOException;
import java.util.List;

public class Empleado {
    private String codigo_acceso;
    private String nombreUsuario;
    private String password;
    private static EmpleadoDao empDao = new EmpleadoDao();

    public Empleado(String codigo_acceso, String nombreUsuario, String password) {
        this.codigo_acceso = codigo_acceso;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public Empleado() {

    }

    public String getCodigo_acceso() {
        return codigo_acceso;
    }

    public void setCodigo_acceso(String codigo_acceso) {
        this.codigo_acceso = codigo_acceso;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Empleado [" +
                "codigo_acceso=" + codigo_acceso +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", password='" + password + '\'' +
                ']';
    }

    public List<Empleado> leerEmpleados() throws IOException {
        return empDao.leerEmpleados();
    }

    public void escribirEmpleados(List<Empleado> empleados) throws IOException {
        empDao.escribirEmpleados(empleados);
    }
}
