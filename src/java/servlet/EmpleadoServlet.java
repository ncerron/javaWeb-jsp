
package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import logica.Juego;
import logica.Usuario;
import persistencia.exceptions.NonexistentEntityException;


@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/EmpleadoServlet"})
public class EmpleadoServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idE= request.getParameter("idE");
        String accion = request.getParameter("action");
        Controladora controladora = new Controladora();

        if (accion.equalsIgnoreCase("borrar")) {

            int id = Integer.parseInt(idE);
            List<Juego> listaJuegos = controladora.getListaJuegos();

            search:
            {
                for (Juego elem : listaJuegos) {
                    List<Empleado> listaEmpleados = elem.getListaEmpleados();
                    for (Empleado emp : listaEmpleados) {
                        if (emp.getIdEmpleado() == id) {
                            request.setAttribute("errorMessage", "No se puede eliminar, el Empleado esta asociado a un Juego ");
                            request.getRequestDispatcher("modificacion-empleado.jsp").forward(request, response);
                            break search;
                        } else {

                            try {
                                controladora.eliminarEmpleado(id);
                            } catch (NonexistentEntityException ex) {
                                Logger.getLogger(EmpleadoServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            response.sendRedirect("modificacion-empleado.jsp");
                        }
                    }

                }
            }

        } else if (accion.equalsIgnoreCase("editarForm")) {
            int id = Integer.parseInt(idE);
            request.getSession().setAttribute("idEmpleado", id);
            response.sendRedirect("editar-empleado.jsp");

        } else if (accion.equalsIgnoreCase("agregar")) {
            String nombre = request.getParameter("nombre");
            String dni = request.getParameter("dni");
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("pass");
            
            if (!nombre.equals("") && !dni.equals("") && !usuario.equals("") && !pass.equals("")) {
                Usuario user = controladora.crearUsuarioE(usuario, pass);
                controladora.crearEmpleado(nombre, dni, user);
                response.sendRedirect("modificacion-empleado.jsp");
            } else {
                request.setAttribute("errorMessage", "Existen campos vacíos");
                request.getRequestDispatcher("form-empleado.jsp").forward(request, response);
            }

        } else if (accion.equalsIgnoreCase("editar")) {   
            ////leo del formulario
            int id = Integer.parseInt(request.getParameter("id"));
            
            //dni, nombre, usuario, pass
            String nombre = request.getParameter("nombre");
            String dni = request.getParameter("dni");
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("pass");
            Usuario user = controladora.crearUsuarioE(usuario, pass);
            
            
            try {
                controladora.editarEmpleado(id, nombre, dni, user);
            } catch (Exception ex) {
                Logger.getLogger(JuegoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("idJuego", null);
            response.sendRedirect("modificacion-empleado.jsp");

        } else if (accion.equalsIgnoreCase("buscar")) {
            
            String nombre = request.getParameter("nombre");
            List<Juego> listaJuegos = controladora.getListaJuegos();
            List<Empleado> listaEmpleadoJuego = new ArrayList<>();
            int juegoE=0;
            //busco juego
            for (Juego juego : listaJuegos) {
                if (juego.getNombre().equals(nombre)) {
                    listaEmpleadoJuego = juego.getListaEmpleados();
                    juegoE = juego.getIdJuego();
                }else{
                   request.getSession().setAttribute("idJuegoE", "");
                }
            }
            
            request.getSession().setAttribute("idJuegoE", juegoE);
            response.sendRedirect("listadoEmpleadosxJuego.jsp");

        } else {
            response.sendRedirect("home.jsp");
        }      
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
