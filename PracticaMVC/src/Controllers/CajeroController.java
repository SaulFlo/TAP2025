package Controllers;

import Models.CajeroModel;
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
        String cuenta = view.solicitarCuenta("Transferir");
        double cantidad = view.solicitarCantidad("Transferir");

        if (cantidad <= 0) {
            view.mostrarMensaje("Error en la cantidad");
            return;
        }
        if (model.realizarTransferencia(cuenta, cantidad)) {
            view.mostrarMensaje("Transferencia exitosa de $"+cantidad);
        }else{
            view.mostrarMensaje("Error al procesar el deposito");
        }
    }
}
