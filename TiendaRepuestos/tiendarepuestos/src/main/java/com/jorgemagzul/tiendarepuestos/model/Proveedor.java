package com.jorgemagzul.tiendarepuestos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedores")
public class Proveedor {
    //mapeo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //que se genere el id porque es auto increment
    private Integer idProveedor;

    @Column(name = "nombre_empresa") //de la base de datos, si no existe entonces la crea
    private String nombreEmpresa; //declarar lo de la base de datos pero ya en camelCase

    @Column(name = "contacto") //de la base de datos, si no existe entonces la crea
    private String contacto; //declarar lo de la base de datos pero ya en camelCase

    @Column(name = "telefono_proveedor") //de la base de datos, si no existe entonces la crea
    private String telefonoProveedor; //declarar lo de la base de datos pero ya en camelCase

    @Column(name = "direccion_proveedor") //de la base de datos. (si no existe entonces la crea)
    private String direccionProveedor; //declarar lo de la base de datos pero ya en camelCase

    //setters and getters

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }
}
