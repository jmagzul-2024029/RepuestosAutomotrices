package com.jorgemagzul.tiendarepuestos.controller;

import com.jorgemagzul.tiendarepuestos.model.Repuesto;
import com.jorgemagzul.tiendarepuestos.service.RepuestoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/repuestos")

public class RepuestoController {

    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService){
        this.repuestoService = repuestoService;
    }

    //obtener todos los repuestos
    @GetMapping
    public List<Repuesto> getAllRepuestos(){
        return repuestoService.getAllRepuestos();
    }

    //obtener repuestos por id
    @GetMapping("/{id}")
    public Repuesto getRepuestoById(@PathVariable Integer id_repuesto){
        return repuestoService.getRepuestoById(id_repuesto);
    }

    //crear un nuevo repuesto
    @PostMapping
    public Repuesto createRepuesto(@RequestBody Repuesto repuesto){
        return repuestoService.saveRepuesto(repuesto);
    }

    //actualizar/editar repuesto
    @PutMapping("/{id_repuesto}")
    public Repuesto updateRepuesto(@PathVariable Integer id_repuesto, @RequestBody Repuesto repuesto){
        return repuestoService.updateRepuesto(id_repuesto, repuesto);
    }

    //Eliminar repuesto por id
    @DeleteMapping("/{id_repuesto}")
    public void deleteRepuesto(@PathVariable Integer id_repuesto){
        repuestoService.deleteRepuesto(id_repuesto);
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
