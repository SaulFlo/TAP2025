package Controllers;

import Models.CajeroModel;
import Models.Cuenta;
import Views.CajeroView;

public class CajeroController {
    private CajeroModel model;
    private CajeroView view;
    private boolean sistemaActivo;

    public CajeroController(CajeroModel model, CajeroView view){
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
    }

    public void iniciarSistema(){
        view.mostrarBienvenida();
        while (sistemaActivo){
            if(autenticarUsuario()){
                ejecutarMenu();
            }else{
                view.mostrarMensaje("Credenciales incorrectas");
            }
        }
        view.mostrarMensaje("Gracias por usar nuestro cajero");
    }

    private boolean autenticarUsuario(){
        String numeroCuenta = view.solicitarCuenta();
        String pin = view.solicitarPin();
        return model.autenticar(numeroCuenta, pin);
    }

    private void ejecutarMenu(){
        boolean sesionActiva = true;
        while(sesionActiva){
            view.mostrarMenu(model.getCuentaActual().getTitular());
            int opcion = view.leerOpcion();
            switch (opcion){
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    realizarRetiro();
                    break;
                case 3:
                    realizarDeposito();
                    break;
                case 4:
                    realizarTransferencia();
                    break;
                case 5:
                    cambiarPin();
                    break;
                case 9:
                    sesionActiva = false;
                    model.setCuentaActual(null);
                    break;
                    default:
                        view.mostrarMensaje("Opcion invalida");
            }
        }
    }
    public void consultarSaldo(){
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }

    private void realizarRetiro(){
        double cantidad = view.solicitarCantidad("Retirar");
        if (cantidad <= 0) {
            view.mostrarMensaje("Cantidad inválida");
            return;
        }
        if (model.realizarRetiro(cantidad)) {
            view.mostrarMensaje("Retiro exitoso de "+cantidad);
        }else{
            view.mostrarMensaje("Fondos insuficientes");
        }
    }

    public void realizarDeposito(){
        double cantidad = view.solicitarCantidad("Depositar");
        if (cantidad <= 0) {
            view.mostrarMensaje("Error en la cantidad");
            return;
        }
        if (model.realizarDeposito(cantidad)) {
            view.mostrarMensaje("Depósito exitoso de "+cantidad);
        }else{
            view.mostrarMensaje("Error al procesar el deposito");
        }
    }

    public void realizarTransferencia(){
        String numCuenta = view.solicitarString("Ingrese el número de cuenta a la que se desea transferir:");
        double cantidad = view.solicitarCantidad("Transferir");

        if (model.cuentaExiste(numCuenta)) {
            Cuenta cuentaTransferir = model.getCuentas().get(numCuenta);
            if(model.getCuentaActual().transferir(cuentaTransferir, cantidad))
                view.mostrarMensaje("Transferencia exitosa de $"+cantidad+" a la cuenta de "+cuentaTransferir.getTitular());
            else
                view.mostrarMensaje("Fondos insuficientes");
        }
        else {
            view.mostrarMensaje("La cuenta a la que se intenta transferir no existe.");
        }
    }

    public void cambiarPin(){
        String pin = view.solicitarPin();
        if (model.getCuentaActual().validarPin(pin)) {
            String pinNuevo = view.solicitarString("Ingrese su pin nuevo");
            if(model.cambiarPin(pinNuevo))
                view.mostrarMensaje("Pin cambiado correctamente");
            else
                view.mostrarMensaje("El nuevo pin debe ser de 4 números.");
        }
        else
            view.mostrarMensaje("Pin incorrecto. Para modificar el pin ocupamos validar.");
    }

}
