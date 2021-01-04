
<%@page import="logica.Turnos"%>
<%@page import="logica.Juego"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logica.Cliente"%>
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
            String usuario = (String) request.getSession().getAttribute("usuario");

            if (usuario == null) {
                response.sendRedirect("index.jsp");
            } else {
                //buscar clientes, juegos y turno del juego
                Controladora control = new Controladora();

        %>

        <div class="container d-flex justify-content-center">
            <div class="card border-secondary">
                <div class="card-body ">
                    <h2>Venta de Entradas  </h2>

                    <form action="EntradaServlet"  method="post"  class="form-group container" name="AgregarForm">
                        <input type="hidden" name="action" value="agregar-primero">
                        <div style="color: orange; font-size: 18px;">${errorMessage}</div>
                        <input  class="form-control mt-3 " type="date" name="fecha">
                        <select class="form-control mt-4" name="cliente">    
                            <%     
                                List<Cliente> listaCliente = control.getListaCliente();
                                for (Cliente cliente : listaCliente) {
                                    String nombreCliente = cliente.getNombre();

                            %>
                            <option><%=nombreCliente%></option>
                            <% }%>
                            <option value="" selected="selected" >
                                Seleccione Cliente
                            </option>

                        </select>
                      
                        <select class="form-control mt-3" name="juego" >
                            <%  
                                List<Juego> listaJuegos = control.getListaJuegos();
                                for (Juego juego : listaJuegos) {
                                    String nombreJuego = juego.getNombre();
                                    int idJuego = juego.getIdJuego();
                            %>    
                           
                            <option value="<%=nombreJuego%>"><%=nombreJuego%></option>
                            <%}%>  
                            
                            <option value="" selected="selected" >
                                Seleccione juego
                            </option>
                            </select>
                            
                            <div id="btn_group">   
                                <button id="btn1" class="btn btn-primary ">Proximo paso</button>
                                <a type="button"  class="btn btn-primary " id="btn1" href="home.jsp">Cancelar</a>
                            </div>

                    </form>
                </div>
            </div>
        </div>



        <!-- Option 2: Separate Popper and Bootstrap JS -->


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>

        <%}%>
    </body>
</html>