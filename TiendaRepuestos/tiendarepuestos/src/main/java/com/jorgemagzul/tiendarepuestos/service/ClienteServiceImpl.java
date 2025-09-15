package com.jorgemagzul.tiendarepuestos.service;

import com.jorgemagzul.tiendarepuestos.model.Cliente;
import com.jorgemagzul.tiendarepuestos.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) { //constructor
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Integer id_cliente) {
        return clienteRepository.findById(id_cliente).orElse(null);
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        //validar si todos los datos ya existen
        if (clienteRepository.existsByEmailClienteAndNombreClienteAndTelefonoCliente(cliente.getEmailCliente(), cliente.getNombreCliente(), cliente.getTelefonoCliente())) {
            throw new IllegalArgumentException("Ya existe un cliente con esos datos, no se puede ingresar uno igual.");
        }
        //validar la duplicación del correo
        if (clienteRepository.existsByEmailCliente(cliente.getEmailCliente())) {
            throw new IllegalArgumentException("Este correo ya existe, no pueden haber dos  iguales.");
        }
        //validar nombre y telefono
        if (clienteRepository.existsByNombreClienteAndTelefonoCliente(cliente.getNombreCliente(), cliente.getTelefonoCliente())) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre y apellido.");
        }

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Integer id_cliente, Cliente cliente) {
        Cliente existinCliente = clienteRepository.findById(id_cliente).orElse(null);
        if (existinCliente != null) {//si es diferente a null

            if ((!existinCliente.getEmailCliente().equals(cliente.getEmailCliente()) || !existinCliente.getNombreCliente().equals(cliente.getNombreCliente()) || !existinCliente.getTelefonoCliente().equals(cliente.getTelefonoCliente()))
                    && clienteRepository.existsByEmailClienteAndNombreClienteAndTelefonoClienteAndIdClienteNot(cliente.getEmailCliente(), cliente.getNombreCliente(), cliente.getTelefonoCliente(), existinCliente.getIdCliente())) {
                throw new IllegalArgumentException("Estos datos ya están registrados, utilice unos nuevos");
            }

            //validar si el nombre y telefono están duplicados
            if ((!existinCliente.getNombreCliente().equals(cliente.getNombreCliente()) || !existinCliente.getTelefonoCliente().equals(cliente.getTelefonoCliente()))
                    && clienteRepository.existsByNombreClienteAndTelefonoClienteAndIdClienteNot(cliente.getNombreCliente(), cliente.getTelefonoCliente(), cliente.getIdCliente())) {
                throw new IllegalArgumentException("Este nombre y teléfono ya existen, intenta con datos distintos");
            }

            //actualizar campos
            existinCliente.setEmailCliente(cliente.getEmailCliente());
            existinCliente.setNombreCliente(cliente.getNombreCliente());//setear
            existinCliente.setDireccionCliente(cliente.getDireccionCliente());
            return clienteRepository.save(existinCliente);
        }
        return null;
    }

    @Override
    public void deleteCliente(Integer id_cliente) {
        clienteRepository.deleteById(id_cliente);
    }

}