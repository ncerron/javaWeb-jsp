
package servlet;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Cliente;
import logica.Controladora;
import logica.Entrada;
import logica.Juego;
import logica.Turnos;
import persistencia.exceptions.NonexistentEntityException;


@WebServlet(name = "EntradaServlet", urlPatterns = {"/EntradaServlet"})
public class EntradaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idE = request.getParameter("idE");
        String accion = request.getParameter("action");
        Controladora controladora = new Controladora();

        if (accion.equalsIgnoreCase("borrar")) {
            int id = Integer.parseInt(idE);
            try {
                controladora.eliminarEntrada(id);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("modificacion-entrada.jsp");

        }  else if (accion.equalsIgnoreCase("agregar-primero")) {
            
            //verificar seleccionar fecha a partir de fecha del sitema en adelante
            String fecha = request.getParameter("fecha");
            String cliente = request.getParameter("cliente");
            String juego = request.getParameter("juego");
            
            if (!fecha.equals("") && !cliente.equals("") && !juego.equals("")) {
                
                try {
                    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

                    Date fechaActual = new Date();
                    String newStr = date.format(fechaActual);
                    Date fechaA = date.parse(newStr);

                    Date fechaInicio = date.parse(fecha);  //String a date
                    
                    if((fechaInicio.after(fechaA)) || (fechaInicio.equals(fechaA))){
                        //fecha inicio mayor
                        
                        request.getSession().setAttribute("fecha", fecha);
                        request.getSession().setAttribute("cliente", cliente);
                        request.getSession().setAttribute("juego", juego);
                        
                        //buscar id Juego par luego hacer la busqueda de los turnos
                        int idJuego = 0;
                        
                        List<Juego> listaJuegos = controladora.getListaJuegos();
                        for (Juego elem : listaJuegos) {
                            if (elem.getNombre().equals(juego)) {
                                idJuego = elem.getIdJuego();
                                request.getSession().setAttribute("idJuego", idJuego);
                            }
                        }
                        response.sendRedirect("form-entrada2.jsp");
                        
                    }else{
                        request.setAttribute("errorMessage", "Verifique fecha ingresada");
                        request.getRequestDispatcher("form-entrada.jsp").forward(request, response);
                        
                        
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(EntradaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                   

            } else {
                request.setAttribute("errorMessage", "Existen campos vacíos");
                request.getRequestDispatcher("form-entrada.jsp").forward(request, response);
            }

        } else if (accion.equalsIgnoreCase("agregar")) {
            
            String fecha = request.getParameter("fecha");
            String cliente = request.getParameter("cliente");
            String juego = request.getParameter("juego");
            String[] listaId;
            listaId = request.getParameterValues("idTurno");

            if (null != listaId && listaId.length > 0) {
                int idT = Integer.parseInt(listaId[0].replace(" ", ""));

                //fecha
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date f = new Date();
                try {
                    f = sdf.parse(fecha);
                } catch (ParseException ex) {
                    Logger.getLogger(EntradaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                //cliente
                Cliente c = new Cliente();
                List<Cliente> listaClientes = controladora.getListaCliente();
                for (Cliente elem : listaClientes) {
                    if (elem.getNombre().equals(cliente)) {
                        c = elem;
                    }
                }

                //juego
                Juego j = new Juego();
                List<Juego> listaJuegos = controladora.getListaJuegos();
                for (Juego elem : listaJuegos) {
                    if (elem.getNombre().equals(juego)) {
                        j = elem;
                    }
                }

                //turnos      
                Turnos t = new Turnos();
                List<Turnos> listaTurnos = j.getListaTurnos();
                for (Turnos elem : listaTurnos) {
                    if (elem.getIdTurno() == idT) {
                       t=elem;
                    }
                }
                controladora.crearEntrada(f, t, j, c);
                response.sendRedirect("modificacion-entrada.jsp");
            }
            
            else{
             request.setAttribute("errorMessage", "Seleccione un turno");
                request.getRequestDispatcher("form-entrada2.jsp").forward(request, response);
            }
            
            
        } else if (accion.equalsIgnoreCase("editarForm")) {
            //vengo de modificacion-entrada
            String idEntrada = request.getParameter("idE");
            int idEnt = Integer.parseInt(idEntrada);
            request.getSession().setAttribute("idEntrada", idEnt);
            List<Entrada> listaEntradas = controladora.getListaEntradas();

            for (Entrada entrada : listaEntradas) {
                if (entrada.getIdEntrada() == idEnt) {
                    Date dia =entrada.getFecha();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String fecha= sdf.format(dia);
                      
                    String cliente = entrada.getCliente().getNombre();
                    String juego=entrada.getJuego().getNombre();
                    
                    request.getSession().setAttribute("fecha", fecha);
                    request.getSession().setAttribute("cliente", cliente);
                    request.getSession().setAttribute("juego", juego);
                }
            }

            response.sendRedirect("editar-entrada.jsp");

        }else if (accion.equalsIgnoreCase("editar")) {
            
            try {
                //vengo de editar-entrada
              
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = request.getParameter("fecha");
                Date fechaActual = new Date();
                String newStr = date.format(fechaActual);
                Date fechaA = date.parse(newStr);
                
                Date fechaInicio = date.parse(fecha);  //String a date
                
                if ((fechaInicio.after(fechaA)) || (fechaInicio.equals(fechaA))) {
                    //fecha inicio mayor
                   
                    String id = request.getParameter("idEntrada");
                    String cliente = request.getParameter("cliente");
                    String juego = request.getParameter("juego");

                    request.getSession().setAttribute("fecha", fecha);
                    request.getSession().setAttribute("cliente", cliente);
                    request.getSession().setAttribute("juego", juego);
                    request.getSession().setAttribute("idEntrada", id);

                    response.sendRedirect("editar-entrada2.jsp");
                } else {
                    request.setAttribute("errorMessage", "Verifique fecha ingresada");
                    request.getRequestDispatcher("editar-entrada.jsp").forward(request, response);
                    
                }
            } catch (ParseException ex) {
                Logger.getLogger(EntradaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }


        
        }else if (accion.equalsIgnoreCase("editar2")) {
            //vengo de editar-entrada2
            
            String id = request.getParameter("idEntrada");
            String cliente = request.getParameter("cliente");
            String juego = request.getParameter("juego");
            String fecha = request.getParameter("fecha");
            String[] listaId;
            listaId = request.getParameterValues("idTurno");

            if (null != listaId && listaId.length > 0) {

                try {
                    int idEntrada = Integer.parseInt(id);
                    int idT = Integer.parseInt(listaId[0].replace(" ", ""));
                    
                    //fecha
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date f = new Date();
                    f = sdf.parse(fecha);
                    
                    //cliente
                    Cliente c = new Cliente();
                    List<Cliente> listaClientes = controladora.getListaCliente();
                    for (Cliente elem : listaClientes) {
                        if (elem.getNombre().equals(cliente)) {
                            c = elem;
                        }
                    }
                    
                    //juego
                    Juego j = new Juego();
                    List<Juego> listaJuegos = controladora.getListaJuegos();
                    for (Juego elem : listaJuegos) {
                        if (elem.getNombre().equals(juego)) {
                            j = elem;
                        }
                    }
                    
                    //turnos
                    Turnos t = new Turnos();
                    List<Turnos> listaTurnos = j.getListaTurnos();
                    for (Turnos elem : listaTurnos) {
                        if (elem.getIdTurno() == idT) {
                            t = elem;
                        }
                    }
                    
                    try {
                        //int idEntrada, Date fecha, Turnos turno, Juego juego, Cliente cliente
                        controladora.editarEntrada(idEntrada, f, t, j, c);
                    } catch (Exception ex) {
                        Logger.getLogger(EntradaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.sendRedirect("modificacion-entrada.jsp");
                } catch (ParseException ex) {
                    Logger.getLogger(EntradaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                request.setAttribute("errorMessage", "Seleccione un turno");
                request.getRequestDispatcher("editar-entrada2.jsp").forward(request, response);
            }



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
