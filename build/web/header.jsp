
<%@page import="logica.Controladora"%>
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">

    <div class="container-fluid">  
        <a class="navbar-brand" href="index.jsp">PD</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <!-- verifica que se haya iniciado la sessions -->
                <%
                    HttpSession missesion = request.getSession();
                    String usuario = (String) request.getSession().getAttribute("usuario");

                    if (usuario == null) {
                        response.sendRedirect("index.jsp");
                    } else {
                %>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="home.jsp">Home</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Usuarios
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="form-usuario.jsp">Alta Usuario</a></li>
                        <li><a class="dropdown-item" href="modificacion-usuario.jsp">Modificación Usuarios</a></li>                    
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Clientes
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="form-cliente.jsp">Alta Cliente</a></li>
                        <li><a class="dropdown-item" href="modificacion-cliente.jsp">Modificación Cliente</a></li>                    
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Juegos
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="form-juego.jsp">Alta Juego</a></li>
                        <li><a class="dropdown-item" href="modificacion-juego.jsp">Modificación Juegos</a></li>                    
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Empleados
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="form-empleado.jsp">Alta Empleado</a></li>
                        <li><a class="dropdown-item" href="modificacion-empleado.jsp">Modificación Empleado</a></li>  
                        <li><a class="dropdown-item" href="buscar.jsp">Listado de Empleados x Juego</a></li>   
                    </ul>

                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Entradas
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="form-entrada.jsp">Venta Entrada</a></li>
                        <li><a class="dropdown-item" href="modificacion-entrada.jsp">Modificación Entrada</a></li>  
                    </ul>
                </li>              
                <%
                    }
                %>              
            </ul>
            <%
                if (usuario != null) {%>
            <ul class="navbar-nav  mx-auto"><li class="nav-item"><span>Hola <%=usuario%>!</span> </li></ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="logout.jsp">Logout</a></li>
            </ul>         
            <%}%>
        </div>

    </div>

</nav>


