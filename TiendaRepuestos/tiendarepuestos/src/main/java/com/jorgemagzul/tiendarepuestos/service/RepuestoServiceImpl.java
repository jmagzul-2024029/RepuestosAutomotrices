package com.jorgemagzul.tiendarepuestos.service;

import com.jorgemagzul.tiendarepuestos.model.Repuesto;
import com.jorgemagzul.tiendarepuestos.repository.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepuestoServiceImpl implements RepuestoService{

    private final RepuestoRepository repuestoRepository;

    public RepuestoServiceImpl(RepuestoRepository repuestoRepository) { //constructor
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public List<Repuesto> getAllRepuestos() {
        return repuestoRepository.findAll();
    }

    @Override
    public Repuesto getRepuestoById(Integer id_repuesto) {
        return repuestoRepository.findById(id_repuesto).orElse(null);
    }

    @Override
    public Repuesto saveRepuesto(Repuesto repuesto) {
        //validar si ya existe el nombre, la marca y el modelo
        if (repuestoRepository.existsByNombreRepuestoAndMarcaAndModelo(
                repuesto.getNombreRepuesto(), repuesto.getMarca(), repuesto.getModelo())) {
            throw new IllegalArgumentException("Ya existe un repuesto con esos datos.");
        }

        //validar si existe el nombre y la marca
        if (repuestoRepository.existsByNombreRepuestoAndMarca(repuesto.getNombreRepuesto(), repuesto.getMarca())) {
            throw new IllegalArgumentException("Ya existe un repuesto con el mismo nombre y marca");
        }
        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto updateRepuesto(Integer id_repuesto, Repuesto repuesto) {
        Repuesto existinRepuesto = repuestoRepository.findById(id_repuesto).orElse(null);
        if (existinRepuesto != null) {//si es diferente a null

            //validar si los datos ya existen en otro repuesto
            if ((!existinRepuesto.getNombreRepuesto().equals(repuesto.getNombreRepuesto())
                    || !existinRepuesto.getMarca().equals(repuesto.getMarca())
                    || !existinRepuesto.getModelo().equals(repuesto.getModelo()))
                    && repuestoRepository.existsByNombreRepuestoAndMarcaAndModeloAndIdRepuestoNot(
                            repuesto.getNombreRepuesto(), repuesto.getMarca(), repuesto.getModelo(), id_repuesto)) {
                throw new IllegalArgumentException("Estos datos ya están registrados con otro repuesto");
            }

            //validar si el nombre y marca ya están duplicados
            if ((!existinRepuesto.getNombreRepuesto().equals(repuesto.getNombreRepuesto())
                    || !existinRepuesto.getMarca().equals(repuesto.getMarca()))
                    && repuestoRepository.existsByNombreRepuestoAndMarcaAndIdRepuestoNot(repuesto.getNombreRepuesto(), repuesto.getMarca(), id_repuesto)) {
                throw new IllegalArgumentException("ya existe un repuesto con el mismo nombre y marca");
            }

            //actualizar campos
            existinRepuesto.setNombreRepuesto(repuesto.getNombreRepuesto());
            existinRepuesto.setMarca(repuesto.getMarca());
            existinRepuesto.setModelo(repuesto.getModelo());
            existinRepuesto.setPrecio(repuesto.getPrecio());
            existinRepuesto.setStock(repuesto.getStock());

            return repuestoRepository.save(existinRepuesto);
        }
        return null;
    }

    @Override
    public void deleteRepuesto(Integer id_repuesto) {
        repuestoRepository.deleteById(id_repuesto);
    }
}
