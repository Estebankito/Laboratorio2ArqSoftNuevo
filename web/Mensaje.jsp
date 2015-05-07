<%-- 
    Document   : Mensaje
    Created on : 6/05/2015, 05:02:30 PM
    Author     : jorgel.diaz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lab 2</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="resources/divtitulo.css" type="text/css"/>
        <link rel="stylesheet" href="resources/div1.css" type="text/css"/>
        <link rel="stylesheet" href="resources/div2.css" type="text/css">
        <link rel="stylesheet" href="resources/div2alterno.css" type="text/css"/>



    </head>
    <body background="resources/concesionario-autos.jpg">
        <div class="titulo" align="center" >
            Concesionario CRUD
        </div>

        <div align="center" style= "margin-left: 15%  ;margin-right:  15%  ;margin-top: 5%  ;">
            <div class="contenedor1">
                <div style="margin-top: 50%;">
                    <br>
                    <form action="./ClienteServlet" method="POST">
                        <input type="submit" name="accion" value="Cliente"/>
                    </form>
                    <br>
                    <form action="./VehiculoServlet" method="POST" enctype="multipart/form-data">
                        <input type="submit" name="accion" value="Vehiculo"/>
                    </form>
                    <br>
                    <form action="./VentaServlet" method="POST">
                        <input type="submit" name="accion" value="Venta"/>
                    </form>
                </div> 
            </div>
            <div class="contenedor2alterno">
                <center>
                    <h2><%=request.getAttribute("Message")%> </h2>
                </center>

                <tr>                        
                    <td colspan="2">
                        <br>
                <center> <a class="btn-primary" HREF="index.html"><h2><b> INICIO </b></h2></a></center>
                </td>
                </tr>
            </div> 
        </div>
    </body>
</html>
