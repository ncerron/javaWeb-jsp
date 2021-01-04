
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;

@WebServlet(name = "LoginServelt", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controladora control = new Controladora();
        boolean ok = false;
        ok = control.comprobarIngreso(request.getParameter("usuario"), request.getParameter("pass"));

        if (ok == true) {
            //verificar que exista una sesion
            HttpSession misession = request.getSession(true);         
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("pass");
            
            misession.setAttribute("usuario", usuario);
            response.sendRedirect("home.jsp");
        } else {
           request.setAttribute("errorMessage", "verifique los datos ingresados");
           request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
