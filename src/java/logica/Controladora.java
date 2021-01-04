
package logica;

//1:43 ultima clase

import java.util.Date;
import java.util.List;
import static logica.Tarjeta_.puntos;
import persistencia.ControladorPesistencia;
import persistencia.exceptions.NonexistentEntityException;

public class Controladora {
    
    ControladorPesistencia controlP = new ControladorPesistencia();

//////////////////////////////////  USUARIO  /////////////////////////////////   
    private List<Usuario> listaUsuarios;

    public void eliminarUsuario(int id) throws NonexistentEntityException {
        controlP.eliminarUsuario(id);
    }

    public void editarUsuario(int id, String nombre, String pass) throws Exception {
        Usuario usu = new Usuario();
        usu.setIdUsuario(id);
        usu.setNombre(nombre);
        usu.setPass(pass);
        controlP.editarUsuario(usu);
    }

    public void crearUsuario(String nombre, String pass) {
        //crea el usuario
        Usuario usu = new Usuario();
        usu.setNombre(nombre);
        usu.setPass(pass);

        //se guarda en base de datos
        controlP.crearUsuario(usu);
    }

    ///usado al crear nuevo empleado
    public Usuario crearUsuarioE(String nombre, String pass) {
        //crea el usuario
        Usuario usu = new Usuario();
        usu.setNombre(nombre);
        usu.setPass(pass);

        //se guarda en base de datos
        controlP.crearUsuario(usu);
        return usu;
    }

    public List<Usuario> getListaUsuarios() {
        return controlP.getListaUsuarios();
    }

    public Usuario getUsuario(int id) {
        return controlP.getUsuario(id);
    }

    public boolean comprobarIngreso(String usuario, String pass) {
        boolean ok = false;

        listaUsuarios = controlP.getListaUsuarios();
        for (Usuario usu : listaUsuarios) {
            if (usu.getNombre().equals(usuario) && usu.getPass().equals(pass)) {
                ok = true;
                return ok;
            }
        }
        return ok;
    }

    //////////////////////////////////  JUEGO  /////////////////////////////////   
    private List<Juego> listaJuegos;

    public void eliminarJuego(int id) throws NonexistentEntityException {
        controlP.eliminarJuego(id);
    }

    public void editarJuego(int id, String nombre, int capacidad, int duracion, List<Empleado> ListaEmpleados, Horario horario, List<Turnos> ListaTurnos) throws Exception {
        Juego juego = new Juego();
        juego.setIdJuego(id);
        juego.setNombre(nombre);
        juego.setCapacidad(capacidad);
        juego.setDuracionTurno(duracion);
        juego.setHorario(horario);
        juego.setListaEmpleados(ListaEmpleados);
        juego.setListaTurnos(ListaTurnos);        
        controlP.editarJuego(juego);
    }


    public void crearJuego(String nombre, int capacidad, int duracion, List<Empleado> ListaEmpleados, Horario horario, List<Turnos> ListaTurnos) {
        Juego juego = new Juego();
        juego.setNombre(nombre);
        juego.setCapacidad(capacidad);
        juego.setDuracionTurno(duracion);
        juego.setHorario(horario);
        juego.setListaEmpleados(ListaEmpleados);
        juego.setListaTurnos(ListaTurnos);
        controlP.crearJuego(juego);
    }

    
    public List<Juego> getListaJuegos() {
        return controlP.getListaJuegos();
    }

    public Juego getJuego(int id) {
        return controlP.getJuego(id);
    }

    public Horario crearHorario(String inicio, String fin) {
        Horario horario = new Horario();
        horario.setHoraInicio(inicio);
        horario.setHoraFin(fin);
        controlP.crearHorario(horario);
        return horario;
    }

    //int idTurno, String turno, int capacidadTurno, int lugaresOcupados
    public Turnos crearTurno(String turno, int capacidad, int lugaresOcupados){
        Turnos turnoo = new Turnos();
        turnoo.setCapacidadTurno(capacidad);
        turnoo.setTurno(turno);
        turnoo.setLugaresOcupados(lugaresOcupados);
        controlP.crearTurno(turnoo);
        return turnoo;       
    } 
    
    public List<Turnos> getListaTurnos() {
        return controlP.getListaTurnos();
    }
    
    
    //////////////////////////////////  EMPLEADO  /////////////////////////////////   
    private List<Empleado> listaEmpleados;

    public void eliminarEmpleado(int id) throws NonexistentEntityException {
        controlP.eliminarEmpleado(id);
    }

    public void editarEmpleado(int id, String nombre, String dni, Usuario usuario) throws Exception {
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(id);
        empleado.setNombre(nombre);
        empleado.setDni(dni);
        empleado.setUsuario(usuario);
        controlP.editarEmpleado(empleado);
    }

    public void crearEmpleado(String nombre, String dni, Usuario usuario) {
        //crea el usuario
        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setDni(dni);
        empleado.setUsuario(usuario);
        controlP.crearEmpleado(empleado);

    }

    public List<Empleado> getListaEmpleados() {
        return controlP.getListaEmpleados();
    }

    public Empleado getEmpleado(int id) {
        return controlP.getEmpleado(id);
    }

    ////////////////////////////////// CLIENTE  /////////////////////////////////   
    private List<Cliente> listaClientes;

    public void eliminarCliente(int id) throws NonexistentEntityException {
        controlP.eliminarCliente(id);
    }

    public void editarCliente(int id, Tarjeta tarjeta, String nombre, String dni) throws Exception {
        Cliente cli = new Cliente();
        cli.setIdCliente(id);
        cli.setNombre(nombre);
        cli.setDni(dni);
        cli.setTarjeta(tarjeta);
        controlP.editarClietne(cli);
    }

    public void crearCliente(Tarjeta tarjeta, String nombre, String dni) {
        Cliente cli = new Cliente();
        cli.setNombre(nombre);
        cli.setDni(dni);
        cli.setTarjeta(tarjeta);
        controlP.crearCliente(cli);
    }

    public List<Cliente> getListaCliente() {
        return controlP.getListaClientes();
    }

    public Cliente getCliente(int id) {
        return controlP.getCliente(id);
    }

    public Tarjeta crearTarjeta(int puntos, int nro) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNumero(nro);
        tarjeta.setPuntos(puntos);
        controlP.crearTarjeta(tarjeta);
        return tarjeta;
    }

 ////////////////////////////////// ENTRADA  /////////////////////////////////   
    private List<Entrada> listaEntradas;

    public void eliminarEntrada(int id) throws NonexistentEntityException {
        controlP.eliminarEntradas(id);
    }

//Date fecha, Turnos turno, Juego juego, Cliente cliente
    public void crearEntrada(Date fecha, Turnos turno, Juego juego, Cliente cliente) {
        Entrada entrada = new Entrada();
        entrada.setFecha(fecha);
        entrada.setJuego(juego);
        entrada.setTurno(turno);
        entrada.setCliente(cliente);
        controlP.crearEntrada(entrada);
    }

    public List<Entrada> getListaEntradas() {
        return controlP.getListaEntradas();
    }
  //int idEntrada, Date fecha, Turnos turno, Juego juego, Cliente cliente
    
    public void editarEntrada(int idEntrada, Date fecha, Turnos turno, Juego juego, Cliente cliente) throws Exception{
        Entrada entrada = new Entrada();
        entrada.setCliente(cliente);
        entrada.setFecha(fecha);
        entrada.setIdEntrada(idEntrada);
        entrada.setJuego(juego);
        entrada.setTurno(turno);
        controlP.editarEntrada(entrada);
    
    }
    
}
