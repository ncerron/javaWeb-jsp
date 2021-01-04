<%-- 
    Document   : listaEmpleados
    Created on : 18/12/2020, 21:37:58
    Author     : nataliacc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="logica.Juego"%>
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


        <%   
            Controladora control = new Controladora();
            List<Empleado> listaEmpleadosJuego = new ArrayList<>();

            if (session.getAttribute("idJuego") != null && !session.getAttribute("idJuego").equals("")) {
                String id = session.getAttribute("idJuego").toString();
                int idj = Integer.parseInt(id);
                Juego juego = control.getJuego(idj);
                //lista de empleados en determinado juego
                listaEmpleadosJuego = juego.getListaEmpleados();

                //lista de todos los empleados
                List<Empleado> listaEmpleados = control.getListaEmpleados();
                for (Empleado emp : listaEmpleados) {
                    String nombre = emp.getNombre();
                    int idEmpl = emp.getIdEmpleado();
                    String checked = " ";

                    for (Empleado empjuego : listaEmpleadosJuego) {
                        if (emp.getIdEmpleado() == empjuego.getIdEmpleado()) {
                            checked = "checked";
                        }
                    }
        %>

        <input type="checkbox" name="listaEmpleados" class="test" value=" <%=idEmpl%>" <%=checked%>/>  
        <label for="vehicle1"> <%=idEmpl%> - <%=nombre%></label><br>

        <%}%>

        <%
        } else {
            //lista de todos los empleados
            List<Empleado> listaEmpleados = control.getListaEmpleados();
            if (listaEmpleados.size() < 1) {
        %>

        <div style="color: orange; font-size: 16px;">debe dar de alta empleados</div>
        <%} else {
            for (Empleado emp : listaEmpleados) {
                String nombre = emp.getNombre();
                int idEmpl = emp.getIdEmpleado();
        %>
        <input type="checkbox" name="listaEmpleados" class="test" value=" <%=idEmpl%>"/>  
        <label for="vehicle1"> <%=idEmpl%> - <%=nombre%></label><br>
        <%}
                }
            }%>

        <script>
                            
            $(document).ready(function(){
$('input[type="checkbox"]').click(function(){
if($('.test:checked').length >= 3){
$( ".test" ).prop( "checked", false );
        
alert("Puede ingresar hasta 2 empleados");

                    }

                });
            });
        </script>

        <%}%>

    </body>
</html>
