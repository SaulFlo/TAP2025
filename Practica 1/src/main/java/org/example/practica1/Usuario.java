package org.example.practica1;

public class Usuario {
    private String usuario;
    private String nombre;
    private double saldo;

    public Usuario(String usuario, String nombre, double saldoInicial) {
        this.usuario = usuario;
        this.nombre = nombre;
        saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
