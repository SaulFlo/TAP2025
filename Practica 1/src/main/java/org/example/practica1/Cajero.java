package org.example.practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cajero {
    private Scanner sc = new Scanner(System.in);
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private int inMax;
    private Usuario usuSelec;

    public Cajero() {
        usuSelec = null;
    }

    public void menuInicio(){
        inMax = 3;
        System.out.println("=== Bienvenido al Cajero ===");

        while (inMax > 0 && usuSelec == null) {
            System.out.print("Ingrese su PIN: ");
            String pin = sc.nextLine();
            if((buscarUsuario(pin)))
                menuUsuario();
            else{
                System.out.println("PIN incorrecto.");
                inMax--;
            }
        }

        if(usuSelec == null)
            System.out.println("Demasiados intentos fallidos. Adiós.");

    }

    public void menuUsuario(){
        boolean salir = false;
        System.out.println("Bienvenido, " + usuSelec.getNombre());

        while (!salir) {
            System.out.println("Qué desea hacer?");
            System.out.println("\n1. Ver saldo");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                System.out.println("Su saldo es: $" + usuSelec.getSaldo());
            } else if (opcion == 2) {
                System.out.print("Ingrese cantidad a retirar: ");
                double retiro = sc.nextDouble();
                retirarDinero(retiro);
            } else if (opcion == 3) {
                System.out.print("Ingrese cantidad a depositar: ");
                double deposito = sc.nextDouble();
                depositarDinero(deposito);
            } else if (opcion == 4) {
                salir = true;
                System.out.println("Gracias por usar el cajero.");
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    public void depositarDinero(double deposito){
        usuSelec.setSaldo(usuSelec.getSaldo() + deposito);
        System.out.println("Depósito exitoso. Nuevo saldo: $" + usuSelec.getSaldo());
    }

    public void retirarDinero(double monto){
        if (monto <= usuSelec.getSaldo()) {
            usuSelec.setSaldo(usuSelec.getSaldo() - monto);
            System.out.println("Retiro exitoso. Nuevo saldo: $" + usuSelec.getSaldo());
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    public boolean buscarUsuario(String pin) {
        boolean existe = false;

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsuario().equals(pin)) {
                usuSelec = usuarios.get(i);
                existe = true;
            }
        }

        return existe;
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
}
