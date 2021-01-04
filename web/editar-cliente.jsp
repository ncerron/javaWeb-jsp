
<%@page import="logica.Juego"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="logica.Entrada"%>
<%@page import="logica.Tarjeta"%>
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
            
%>

        <div class="container d-flex justify-content-center " >
            <div class="card border-secondary ">
                <div class="card-body " style="width:400px">
                    <h2 class="text-center">Editar Cliente</h2>
                    <form action="ClienteServlet"  method="post"  class="form-group container mt-4" name="editarForm">
                         <input type="hidden" name="action" value="editar"> 
                        <%
                                String id = session.getAttribute("idCliente").toString();
                                int idC = Integer.parseInt(id);
                                Controladora control = new Controladora();
                                Cliente cli= control.getCliente(idC);
                            
                                String nombre = cli.getNombre();
                                String dni = cli.getDni();
                                Tarjeta tarj= cli.getTarjeta();
                                int nroTarjeta = tarj.getNumero();
                                int puntos = tarj.getPuntos();   

                        %>

                        <div class="form-group row mt-4">
                            <label for="id" class="col-md-5 col-form-label">Id</label>
                            <div class="col-sm-6">         
                                <input readonly class="form-control" type="text" name="id" id="id" value="<%=id%>">
                            </div>
                        </div>

                        <div class="form-group row mt-2">
                            <label for="nom" class="col-md-5 col-form-label">Nombre</label>
                            <div class="col-sm-6">         
                                <input class="form-control" type="text" name="nombre" id="nom" value="<%=nombre%>">
                            </div>
                        </div>

                        <div class="form-group row mt-2">
                            <label for="dni" class="col-md-5 col-form-label">DNI</label>
                            <div class="col-sm-6">         
                                <input class="form-control" type="text" name="dni" id="dni" value="<%=dni%>">
                            </div>
                        </div>

                        <div class="form-group row mt-2">
                            <label for="ptos" class="col-md-5 col-form-label">Tarjeta</label>
                            <div class="col-sm-6">         
                                <input class="form-control" type="text" name="puntos" id="ptos" value="<%=nroTarjeta%>">
                            </div>
                        </div>    

                        <div class="form-group row mt-2">
                            <label for="tarjeta" class="col-md-5 col-form-label">Puntos</label>
                            <div class="col-sm-6">         
                                <input class="form-control" type="text" name="tarjeta" id="tarjeta" value="<%=puntos%>">
                            </div>
                        </div>       

                         

                            
                        <div id="btn_group">   
                            <button id="btn1" class="btn btn-primary mt-3">Editar</button>
                            <a type="button"  class="btn btn-primary mt-3" id="btn1" href="modificacion-cliente.jsp">Cerrar</a>
                        </div>  
                        
                    </form>
                </div>
            </div>
        </div>
                    
         <%}%>           

        <!-- Option 2: Separate Popper and Bootstrap JS -->

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>


    </body>
</html>
