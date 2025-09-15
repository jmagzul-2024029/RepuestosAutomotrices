package com.jorgemagzul.tiendarepuestos.controller;

import com.jorgemagzul.tiendarepuestos.model.Proveedor;
import com.jorgemagzul.tiendarepuestos.service.ProveedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proveedores")

public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService){
        this.proveedorService = proveedorService;
    }

    //obtener todos los proveedores
    @GetMapping
    public List<Proveedor> getAllProveedores(){
        return proveedorService.getAllProveedores();
    }

    //obtener proveedor por id
    @GetMapping("/{id}")
    public Proveedor getProveedorById(@PathVariable Integer id_proveedor){
        return proveedorService.getProveedorById(id_proveedor);
    }

    //crear un nuevo proveedir
    @PostMapping
    public Proveedor createProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.saveProveedor(proveedor);
    }

    //actualizar/editar proveedor
    @PutMapping("/{id_proveedor}")
    public Proveedor updateProveedor(@PathVariable Integer id_proveedor, @RequestBody Proveedor proveedor){
        return proveedorService.updateProveedor(id_proveedor, proveedor);
    }

    //Eliminar proveedor por id
    @DeleteMapping("/{id_proveedor}")
    public void deleteProveedor(@PathVariable Integer id_proveedor){
        proveedorService.deleteProveedor(id_proveedor);
    }

    //errores de datos invalidos
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handlerIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    //errores personalizados
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> HandlerResponseStatusException(ResponseStatusException ex){
        return ResponseEntity.status(ex.getStatusCode()).body(Map.of("error", ex.getReason()));
    }
}
