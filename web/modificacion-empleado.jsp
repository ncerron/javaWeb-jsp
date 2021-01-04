
<%@page import="logica.Empleado"%>
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
            <h2 class="text-center mb-4">Modificación de Empleados</h2>
              <div style="color: orange; font-size: 18px;">${errorMessage}</div>
            <table id="taleUsuarios" class="table table-striped table-responsive-md">
                <thead class="table-light">
                    <tr>
                        <th scope="col" style="padding-left: 30px">Empleado</th>
                        <th scope="col">Dni</th>
                        <th scope="col">Usuario</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>

                <form action="EmpleadoServlet" method="get">
                    <tbody>

                        <tr>
                            <%                                Controladora control = new Controladora();
                                List<Empleado> listaEmpleados = control.getListaEmpleados();
                                for (Empleado emp : listaEmpleados) {
                            %>
                            <%
                                int id = emp.getIdEmpleado();
                                String nombre = emp.getNombre();
                                String dni = emp.getDni();
                                String usuario = emp.getUsuario().getNombre();
                            %>

                            <td id="<%=id%>" class="col-md-2" style="color:white; padding-left: 30px"><%=nombre%></td>
                            <td id="<%=id%>" class="col-md-2" style="color:white;"><%=dni%></td>
                            <td id="<%=id%>" class="col-md-2" style="color:white;"><%=usuario%></td>
                            <td class="col-md-2">
                                <a class="btn btn-primary " href="EmpleadoServlet?action=editarForm&idE=<%=id%>">Editar</a>
                                <a class="btn btn-danger" href="EmpleadoServlet?action=borrar&idE=<%=id%>">Borrar</a>                          

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
