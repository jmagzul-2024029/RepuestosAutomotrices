package com.jorgemagzul.tiendarepuestos.controller;

import com.jorgemagzul.tiendarepuestos.model.Venta;
import com.jorgemagzul.tiendarepuestos.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService){
        this.ventaService = ventaService;
    }

    //obtener todas las ventas
    @GetMapping
    public List<Venta> getAllVentas(){
        return ventaService.getAllVentas();
    }

    //obtener repuestos por id
    @GetMapping("/{id}")
    public Venta getVentaById(@PathVariable Integer id_venta){
        return ventaService.getVentaById(id_venta);
    }

    //crear un nuevo venta
    @PostMapping
    public Venta createVenta(@RequestBody Venta venta){
        return ventaService.saveVenta(venta);
    }

    //actualizar/editar venta
    @PutMapping("/{id_venta}")
    public Venta updateVenta(@PathVariable Integer id_venta, @RequestBody Venta venta){
        return ventaService.updateVenta(id_venta, venta);
    }

    //Eliminar venta por id
    @DeleteMapping("/{id_venta}")
    public void deleteventa(@PathVariable Integer id_venta){
        ventaService.deleteVenta(id_venta);
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
