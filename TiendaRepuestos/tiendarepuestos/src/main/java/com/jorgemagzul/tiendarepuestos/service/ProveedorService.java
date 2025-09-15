package com.jorgemagzul.tiendarepuestos.service;

import com.jorgemagzul.tiendarepuestos.model.Proveedor;

import java.util.List;

public interface ProveedorService {
    List<Proveedor> getAllProveedores();
    Proveedor getProveedorById(Integer id_proveedor);
    Proveedor saveProveedor(Proveedor Proveedor);
    Proveedor updateProveedor(Integer id_proveedor, Proveedor Proveedor);
    void deleteProveedor(Integer id_proveedor); //se crea un metodo
}