package com.forjagym.springboot_forjagym.repository.memoria.CLIENTE;

import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TarjetaMemoria {
    private final Map<String, String> cvvXTarjeta = new HashMap<>();
    private final Map<String, YearMonth> vencimientoXTarjeta = new HashMap<>();
    private final Map<String, Double> saldoXTarjeta = new HashMap<>();

    public TarjetaMemoria() {
        guardar("1111111111", "123", YearMonth.of(2027, 12), 500);
        guardar("2222222222", "456", YearMonth.of(2027, 6), 1000);
        guardar("3333333333", "789", YearMonth.of(2027, 1), 10);
    }

    public void guardar(String numero, String cvv, YearMonth vencimiento, double saldo) {
        cvvXTarjeta.put(numero, cvv);
        vencimientoXTarjeta.put(numero, vencimiento);
        saldoXTarjeta.put(numero, saldo);
    }

    public boolean existeTarjeta(String numTarjeta) {
        return cvvXTarjeta.containsKey(numTarjeta);
    }

    public String getCvv(String numTarjeta) {
        return cvvXTarjeta.get(numTarjeta);
    }

    public YearMonth getVencimiento(String numTarjeta) {
        return vencimientoXTarjeta.get(numTarjeta);
    }

    public double getSaldo(String numTarjeta) {
        return saldoXTarjeta.getOrDefault(numTarjeta, 0.0);
    }

    public void descontar(String numTarjeta, double monto) {
        saldoXTarjeta.put(numTarjeta, getSaldo(numTarjeta) - monto);
    }
}
