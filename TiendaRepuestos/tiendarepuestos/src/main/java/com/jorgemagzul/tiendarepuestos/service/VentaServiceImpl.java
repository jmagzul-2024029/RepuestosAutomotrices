package com.jorgemagzul.tiendarepuestos.service;

import com.jorgemagzul.tiendarepuestos.model.Repuesto;
import com.jorgemagzul.tiendarepuestos.model.Venta;
import com.jorgemagzul.tiendarepuestos.repository.ClienteRepository;
import com.jorgemagzul.tiendarepuestos.repository.RepuestoRepository;
import com.jorgemagzul.tiendarepuestos.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService{

    private final VentaRepository ventaRepository;
    private final RepuestoRepository repuestoRepository;
    private final ClienteRepository clienteRepository;

    public VentaServiceImpl(VentaRepository ventaRepository,RepuestoRepository repuestoRepository, ClienteRepository clienteRepository) { //constructor
        this.ventaRepository = ventaRepository;
        this.repuestoRepository = repuestoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll(); //obtiene y devuelve las ventas
    }

    @Override
    public Venta getVentaById(Integer id_venta) {
        return ventaRepository.findById(id_venta).orElse(null);//buscar ventas y si no las c¿encuentra entonces devuelve null
    }

    @Override
    public Venta saveVenta(Venta venta) {
        validarVenta(venta);
        //actualizar el stock
        actualizarStock(venta.getRepuesto().getIdRepuesto(), venta.getCantidad());
        return ventaRepository.save(venta);
    }

    @Override
    public Venta updateVenta(Integer id_venta, Venta venta) {
        Venta existinVenta = ventaRepository.findById(id_venta).orElse(null);
        if (existinVenta != null) {//si es diferente a null

            validarVenta(venta);

            //actualizar los datos con los nuevos valores
            existinVenta.setCliente(venta.getCliente());
            existinVenta.setRepuesto(venta.getRepuesto());
            existinVenta.setFecha(venta.getFecha());
            existinVenta.setCantidad(venta.getCantidad());
            existinVenta.setTotal(venta.getTotal());

            return ventaRepository.save(existinVenta);
        }
        return null;
    }

    @Override
    public void deleteVenta(Integer id_venta) {
        ventaRepository.deleteById(id_venta);
    }

    // validar si el cliente y repuesto existen, y si hay suficiente en stock
    private void validarVenta(Venta venta){
        //validar el cliente
        if (venta.getCliente() == null || venta.getCliente().getIdCliente() == null || !clienteRepository.existsById(venta.getCliente().getIdCliente())){
            throw new IllegalArgumentException("Este cliente mencionado no está registrado");
        }

        //validar el repuesto
        if (venta.getRepuesto() == null || venta.getRepuesto().getIdRepuesto() == null || !repuestoRepository.existsById(venta.getRepuesto().getIdRepuesto())){
            throw new IllegalArgumentException("Este repuesto no está registrado/no existe");
        }

        //validar el stock disponible
        Repuesto repuesto = repuestoRepository.findById(venta.getRepuesto().getIdRepuesto()).orElse(null); //buscar repuesto en la base de datos
        if (repuesto != null && repuesto.getStock() < venta.getCantidad()){
            throw new IllegalArgumentException("No hay suficiente en stock para completar la venta");
        }
    }

    //para actualizar el stock del repuesto después de cada venta
    private void actualizarStock(Integer idRepuesto, Integer cantidadVendida){
        //buscar el repuesto por su id
        Repuesto repuesto = repuestoRepository.findById(idRepuesto).orElseThrow(() -> new IllegalArgumentException("Repuesto no encontrado"));

        //verificar el stock
        if (repuesto.getStock() < cantidadVendida){
            throw new IllegalArgumentException("Stock insuficiente");
        }

        //restar la cantidad vendida al stock actual
        repuesto.setStock(repuesto.getStock()-cantidadVendida);

        //guardar el nuevo stock en la db
        repuestoRepository.save(repuesto);
    }
}
