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
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        String texto;
        String matricula;
        String accion;
        String color = "";
        String cantidad = "";
        String modelo = "";
        String precio = "";        
        Part filePart;
        InputStream inputStream = null;
        byte[] foto = null;
        
        //Capturamos los valores de los campos en la vista
        
        accion = request.getParameter("accion");
        
        matricula = request.getParameter("matricula");

        texto = request.getParameter("modelo");
        if (Validacion.validarNumero(texto)) {
            modelo = texto;
        }

        texto = request.getParameter("color");
        if (Validacion.validarString(texto)) {
            color = texto;            
        }
        
        texto = request.getParameter("cantidad");
        if (Validacion.validarNumero(texto)) {
            cantidad = texto;
        }
        
        texto = request.getParameter("precio");
        if (Validacion.validarPrecio(texto)) {
           // precio = Double.parseDouble(texto);
            precio = texto;
        }
       
        
        filePart = request.getPart("foto");

        if (filePart != null) {

            // Información para Debug
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // Obtener el InputStream del Archivo Subido
            inputStream = filePart.getInputStream();
            foto = new byte[(int) filePart.getSize()];
            inputStream.read(foto);
            inputStream.close();
        
        }  
            Vehiculo vehiculo = new Vehiculo(matricula, modelo, color, cantidad, precio, foto);
           
        
        if ("Agregar".equalsIgnoreCase(accion)) {
            boolean ok=true;
            List l = vehiculoDao.getAllVehiculos();
            for(int i=0;i<l.size();i++){
                Vehiculo v=(Vehiculo)l.get(i);
                if(v.getMatricula().equals(matricula)){
                    ok=false;
                }
            }
            if(ok){
                vehiculoDao.addVehiculo(vehiculo);
                vehiculo = new Vehiculo();
            }
            else{
                //Mensaje de alerta----intento fallido de duplicación de primary key
            }
            
        }else if ("Editar".equalsIgnoreCase(accion)) {
            vehiculoDao.editVehiculo(vehiculo);
        }else if ("Eliminar".equalsIgnoreCase(accion)) {
            vehiculoDao.deleteVehiculo(matricula);
        }else if ("Buscar".equalsIgnoreCase(accion)) {
            vehiculo = vehiculoDao.getVehiculo(matricula);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(VehiculoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(VehiculoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
