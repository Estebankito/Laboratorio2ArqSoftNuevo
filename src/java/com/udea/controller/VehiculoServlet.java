/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.dao.VehiculoDaoLocal;
import com.udea.model.Vehiculo;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author usuario
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "VehiculoServlet",urlPatterns = {"/VehiculoServlet"})
public class VehiculoServlet extends HttpServlet {
    @EJB
    private VehiculoDaoLocal vehiculoDao;
    
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
        String matricula;
        String accion;
        String color = "";
        int cantidad = 0;
        int modelo = 0;
        Double precio = 0.0;
        InputStream inputStream = null;
        
        //Capturamos los valores de los campos en la vista
        
        accion = request.getParameter("accion");
        
        matricula = request.getParameter("matricula");

        texto = request.getParameter("modelo");
        if (Validacion.validarNumero(texto)) {
            modelo = Integer.parseInt(texto);
        }

        texto = request.getParameter("color");
        if (Validacion.validarString(texto)) {
            color = texto;            
        }
        
        texto = request.getParameter("cantidad");
        if (Validacion.validarNumero(texto)) {
            cantidad = Integer.parseInt(texto);
        }
        
        texto = request.getParameter("precio");
        if (Validacion.validarPrecio(texto)) {
            precio = Double.parseDouble(texto);
        }
       

        // Obtenemos el archivo en partes a través de una petición Multipart
        /*Part filePart = request.getPart("foto");

        if (filePart != null) {

            // Información para Debug
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // Obtener el InputStream del Archivo Subido
            inputStream = filePart.getInputStream();
        
        }    */
            Vehiculo vehiculo = new Vehiculo(matricula, modelo, color, cantidad, precio, (Blob) inputStream);
           
        
        if ("Añadir".equalsIgnoreCase(accion)) {
            vehiculoDao.addVehiculo(vehiculo);
        }else if ("Editar".equalsIgnoreCase(accion)) {
            vehiculoDao.editVehiculo(vehiculo);
        }else if ("Eliminar".equalsIgnoreCase(accion)) {
            vehiculoDao.deleteVehiculo(matricula);
        }else if ("Buscar".equalsIgnoreCase(accion)) {
            vehiculoDao.getVehiculo(matricula);
        }
        
        request.setAttribute("vehiculo", vehiculo);
        request.setAttribute("AllVehiculos", vehiculoDao.getAllVehiculos());
        
        // CAMBIAR PARAMETRO POR jsp CORRESPONDIENTE
        request.getRequestDispatcher("Vehiculo.jsp").forward(request, response);
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
