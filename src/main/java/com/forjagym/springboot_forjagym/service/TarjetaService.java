package com.forjagym.springboot_forjagym.service;

import com.forjagym.springboot_forjagym.model.COMPRA.Tarjeta;

public interface TarjetaService {
    void registrarTarjeta(Tarjeta tarjeta);

    void validar(Tarjeta tarjeta, double monto);

}
