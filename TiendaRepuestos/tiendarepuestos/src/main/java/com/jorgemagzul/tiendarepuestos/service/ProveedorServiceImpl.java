package com.jorgemagzul.tiendarepuestos.service;


import com.jorgemagzul.tiendarepuestos.model.Proveedor;
import com.jorgemagzul.tiendarepuestos.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) { //constructor
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor getProveedorById(Integer id_proveedor) {
        return proveedorRepository.findById(id_proveedor).orElse(null);
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) {
        //validar si todos los datos ya existen
        if (proveedorRepository.existsByNombreEmpresaAndContactoAndTelefonoProveedor(
                proveedor.getNombreEmpresa(), proveedor.getContacto(), proveedor.getTelefonoProveedor())) {
            throw new IllegalArgumentException("Ya existe un proveedor con esos datos.");
        }
        //validar si existe un proveedor con el mismo numero de telefono
        if (proveedorRepository.existsByTelefonoProveedor(proveedor.getTelefonoProveedor())) {
            throw new IllegalArgumentException("Este teléfono ya está registrado con otro proveedor.");
        }
        //validar nombre y contacto
        if (proveedorRepository.existsByNombreEmpresaAndContacto(proveedor.getNombreEmpresa(), proveedor.getContacto())) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre y apellido.");
        }

        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(Integer id_proveedor, Proveedor proveedor) {
        Proveedor existinProveedor = proveedorRepository.findById(id_proveedor).orElse(null);
        if (existinProveedor != null) {//si es diferente a null

            //validar si los datos ya existen en otro proveedor
            if ((!existinProveedor.getNombreEmpresa().equals(proveedor.getNombreEmpresa()) || !existinProveedor.getContacto().equals(proveedor.getContacto()) || !existinProveedor.getTelefonoProveedor().equals(proveedor.getTelefonoProveedor()))
                    && proveedorRepository.existsByNombreEmpresaAndContactoAndTelefonoProveedorAndIdProveedorNot(proveedor.getNombreEmpresa(), proveedor.getContacto(), proveedor.getTelefonoProveedor(), existinProveedor.getIdProveedor())) {
                throw new IllegalArgumentException("Estos datos ya están registrados");
            }

            //validar si el nombre y contacto están duplicados
            if ((!existinProveedor.getNombreEmpresa().equals(proveedor.getNombreEmpresa()) || !existinProveedor.getContacto().equals(proveedor.getContacto()))
                    && proveedorRepository.existsByNombreEmpresaAndContactoAndIdProveedorNot(proveedor.getNombreEmpresa(), proveedor.getContacto(), proveedor.getIdProveedor())) {
                throw new IllegalArgumentException("Este nombre y contacto ya existen, verifica de nuevo");
            }

            //ver si el telefono está duplicado
            if (!existinProveedor.getTelefonoProveedor().equals(proveedor.getTelefonoProveedor()) && proveedorRepository.existsByTelefonoProveedorAndIdProveedorNot(proveedor.getTelefonoProveedor(), id_proveedor)){
                throw new IllegalArgumentException("Este teléfono está registrado con otro proveedor");
            }
            //actualizar campos
            existinProveedor.setNombreEmpresa(proveedor.getNombreEmpresa());
            existinProveedor.setContacto(proveedor.getContacto());
            existinProveedor.setTelefonoProveedor(proveedor.getTelefonoProveedor());
            existinProveedor.setDireccionProveedor(proveedor.getDireccionProveedor());

            return proveedorRepository.save(existinProveedor);
        }
        return null;
    }

    @Override
    public void deleteProveedor(Integer id_proveedor) {
        proveedorRepository.deleteById(id_proveedor);
    }
}
