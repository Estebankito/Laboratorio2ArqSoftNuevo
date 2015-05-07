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
@NamedQueries(@NamedQuery(name = "Vehiculo.getAll", query = "SELECT v FROM Vehiculo v"))
public class Vehiculo {
    @Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column
private String matricula;
@Column
private String modelo;
@Column
private String color;
@Column
private String cantidad;
@Column
private String precio;
@Column
private byte[] foto;

    public Vehiculo() {
    }   

    public Vehiculo(String matricula, String modelo, String color, String cantidad, String precio, byte[] foto) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
        this.cantidad = cantidad;
        this.precio = precio;
        this.foto = foto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }    
}
