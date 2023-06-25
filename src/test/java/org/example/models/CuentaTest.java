package org.example.models;

import org.example.exceptions.DineroInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {
  Cuenta cuenta;

  @Test
  void miPrimerTest() {
    assertTrue(true);
  }

  @BeforeEach
  void setUp() {
    this.cuenta = new Cuenta("Camila", 100000);
  }

  @Test
  @DisplayName("Test Nombre Cuenta:")
  void testNombreCuenta() {
    String nombreEsperado= "Camila";
    String real= cuenta.getPersona();
    assertAll(
            ()-> assertEquals(nombreEsperado, real, "El nombre obtenido no es el esperado"),
            ()-> assertTrue(real.equals("Camila"))
    );
  }

  @Test
  void testDeposito() {
    Integer saldoInicial= cuenta.getSaldo();
    Integer deposito = 20000;
    cuenta.deposit(deposito);
    assertTrue(cuenta.getSaldo()== (saldoInicial+deposito));

  }

  @Test
  void testTransfer() {
    Cuenta cuenta2 =  new Cuenta("Rogrigo", 10000);
    Banco BCI= new Banco("BCI");
    BCI.addCuenta(cuenta2);
    BCI.addCuenta(cuenta);
    assertEquals(BCI.getCuentas().size(), 2);

    BCI.transfer(cuenta, cuenta2, 20000);
    assertTrue(cuenta2.getSaldo()==30000);
  }


  @Test
  @EnabledOnOs(OS.MAC)
  void testSoloWindows(){
    fail();
  }

  @Test
  @EnabledIfSystemProperty(named="user.language", matches = "es")
  void porSystemProperties() {
    Properties properties = System.getProperties();
    properties.forEach((k, v)-> System.out.println(k + ": " + v));
    System.out.println("========================= ENV");
    Map<String, String> env=System.getenv();
    env.forEach((k, v)-> System.out.println(k + ": " + v));
  }


  //TEST EXCEPTIONS

  @Test
  void dineroInsuficiente() {
    Exception exception= assertThrows(
            DineroInsuficienteException.class, () ->{
              cuenta.retiro(1000000);
            }
    );
  }
}