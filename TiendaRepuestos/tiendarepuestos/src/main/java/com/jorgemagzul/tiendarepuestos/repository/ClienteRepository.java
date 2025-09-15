package com.jorgemagzul.tiendarepuestos.repository;

import com.jorgemagzul.tiendarepuestos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //Validaciones para agregar registros:

    //verificar si existe un registro con todos los datos
    boolean existsByEmailClienteAndNombreClienteAndTelefonoCliente(String emailCliente, String nombreCliente, String telefonoCliente);

    // Verificar si ya existe un correo igual
    boolean existsByEmailCliente(String emailCliente);

    // ver si existe algún registro con el mismo nombre y telefono
    boolean existsByNombreClienteAndTelefonoCliente(String nombreCliente, String telefonoCliente);

    // Validaciones para actualizar:

    //ver si el nombre y apellido nuevos son iguales a uno ya existente en los registros
    boolean existsByNombreClienteAndTelefonoClienteAndIdClienteNot(String nombreCliente, String telefonoCliente, Integer idCliente);

    //ver su el nombre, apellido y email nuevos son iguales a unos registros ya existentes
    boolean existsByEmailClienteAndNombreClienteAndTelefonoClienteAndIdClienteNot(String emailCliente, String nombreCliente, String telefonoCliente, Integer idCliente);
}