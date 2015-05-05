/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.dao;

import com.udea.model.Vehiculo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author esteban.catanoe
 */
@Local
public interface VehiculoDaoLocal {

    void addVehiculo(Vehiculo vehiculo);

    void editVehiculo(Vehiculo vehiculo);

    void deleteVehiculo(String idVehiculo);

    Vehiculo getVehiculo(String idVehiculo);

    List<Vehiculo> getAllVehiculos();
    
}
