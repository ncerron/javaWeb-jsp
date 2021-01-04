
package servlet;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
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
import logica.Horario;
import logica.Juego;
import logica.Turnos;
import persistencia.exceptions.NonexistentEntityException;

@WebServlet(name = "JuegoServlet", urlPatterns = {"/JuegoServlet"})
public class JuegoServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String idJ = request.getParameter("idJ");
        String accion = request.getParameter("action");
        Controladora controladora = new Controladora();

        if (accion.equalsIgnoreCase("borrar")) {
            int id = Integer.parseInt(idJ);
            try {
                controladora.eliminarJuego(id);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(JuegoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("modificacion-juego.jsp");

        } else if (accion.equalsIgnoreCase("editarForm")) {
            int id = Integer.parseInt(idJ);
            request.getSession().setAttribute("idJuego", id);
            response.sendRedirect("editar-juego.jsp");

        } else if (accion.equalsIgnoreCase("agregar")) {

            String nombre = request.getParameter("nombre");
            String dur = request.getParameter("duracion");
            String cap = request.getParameter("capacidad");
            String horaInicio = request.getParameter("horaInicio");
            String horaFin = request.getParameter("horaFin");

            if (!nombre.equals("") && !dur.equals("") && !cap.equals("") && !horaInicio.equals("") && !horaFin.equals("")) {

                int duracion = Integer.parseInt(dur);
                int capacidad = Integer.parseInt(cap);

                Horario horario = controladora.crearHorario(horaInicio, horaFin);
                
                //crea turnos
                List<Turnos> turnos = generarTurnos(horaInicio, horaFin, duracion, controladora, capacidad);

                //empleados        
                String[] listaEmpleadoForm;
                listaEmpleadoForm = request.getParameterValues("listaEmpleados");
                List<Empleado> listaEmpleados = controladora.getListaEmpleados();

                List<Empleado> listaEmpleadoJuego = new ArrayList<>();

                for (int i = 0; i < listaEmpleadoForm.length; i++) {
                    for (Empleado elem : listaEmpleados) {
                        String valor = Integer.toString(elem.getIdEmpleado());
                        if (valor.equals(listaEmpleadoForm[i].replace(" ", ""))) {
                            listaEmpleadoJuego.add(elem);
                            break;
                        }
                    }
                }

                controladora.crearJuego(nombre, capacidad, duracion, listaEmpleadoJuego, horario, turnos);
                response.sendRedirect("modificacion-juego.jsp");

            } else {
                request.setAttribute("errorMessage", "Existen campos vacíos");
                request.getRequestDispatcher("form-juego.jsp").forward(request, response);
            }

        } else if (accion.equalsIgnoreCase("editar")) {   
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            int duracion = Integer.parseInt(request.getParameter("duracion"));
            int capacidad = Integer.parseInt(request.getParameter("capacidad"));
            String horaInicio = request.getParameter("horaInicio");
            String horaFin = request.getParameter("horaFin");

            Horario horario = controladora.crearHorario(horaInicio, horaFin);

            //busco listaturnos de determinado juebo
            Juego juego = controladora.getJuego(id);
            List<Turnos> turnos = juego.getListaTurnos();

            //empleados        
            String[] listaEmpleadoForm;
            listaEmpleadoForm = request.getParameterValues("listaEmpleados");
            List<Empleado> listaEmpleados = controladora.getListaEmpleados();

            List<Empleado> listaEmpleadoJuego = new ArrayList<>();

            for (int i = 0; i < listaEmpleadoForm.length; i++) {
                for (Empleado elem : listaEmpleados) {
                    String valor = Integer.toString(elem.getIdEmpleado());
                    if (valor.equals(listaEmpleadoForm[i].replace(" ", ""))) {
                        listaEmpleadoJuego.add(elem);
                        break;
                    }
                }
            }

            try {
                controladora.editarJuego(id, nombre, capacidad, duracion, listaEmpleadoJuego, horario, turnos);
            } catch (Exception ex) {
                Logger.getLogger(JuegoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("idJuego", null);
            response.sendRedirect("modificacion-juego.jsp");

        } else {
            response.sendRedirect("home.jsp");
        }       
    }

    private List<Turnos> generarTurnos(String horaInicio, String horaFin, int duracion, Controladora controladora, int capacidad) {
        //calculo de turnos
        LocalTime t1 = LocalTime.parse(horaInicio); 
        LocalTime t2 = LocalTime.parse(horaFin);  
        Duration diff = Duration.between(t2, t1); 
        int intervalos = Math.abs(60 / duracion);  
        List<Turnos> turnos = new ArrayList<>();
        String[] periodo = new String[intervalos];
        int acum = 0;
    
        for (int i = 0; i < periodo.length; i++) {
            if (i == 0) {
                if (t1.getMinute() < 10) {
                    periodo[i] = "0" + Integer.toString(t1.getMinute());
                    acum = acum + t1.getMinute();
                } 
            } else {
                acum = acum + duracion;
                periodo[i] = Integer.toString( acum );            
            }
        }
        for (int i = t1.getHour(); i < t2.getHour(); i++) { // hora
            for (int j = 0; j < periodo.length; j++) {//minutos
                String time = i  + ":" + periodo[j];
                if (i < 10) {
                    time = "0" + time;
                }
                
                int ocupado = 0;
                Turnos turno = controladora.crearTurno(time, capacidad, ocupado);
                turnos.add(turno);
            }
        }
        return turnos;
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
