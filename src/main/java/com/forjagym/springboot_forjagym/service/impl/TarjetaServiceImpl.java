package com.forjagym.springboot_forjagym.service.impl;

import com.forjagym.springboot_forjagym.model.COMPRA.Tarjeta;
import com.forjagym.springboot_forjagym.repository.memoria.CLIENTE.TarjetaMemoria;
import com.forjagym.springboot_forjagym.service.TarjetaService;
import org.springframework.stereotype.Service;

@Service
public class TarjetaServiceImpl implements TarjetaService {
    private static final double saldo_inicial = 120;
    private final TarjetaMemoria tarjetamemoria;

    public TarjetaServiceImpl(TarjetaMemoria tarjetamemoria) {
        this.tarjetamemoria = tarjetamemoria;
    }

    @Override
    public void registrarTarjeta(Tarjeta tarjeta) {
        if (tarjetamemoria.existeTarjeta(tarjeta.getNumTarjeta())) {
            throw new IllegalArgumentException("Esta tarjeta ya existe");
        }
        tarjetamemoria.guardar(tarjeta.getNumTarjeta(), tarjeta.getNumCVV(), tarjeta.getFechaVen(), saldo_inicial);

    }

    @Override
    public void validar(Tarjeta tarjeta, double monto) {
        String numero = tarjeta.getNumTarjeta();
        if (!tarjetamemoria.existeTarjeta(numero)) {
            throw new IllegalArgumentException("La tarjeta ingresada no existe o no está registrada");
        }
        if (!tarjeta.getNumCVV().equals(tarjetamemoria.getCvv(numero))) {
            throw new IllegalArgumentException("El CVV ingresado es incorrecto");
        }
        if (!tarjeta.getFechaVen().equals(tarjetamemoria.getVencimiento(numero))) {
            throw new IllegalArgumentException("La fecha de vencimiento es incorrecta");
        }
        if (tarjetamemoria.getSaldo(numero) < monto) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        tarjetamemoria.descontar(numero, monto);
    }


}
