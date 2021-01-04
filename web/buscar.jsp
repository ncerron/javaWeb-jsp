<%-- 
    Document   : buscar
    Created on : 20/12/2020, 20:32:07
    Author     : nataliacc
--%>

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
            <h2 class="text-center mb-4">Listado de Empleados</h2>

            <form action="EmpleadoServlet" method="get">

                <div class="input-group mb-3 "  style="width:300px;">
                    <input class="form-control" type="text" name="nombre" placeholder="Ingrese nombre del juego">
                    <input type="hidden" name="action" value="buscar"> 
                    <div class="input-group-append">
                        <button id="btn1" class="btn btn-primary ">Buscar</button>
                    </div>
                </div>

            </form>

            <!-- Option 2: Separate Popper and Bootstrap JS -->

            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
        </div>
       <%}%>
    </body>
</html>










