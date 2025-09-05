package Models;

public class Cuenta {
    private String numCuenta;
    private double saldo;
    private String pin;
    private String titular;

    public Cuenta(String numCuenta, String pin, double saldo, String titular){
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.pin = pin;
        this.titular = titular;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getPin() {
        return pin;
    }

    public String getTitular() {
        return titular;
    }

    //REGLAS DE NEGOCIO
    public boolean validarPin(String pin){
        return this.pin.equals(pin);
    }

    public boolean retirar(double retiro){
        if(retiro > 0 && retiro <= saldo){
            saldo -= retiro;
            return true;
        }
        return false;
    }

    public void depositar(double deposito){
        if(deposito > 0)
            saldo += deposito;
    }

    public boolean transferir(Cuenta cuentaTranferir, double monto){
        if(this.retirar(monto)) {
            cuentaTranferir.depositar(monto);
            return true;
        }
        return false;
    }

    public void cambiarPin(String pin){
        this.pin = pin;
    }
}
