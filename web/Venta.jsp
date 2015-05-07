<%-- 
    Document   : Venta
    Created on : 30/04/2015, 01:17:25 PM
    Author     : jedisson.tapias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h1>Venta</h1>
                <form action="./VentaServlet" method="POST">
                    <table>

                        <tr>
                            <th><h2>N° Factura</h2></th>
                        <th><input type="text" name="nroFactura" value="${venta.nroFactura}"/> </th>
                        </tr>
                        <tr>
                            <th><h2>ID. Cliente</h2></th>
                        <th> <input type="text" name="idCliente" value="${venta.cliente}"/></th>
                        </tr>
                        <tr>
                            <th><h2>Matrícula Vehiculo</h2></th>
                        <th><input type="text" name="idVehiculo" value="${venta.vehiculo}"/> </th>
                        </tr>
                        <tr>
                            <th><h2>Cantidad</h2></th>
                        <th> <input type="text" name="cantidad" value="${venta.cantidad}"/></th>
                        </tr>
                        <tr>
                            <th><h2>Precio Total</h2></th>
                        <th><input type="text" name="precioTotal" value="${venta.precioTotal}"/> </th>
                        </tr>
                        <th><br></th>
                        <tr>
                            <td colspan="2">
                                <input type="submit" name="accion" value="Agregar"/>
                                <input type="submit" name="accion" value="Editar"/>
                                <input type="submit" name="accion" value="Eliminar"/>
                                <input type="submit" name="accion" value="Buscar"/>
                            </td>
                        </tr>
                    </table>
                </form>
                <br>
                <br>
                <table border="1">
                    <th>Factura</th>
                    <th>Cliente</th>
                    <th>Vehiculo</th>
                    <th>Cantidad</th>
                    <th>Precio</th>

                    <c:forEach items="${AllVentas}" var="vent">
                        <tr>
                            <td>${vent.nroFactura}</td>
                            <td>${vent.cliente}</td>
                            <td>${vent.vehiculo}</td>
                            <td>${vent.cantidad}</td>
                            <td>${vent.precioTotal}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
