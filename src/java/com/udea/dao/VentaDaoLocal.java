/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.dao;

import com.udea.model.Venta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author esteban.catanoe
 */
@Local
public interface VentaDaoLocal {

    void addVenta(Venta venta);

    void editVenta(Venta venta);

    void deleteVenta(String idVenta);

    Venta getVenta(String idVenta);

    List<Venta> getAllStudents();

    List<Venta> getAllVentas();
    
}
