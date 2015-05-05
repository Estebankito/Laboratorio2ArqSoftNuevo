/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author esteban.catanoe
 */
@Entity
@Table
@NamedQueries(@NamedQuery(name = "Venta.getAll", query="SELECT ve FROM Venta ve"))
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private String nroFactura;
    @Column
    private int cliente;
    @Column
    private String vehiculo;
    @Column
    private int cantidad;
    @Column
    private Double precioTotal;

    public Venta() {
    }

    public Venta(String nroFactura, int idCliente, String idVehiculo, int cantidad, Double precioTotal) {
        this.nroFactura = nroFactura;
        this.cliente = idCliente;
        this.vehiculo = idVehiculo;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int idCliente) {
        this.cliente = idCliente;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    } 
}
