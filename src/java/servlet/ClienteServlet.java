
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
import logica.Entrada;
import logica.Tarjeta;
import persistencia.exceptions.NonexistentEntityException;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idC= request.getParameter("idC");
        String accion = request.getParameter("action");
        Controladora controladora = new Controladora();

        if (accion.equalsIgnoreCase("borrar")) {
            int id = Integer.parseInt(idC);
            try {
                controladora.eliminarCliente(id);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("modificacion-cliente.jsp");

        } else if (accion.equalsIgnoreCase("editarForm")) {
            int id = Integer.parseInt(idC);
            request.getSession().setAttribute("idCliente", id);
            response.sendRedirect("editar-cliente.jsp");

        } else if (accion.equalsIgnoreCase("agregar")) {
          
            String nombre = request.getParameter("nombre");
            String dni = request.getParameter("dni");
            String numero =request.getParameter("tarjeta");
            String puntos =request.getParameter("puntos");
            
            if (!nombre.equals("") && !dni.equals("") && !numero.equals("") && !puntos.equals("")) {
                int nro = Integer.parseInt(numero);
                int ptos = Integer.parseInt(puntos);
                Tarjeta tarjeta = controladora.crearTarjeta(ptos, nro);
                controladora.crearCliente(tarjeta, nombre, dni);
                response.sendRedirect("modificacion-cliente.jsp");

            } else {
                request.setAttribute("errorMessage", "Existen campos vacíos");
                request.getRequestDispatcher("form-cliente.jsp").forward(request, response);
            }
            
        } else if (accion.equalsIgnoreCase("editar")) {   
          
            int id = Integer.parseInt(request.getParameter("id"));          
            String nombre = request.getParameter("nombre");
            String dni = request.getParameter("dni");
            int nro = Integer.parseInt(request.getParameter("tarjeta"));
            int puntos = Integer.parseInt(request.getParameter("puntos"));

            Tarjeta tarjeta = controladora.crearTarjeta(puntos, nro);
            List<Entrada> listaEntradas = new ArrayList<Entrada>();
            
            try {
                controladora.editarCliente(id, tarjeta, nombre, dni);
            } catch (Exception ex) {
                Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            request.setAttribute("idCliente", null);
            response.sendRedirect("modificacion-cliente.jsp");

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
