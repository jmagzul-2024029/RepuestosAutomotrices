package com.jorgemagzul.tiendarepuestos.service;

import com.jorgemagzul.tiendarepuestos.model.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Cliente getClienteById(Integer id_cliente);
    Cliente saveCliente(Cliente cliente);
    Cliente updateCliente(Integer id_cliente, Cliente cliente);
    void deleteCliente(Integer id_cliente); //se crea un metodo
}
