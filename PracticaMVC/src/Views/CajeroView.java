package Views;

import java.util.Scanner;

public class CajeroView {
    private Scanner sc;
    public CajeroView() {
        sc = new Scanner(System.in);
    }

    public void mostrarBienvenida(){
        System.out.println("===============================");
        System.out.println("Bienvenido a cajero de BBVA");
        System.out.println("===============================");
    }

    public String solicitarCuenta(){
        System.out.println("Ingrese su número de cuenta:");
        return sc.nextLine();
    }

    public String solicitarPin(){
        System.out.println("Ingrese su PIN:");
        return sc.nextLine();
    }

    public void mostrarMenu(String titular){
        System.out.println("===============================");
        System.out.println("Bienvenid@ " + titular);
        System.out.println("===============================");

        System.out.println("1. Consultar saldo");
        System.out.println("2. Realizar retiro");
        System.out.println("3. Realizar deposito");
        System.out.println("4. Realizar transferencia");
        // Definir las opciones restantes
        System.out.println("9. Salir");
    }

    public int leerOpcion(){
        try {
            return Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e) { return -1;}
    }

    public void mostrarSaldo(double saldo){
        System.out.println("======================================");
        System.out.println("Tu saldo es : $" + saldo);
        System.out.println("======================================");
    }

    public String solicitarCuenta(String operacion){
        System.out.println("Ingresa la cuenta a " + operacion + ": ");
        return sc.nextLine();
    }

    public double solicitarCantidad(String operacion){
        System.out.println("Ingresa la cantidad a " + operacion + ": ");
        try {
            return Double.parseDouble(sc.nextLine());
        }catch (NumberFormatException e){
            return -1;
        }
    }

    public void mostrarMensaje(String mensaje){
        System.out.println("==== "+mensaje);
    }
}
