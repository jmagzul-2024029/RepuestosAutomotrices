package com.jorgemagzul.tiendarepuestos.repository;

import com.jorgemagzul.tiendarepuestos.model.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepuestoRepository extends JpaRepository<Repuesto, Integer> {

    //validaciones para agregar

    //evitar que se duplique el mismo nombre, marca y modelo
    boolean existsByNombreRepuestoAndMarcaAndModelo(String nombreRepuesto, String marca, String modelo);

    // verificar si ya existe un repuesto con el mismo nombre y marca
    boolean existsByNombreRepuestoAndMarca(String nombreRepuesto, String marca);

    // Validaciones para actualizar:

    //ver si ya ecisten los datos en otro repuesto
    boolean existsByNombreRepuestoAndMarcaAndModeloAndIdRepuestoNot(String nombreRepuesto, String marca, String modelo, Integer idRepuesto);

    //ver si el nombre o marca ya existe
    boolean existsByNombreRepuestoAndMarcaAndIdRepuestoNot(String nombreRepuesto, String marca, Integer idRepuesto);
}
