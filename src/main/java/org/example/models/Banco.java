package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nombre;

    private List<Cuenta> cuentas = new ArrayList<>();

    public Banco(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void addCuenta(Cuenta cuenta){
        this.cuentas.add(cuenta);
        cuenta.setBanco(this);
    }

    public void transfer(Cuenta cuenta, Cuenta cuenta2, int monto) {
        cuenta.retiro(monto);
        cuenta2.deposit(monto);
    }
}
