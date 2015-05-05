/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.dao;

import com.udea.model.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author esteban.catanoe
 */
@Stateless
public class ClienteDao implements ClienteDaoLocal {
    @PersistenceContext(unitName = "Laboratorio2ArqSoftPU")
    private EntityManager em;
    
    @Override
    public void addCliente(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public void editCliente(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public void deleteCliente(String idCliente) {
        em.remove(getCliente(idCliente));
    }

    @Override
    public Cliente getCliente(String idCliente) {
        return em.find(Cliente.class, idCliente);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return em.createNamedQuery("Cliente.getAll").getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    
    

    
}
