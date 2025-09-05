package Models;

import java.util.HashMap;
import java.util.Map;

public class CajeroModel {
    private Map<String, Cuenta> cuentas;
    private Cuenta cuentaActual;

    public CajeroModel() {
        cuentas = new HashMap<>();
        inicializarCuentas();
    }

    public void inicializarCuentas() {
        cuentas.put("12345", new Cuenta("12345", "1111", 5000, "Juan Perez"));
        cuentas.put("10025", new Cuenta("10025", "2233", 3500, "Martha Chavez"));
        cuentas.put("20971", new Cuenta("20971", "4522", 9060, "Rodrigo Mendez"));
    }

    public boolean autenticar(String numCuenta, String pin) {
        Cuenta cuenta = cuentas.get(numCuenta);
        if(cuenta != null && cuenta.validarPin(pin)) {
            this.cuentaActual = cuenta;
            return true;
        }
        return false;
    }

    public Cuenta getCuentaActual() {
        return cuentaActual;
    }
    public void setCuentaActual(Cuenta cuentaActual) {this.cuentaActual = cuentaActual;}
    public Map<String, Cuenta> getCuentas() {return cuentas;}

    public double consultarSaldo() {
        return this.cuentaActual != null ? cuentaActual.getSaldo() : 0;
    }

    public boolean realizarRetiro(double retiro){
        return this.cuentaActual != null && this.cuentaActual.retirar(retiro);
    }

    public boolean realizarDeposito(double deposito){
        if(this.cuentaActual != null && deposito > 0){
            this.cuentaActual.depositar(deposito);
            return true;
        }
        return false;
    }

    public boolean cuentaExiste(String numCuenta) {
        return cuentas.containsKey(numCuenta);
    }

    public boolean realizarTransferencia(Cuenta cuentaTransferir, double monto){
        if(cuentaActual.transferir(cuentaTransferir, monto))
            return true;

        return false;
    }

    public boolean cambiarPin(String pinNuevo) {
        if(valirdarPin(pinNuevo)){
            cuentaActual.cambiarPin(pinNuevo);
            return true;
        }
        return false;
    }

    public static boolean valirdarPin(String pin) {
        return pin.matches("\\d{4}");
    }
}
