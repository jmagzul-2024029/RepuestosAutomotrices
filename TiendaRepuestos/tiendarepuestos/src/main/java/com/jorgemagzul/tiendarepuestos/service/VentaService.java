package com.jorgemagzul.tiendarepuestos.service;

import com.jorgemagzul.tiendarepuestos.model.Venta;

import java.util.List;

public interface VentaService {
        List<Venta> getAllVentas();
        Venta getVentaById(Integer id_venta);
        Venta saveVenta(Venta Venta);
        Venta updateVenta(Integer id_venta, Venta Venta);
        void deleteVenta(Integer id_venta); //se crea un metodo
}