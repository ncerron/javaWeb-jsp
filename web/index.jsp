
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
         <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Monoton&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="css/newcss.css">

        <title>Parque de Diversiones</title>

    </head>
    <body id="fondo">

        <jsp:include page="header.jsp"/>
        
    
        <div class="container d-flex justify-content-center">

            <div  class="mt-5 form-group ">
                 
                    <form action="LoginServlet"  method="post"  class="form-group">
                        <h3 class="text-center">Iniciar sesión</h3>
                        <div style="color: orange; font-size: 18px; width: 300px;">${errorMessage}</div>
                        <input class="form-control mt-4 " type="text" name="usuario" placeholder="Usuario">
                        <input  class="form-control mt-3 " type="password" name="pass" placeholder="Contraseña">

                        <button id="btn" class="btn btn-primary mt-3 btn-block">Login</button>
                        <p class="text-center" style="font-size: 15px; letter-spacing: 1px; word-spacing: 2px;">¿Primera vez? <a href="form-usuario.jsp" style="color:#ffc107; font-size: 15px; font-weight: 800;">REGISTRARSE</a></p> 

                    </form>
            </div>

        </div>

        <!-- Option 2: Separate Popper and Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>

        
    </body>
   
</html>
