
package servlet;


import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Empleado;
import persistencia.exceptions.NonexistentEntityException;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String idU = request.getParameter("idU");
        String accion = request.getParameter("action");
        Controladora controladora = new Controladora();

        if (accion.equalsIgnoreCase("borrar")) {
            int id = Integer.parseInt(idU);
            List<Empleado> listaEmpleados = controladora.getListaEmpleados();
            for (Empleado elem : listaEmpleados) {
                if (elem.getUsuario().getIdUsuario() == id) {
                    request.setAttribute("errorMessage", "No se puede eliminar, el Usuario esta asociado a un Empleado");
                    request.getRequestDispatcher("modificacion-usuario.jsp").forward(request, response);
                    break;
                } else {
                    try {
                        controladora.eliminarUsuario(id);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.sendRedirect("modificacion-usuario.jsp");
                }
            }

        } else if (accion.equalsIgnoreCase("editarForm")) {
            int id = Integer.parseInt(idU);
            request.getSession().setAttribute("id", id);
            response.sendRedirect("editar-usuario.jsp");

        } else if (accion.equalsIgnoreCase("agregar")) {
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("pass");
            if (!usuario.equals("") && !pass.equals("")) {
                request.getSession().setAttribute("usuario", usuario);
                controladora.crearUsuario(usuario, pass);
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("errorMessage", "Existen campos vacíos");
                request.getRequestDispatcher("form-usuario.jsp").forward(request, response);
            }

            
        } else if (accion.equalsIgnoreCase("editar")) {      
            int id = Integer.parseInt(request.getParameter("id"));
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("pass");
            try {
                controladora.editarUsuario(id, usuario, pass);
            } catch (Exception ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("id", null);
            response.sendRedirect("modificacion-usuario.jsp");

        } else {
            response.sendRedirect("home.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String usuario = request.getParameter("usuario");
//        String pass=request.getParameter("pass");
//        request.getSession().setAttribute("usuario", usuario);
//        
//        Controladora controladora= new Controladora(); 
//        //el servlet es el servidor
//        controladora.crearUsuario(usuario, pass); 
//        response.sendRedirect("home.jsp");

        doGet(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
