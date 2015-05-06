/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.dao.ClienteDaoLocal;
import com.udea.model.Cliente;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

    @EJB
    private ClienteDaoLocal clienteDao;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String texto;
        String nombres = "";
        String apellidos = "";
        String correo = "";
        String accion = "";
        String telefono ="";
        String identificacion="" ;

        accion = request.getParameter("accion");
                
        texto = request.getParameter("identificacion");
        if (Validacion.validarNumero(texto) == true) {
            identificacion = texto;
        }

        texto = request.getParameter("nombres");
        if (Validacion.validarString(texto) == true) {
            nombres = texto;
        }

        texto = request.getParameter("apellidos");
        if (Validacion.validarString(texto) == true) {
            apellidos = texto;
        }

        correo = request.getParameter("correo");

        texto = request.getParameter("telefono");
        if (Validacion.validarNumero(texto) == true) {
            telefono = texto;
        }

        Cliente cliente = new Cliente(identificacion, nombres, apellidos, correo, telefono);

        if ("Agregar".equalsIgnoreCase(accion)) {
            boolean ok=true;
            List lista = clienteDao.getAllClientes();
            for(int i=0;i < lista.size();i++){
                Cliente temporal= (Cliente)lista.get(i);
                if(temporal.getIdentificacion().equals(identificacion)){
                    ok=false;
                }
            }
            if(ok){
                clienteDao.addCliente(cliente);
                cliente = new Cliente();
            }
            else{
                //Falta enviar mensaje que el id ya existe 
            }
            
        } else if ("Editar".equalsIgnoreCase(accion)) {
            clienteDao.editCliente(cliente);
        } else if ("Eliminar".equalsIgnoreCase(accion)) {
            clienteDao.deleteCliente(identificacion);
        } else if ("Buscar".equalsIgnoreCase(accion)) {
            cliente = clienteDao.getCliente(identificacion);
        } else {
            //mostrar todo
        }
        

        request.setAttribute("cliente", cliente);
        request.setAttribute("AllClientes", clienteDao.getAllClientes());

        // CAMBIAR PARAMETRO POR jsp CORRESPONDIENTE
        request.getRequestDispatcher("Cliente.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
