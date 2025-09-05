package org.example.practica1;


public class Main {
    public static void main(String[] args) {

        Cajero cajero = new Cajero();
        cajero.agregarUsuario(new Usuario("1234", "Juan", 1000.0));
        cajero.agregarUsuario(new Usuario("5678", "Maria", 2500.0));

        cajero.menuInicio();
    }
}