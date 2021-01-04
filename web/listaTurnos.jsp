<%-- 
    Document   : listaEmpleados
    Created on : 18/12/2020, 21:37:58
    Author     : nataliacc
--%>

<%@page import="logica.Juego"%>
<%@page import="logica.Turnos"%>
<%@page import="logica.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>

        <%
            HttpSession missesion = request.getSession();
            String usuario = (String) request.getSession().getAttribute("usuario");

            if (usuario == null) {
                response.sendRedirect("index.jsp");
            } else {

        %>

        <table id="taleUsuarios" class="table table-striped table-responsive-md">
            <thead class="table-light">
                <tr>
                    <th scope="col" style="padding-left: 30px">IdTurno</th>                     
                    <th scope="col">Turno</th>    
                    <th scope="col">Lugares Ocupados</th>  
                </tr>
            </thead>

            <tbody>

                <tr>
                    <%  
                        String id = session.getAttribute("idJuego").toString();
                        int idj = Integer.parseInt(id);

                        Controladora control = new Controladora();
                        Juego juego = control.getJuego(idj);

                        List<Turnos> listaTurnos = juego.getListaTurnos();
                        for (Turnos turno : listaTurnos) {
                            int idTurno = turno.getIdTurno();
                            String turnoo = turno.getTurno();
                            int ocupados = turno.getLugaresOcupados();
                    %>

                    <td id="<%=idTurno%>" class="col-md-2" style="color:white; padding-left: 30px"><%=idTurno%></td>                          
                    <td id="<%=idTurno%>" class="col-md-2" style="color:white;"><%=turnoo%></td>
                    <td id="<%=idTurno%>" class="col-md-2" style="color:white;"><%=ocupados%></td>
                </tr>     
                <%}%>
            </tbody>

        </table>                  
        <%}%>
    </body>
</html>
