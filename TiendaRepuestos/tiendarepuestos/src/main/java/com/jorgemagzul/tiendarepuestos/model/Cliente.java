package com.jorgemagzul.tiendarepuestos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    //mapeo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //que se genere el id porque es auto increment
    private Integer idCliente;

    @Column(name = "nombre_cliente") //de la base de datos, si no existe entonces la crea
    private String nombreCliente; //declarar lo de la base de datos pero ya en camelCase

    @Column(name = "direccion_cliente") //de la base de datos, si no existe entonces la crea
    private String direccionCliente; //declarar lo de la base de datos pero ya en camelCase

    @Column(name = "telefono_cliente") //de la base de datos, si no existe entonces la crea
    private String telefonoCliente; //declarar lo de la base de datos pero ya en camelCase

    @Column(name = "email_cliente") //de la base de datos. (si no existe entonces la crea)
    private String emailCliente; //declarar lo de la base de datos pero ya en camelCase

    //setters and getters
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
}
