/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.dao.VentaDaoLocal;
import com.udea.model.Venta;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
@WebServlet(name = "VentaServlet", urlPatterns = {"/VentaServlet"})
public class VentaServlet extends HttpServlet {

    @EJB
    private VentaDaoLocal ventaDao;

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
        String accion;
        String texto;
        String nroFactura = "";
        String idCliente = "";
        String idVehiculo = "";
        String cantidad = "";
        String precioTotal = "";
        String mensaje = "";

        accion = request.getParameter("accion");

        if (accion.equals("Agregar") || accion.equals("Editar")) {

            texto = request.getParameter("nroFactura");
            if (texto != null && !texto.equals("")) {
                nroFactura = texto;
            } else {
                mensaje = "El campo N° FACTURA presenta errores, por favor verificarlo";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
            }

            texto = request.getParameter("idCliente");
            if (texto != null && !texto.equals("") && Validacion.validarNumero(texto)) {
                // idCliente = Integer.parseInt(texto);    
                idCliente = texto;
            } else {
                mensaje = "El campo ID. CLIENTE presenta errores, por favor verificarlo";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
            }

            texto = request.getParameter("idVehiculo");
            if (texto != null && !texto.equals("")) {
                idVehiculo = texto;
            } else {
                mensaje = "El campo MATRICULA VEHICULO presenta errores, por favor verificarlo";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
            }

            texto = request.getParameter("cantidad");
            if (texto != null && !texto.equals("") && Validacion.validarNumero(texto)) {
                cantidad = texto;
            } else {
                mensaje = "El campo CANTIDAD presenta errores, por favor verificarlo";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
            }

            texto = request.getParameter("precioTotal");
            if (texto != null && !texto.equals("") && Validacion.validarPrecio(texto)) {
                precioTotal = texto;
            } else {
                mensaje = "El campo PRECIO TOTAL presenta errores, por favor verificarlo";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
            }
        }
        if (accion.equals("Eliminar") || accion.equals("Buscar")) {
            texto = request.getParameter("nroFactura");
            if (texto != null && !texto.equals("")) {
                nroFactura = texto;
            } else {
                mensaje = "El campo N° FACTURA presenta errores, por favor verificarlo";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
            }
        }
        Venta venta = new Venta(nroFactura, idCliente, idVehiculo, cantidad, precioTotal);

        if ("Agregar".equalsIgnoreCase(accion)) {
            boolean ok = true;
            List l = ventaDao.getAllVentas();
            for (int i = 0; i < l.size(); i++) {
                Venta temporal = (Venta) l.get(i);
                if (temporal.getNroFactura().equals(nroFactura)) {
                    ok = false;
                }
            }
            if (ok) {
                ventaDao.addVenta(venta);
                venta = new Venta();
            } else {
                mensaje = "Ya existe una venta con el N° FACTURA ingresado, por favor verificarlo.";
                request.setAttribute("Message", mensaje);
                request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
            }
        } else if ("Editar".equalsIgnoreCase(accion)) {
            ventaDao.editVenta(venta);
        } else if ("Eliminar".equalsIgnoreCase(accion)) {
            ventaDao.deleteVenta(nroFactura);
        } else if ("Buscar".equalsIgnoreCase(accion)) {
            venta = ventaDao.getVenta(nroFactura);
        }

        request.setAttribute("venta", venta);
        request.setAttribute("AllVentas", ventaDao.getAllVentas());

        request.getRequestDispatcher("Venta.jsp").forward(request, response);
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
