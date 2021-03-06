

<%@page import="logica.Usuario"%>
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

        <div class="container d-flex justify-content-center">
            <div class="card border-secondary">
                <div class="card-body ">
                    <h2 class="text-center">Editar Usuario </h2>
                    <form action="UsuarioServlet"  method="post"  class="form-group container mt-4" name="editarForm">
                         <input type="hidden" name="action" value="editar"> 
                        <%
                                String id = session.getAttribute("id").toString();
                                int idu = Integer.parseInt(id);
                                Controladora control = new Controladora();
                                Usuario usu = control.getUsuario(idu);
                                String nombre = usu.getNombre();
                                String pass = usu.getPass();
                        %>
                        
                        <div class="form-group row mt-4">
                            <label for="id" class="col-md-5 col-form-label">Id</label>
                            <div class="col-sm-6">         
                                <input readonly class="form-control" type="text" name="id" id="pass" value="<%=id%>">
                            </div>
                        </div>

                         <div class="form-group row mt-2">
                            <label for="usu" class="col-md-5 col-form-label">Usuario</label>
                            <div class="col-sm-6">         
                                <input class="form-control" type="text" name="usuario" id="usu" value="<%=nombre%>">
                            </div>
                        </div>

                         <div class="form-group row mt-2">
                            <label for="pass" class="col-md-5 col-form-label">Contraseña</label>
                            <div class="col-sm-6">         
                                <input class="form-control" type="text" name="pass" id="pass" value="<%=pass%>">
                            </div>
                        </div>

                        <div id="btn_group">   
                            <button id="btn1" class="btn btn-primary mt-3">Editar</button>
                            <a type="button"  class="btn btn-primary mt-3" id="btn1" href="modificacion-usuario.jsp">Cerrar</a>
                        </div>
                      
                    </form>
                </div>
            </div>
        </div>

        <!-- Option 2: Separate Popper and Bootstrap JS -->

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>

<%}%>
    </body>
</html>
