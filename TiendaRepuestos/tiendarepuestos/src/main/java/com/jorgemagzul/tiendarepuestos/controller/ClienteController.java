package com.jorgemagzul.tiendarepuestos.controller;

import com.jorgemagzul.tiendarepuestos.model.Cliente;
import com.jorgemagzul.tiendarepuestos.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id_cliente}")
    public Cliente getClienteById(@PathVariable Integer id_cliente) {
        return clienteService.getClienteById(id_cliente);
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.saveCliente(cliente);
    }

    @PutMapping("/{id_cliente}")
    public Cliente updateCliente(@PathVariable Integer id_cliente, @RequestBody Cliente cliente) {
        return clienteService.updateCliente(id_cliente, cliente);
    }

    @DeleteMapping("/{id_cliente}")
    public void deleteCliente(@PathVariable Integer id_cliente) {
        clienteService.deleteCliente(id_cliente);
    }

    //metodo para manejar los errores IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    //metodo para errores tipo ResponseStatusException
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handlerResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(Map.of("error", ex.getReason()));
    }
}
