package com.jorgemagzul.tiendarepuestos.service;

import com.jorgemagzul.tiendarepuestos.model.Repuesto;

import java.util.List;

public interface RepuestoService {
    List<Repuesto> getAllRepuestos();
    Repuesto getRepuestoById(Integer id_Repuesto);
    Repuesto saveRepuesto(Repuesto Repuesto);
    Repuesto updateRepuesto(Integer id_Repuesto, Repuesto Repuesto);
    void deleteRepuesto(Integer id_Repuesto); //se crea un metodo
}