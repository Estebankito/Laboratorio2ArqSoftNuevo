/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.dao;

import com.udea.model.Cliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author esteban.catanoe
 */
@Local
public interface ClienteDaoLocal {

    void addCliente(Cliente cliente);

    void editCliente(Cliente cliente);

    void deleteCliente(int idCliente);

    Cliente getCliente(int idCliente);

    List<Cliente> getAllClientes();
    
}
