<%-- 
    Document   : modificacion-entrada
    Created on : 27/12/2020, 18:34:14
    Author     : nataliacc
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="logica.Entrada"%>
<%@page import="java.util.List"%>
<%@page import="logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <link rel="stylesheet" href="css/newcss.css">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Monoton&display=swap" rel="stylesheet">

    </head>
    <body id="fondo">
        <jsp:include page="header.jsp"/>

        <%
            HttpSession missesion = request.getSession();
            String usua = (String) request.getSession().getAttribute("usuario");

            if (usua == null) {
                response.sendRedirect("index.jsp");
            } else {

        %>

        <div class="container justify-content-center">
            <h2 class="text-center mb-4">Modificación de Entradas</h2>
            <table id="taleUsuarios" class="table table-striped table-responsive-md">
                <thead class="table-light">
                    <tr>
                        <th scope="col" style="padding-left: 30px">Entrada</th>
                        <th scope="col">Cliente</th>
                        <th scope="col">Dia</th>
                        <th scope="col">Turno</th>
                        <th scope="col">Juego</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>

                <form action="EmpleadoServlet" method="get">
                    <tbody>
                        <tr>
                            <%  
                                Controladora control = new Controladora();
                                List<Entrada> listaEntradas = control.getListaEntradas();
                                for (Entrada entrada : listaEntradas) {
                                    int idEntrada=entrada.getIdEntrada();
                                    String cliente=entrada.getCliente().getNombre();
                                  
                                    Date fecha=entrada.getFecha();
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                    String dia = sdf.format(fecha);
                                    
                                    String turno = entrada.getTurno().getTurno();
                                    String juego = entrada.getJuego().getNombre();
                                    
                            %>
                           

                            <td class="col-md-2" style="color:white; padding-left: 30px"><%=idEntrada%></td>
                            <td class="col-md-2" style="color:white;"><%=cliente%></td>
                            <td  class="col-md-2" style="color:white;"><%=dia%></td>
                            <td  class="col-md-2" style="color:white;"><%=turno%></td>
                            <td  class="col-md-2" style="color:white;"><%=juego%></td>
                            <td class="col-md-2">
                                <a class="btn btn-primary " href="EntradaServlet?action=editarForm&idE=<%=idEntrada%>">Editar</a>
                                <a class="btn btn-danger" href="EntradaServlet?action=borrar&idE=<%=idEntrada%>">Borrar</a>                          

                            </td>                     
                        </tr>     
                        <%}%>
                    </tbody>
                </form>
            </table>

            <!-- Option 2: Separate Popper and Bootstrap JS -->

            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
        </div>
        <%}%>
    </body>
</html>
