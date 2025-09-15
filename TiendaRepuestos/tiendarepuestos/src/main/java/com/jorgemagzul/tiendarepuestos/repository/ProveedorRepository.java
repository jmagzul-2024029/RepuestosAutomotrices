package com.jorgemagzul.tiendarepuestos.repository;

import com.jorgemagzul.tiendarepuestos.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    //Validaciones para agregar registros:

    //verificar si existe un registro con el mismo nombre, contacto y teléfono
    boolean existsByNombreEmpresaAndContactoAndTelefonoProveedor(String nombreEmpresa, String contacto, String telefonoProveedor);

    // Verificar si ya existe un proveedor con el mismo teléfono
    boolean existsByTelefonoProveedor(String telefonoProveedor);

    // ver si existe algún registro con el mismo nombre y contacto
    boolean existsByNombreEmpresaAndContacto(String nombreEmpresa, String contacto);

    // Validaciones para actualizar:

    //ver si los nuewvos datos no son iguales a unos que ya existen(excepto si son de este mismo proveedor)
    boolean existsByNombreEmpresaAndContactoAndTelefonoProveedorAndIdProveedorNot(String nombreEmpresa, String Contacto, String telefonoProveedor, Integer idProveedor);

    //ver si el nombre de la empresa y el contacto ya existen
    boolean existsByNombreEmpresaAndContactoAndIdProveedorNot(String nombreEmpresa, String Contacto, Integer idProveedor);

    //ver si el teléfono lo tiene otro proveedor
    boolean existsByTelefonoProveedorAndIdProveedorNot(String telefonoProveedor, Integer idProveedor);
}
