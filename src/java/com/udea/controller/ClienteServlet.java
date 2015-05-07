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
        String telefono = "";
        String identificacion = "";
        String mensaje = "";

        accion = request.getParameter("accion");
        if (accion.equals("Agregar") || accion.equals("Editar")) {

            texto = request.getParameter("identificacion");
            if (texto != null && !texto.equals("") && Validacion.validarNumero(texto)) {
                identificacion = texto;
            } else {
                mensaje = "El campo IDENTIFICACIÓN presenta errores, por favor verificarlo";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
                return;
            }

            texto = request.getParameter("nombres");
            if (texto != null && !texto.equals("") && Validacion.validarString(texto)) {
                nombres = texto;
            } else {
                mensaje = "El campo NOMBRES presenta errores, por favor verificarlo";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
                return;
            }

            texto = request.getParameter("apellidos");
            if (texto != null && !texto.equals("") && Validacion.validarString(texto)) {
                apellidos = texto;
            } else {
                mensaje = "El campo APELLIDOS presenta errores, por favor verificarlo";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
                return;
            }

            correo = request.getParameter("correo");

            texto = request.getParameter("telefono");
            if (Validacion.validarNumero(texto)) {
                telefono = texto;
            } else {
                mensaje = "El campo TELEFONO presenta errores, por favor verificarlo";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
                return;
            }
        }
        if (accion.equals("Eliminar") || accion.equals("Buscar")) {
            texto = request.getParameter("identificacion");
            if (texto != null && !texto.equals("") && Validacion.validarNumero(texto)) {
                identificacion = texto;
            } else {
                mensaje = "El campo IDENTIFICACIÓN presenta errores, por favor verificarlo";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
                return;
            }
        }
        Cliente cliente = new Cliente(identificacion, nombres, apellidos, correo, telefono);

        if ("Agregar".equalsIgnoreCase(accion)) {
            boolean ok = true;
            List lista = clienteDao.getAllClientes();
            for (int i = 0; i < lista.size(); i++) {
                Cliente temporal = (Cliente) lista.get(i);
                if (temporal.getIdentificacion().equals(identificacion)) {
                    ok = false;
                }
            }
            if (ok) {
                clienteDao.addCliente(cliente);
                cliente = new Cliente();
            } else {
                mensaje = "Ya existe un cliente con la IDENTIFICACIÓN ingresada, por favor verificarla.";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
            }

        } else if ("Editar".equalsIgnoreCase(accion)) {
            clienteDao.editCliente(cliente);
        } else if ("Eliminar".equalsIgnoreCase(accion)) {
            clienteDao.deleteCliente(identificacion);
        } else if ("Buscar".equalsIgnoreCase(accion)) {
            cliente = clienteDao.getCliente(identificacion);
        }

        request.setAttribute("cliente", cliente);
        request.setAttribute("AllClientes", clienteDao.getAllClientes());

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
