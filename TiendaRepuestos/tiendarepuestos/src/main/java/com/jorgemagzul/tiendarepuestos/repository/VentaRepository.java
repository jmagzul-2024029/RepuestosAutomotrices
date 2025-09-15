package com.jorgemagzul.tiendarepuestos.repository;

import com.jorgemagzul.tiendarepuestos.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    //sin validaciones para no bloquear la compra que hace un mismo cliente
}
