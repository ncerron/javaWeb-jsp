package persistencia;

import java.util.List;
import logica.Cliente;
import logica.Empleado;
import logica.Entrada;
import logica.Horario;
import logica.Juego;
import logica.Tarjeta;
import logica.Turnos;
import logica.Usuario;
import persistencia.exceptions.NonexistentEntityException;

public class ControladorPesistencia {

    ClienteJpaController clienteJPA = new ClienteJpaController();
    EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
    EntradaJpaController entradaJPA = new EntradaJpaController();
    HorarioJpaController horarioJPA = new HorarioJpaController();
    JuegoJpaController juegoJPA = new JuegoJpaController();
    TarjetaJpaController tarjetaJPA = new TarjetaJpaController();
    TurnosJpaController turnosJPA = new TurnosJpaController();
    UsuarioJpaController usuarioJPA = new UsuarioJpaController();

   //////////////////////////////////  USUARIO  /////////////////////////////////   
    
    public void crearUsuario(Usuario usuario) {
        usuarioJPA.create(usuario);
    }

    public List<Usuario> getListaUsuarios() {
        List<Usuario> listaUsuarios;
        listaUsuarios = usuarioJPA.findUsuarioEntities();
        return listaUsuarios;
    }

    public void eliminarUsuario(int id) throws NonexistentEntityException {
        usuarioJPA.destroy(id);
    }

    public void editarUsuario(Usuario usuario) throws Exception {
        usuarioJPA.edit(usuario);
    }

    public Usuario getUsuario(int id) {
        return usuarioJPA.findUsuario(id);
    }

    //////////////////////////////////  JUEGO  /////////////////////////////////  
    
     public void crearJuego(Juego juego) {
        juegoJPA.create(juego);
    }

    public List<Juego> getListaJuegos() {
        List<Juego> listaJuegos;
        listaJuegos = juegoJPA.findJuegoEntities();
        return listaJuegos;
    }

    public void eliminarJuego(int id) throws NonexistentEntityException {
        juegoJPA.destroy(id);
    }

    public void editarJuego(Juego juego) throws Exception {
        juegoJPA.edit(juego);
    }

    public Juego getJuego(int id) {
        return juegoJPA.findJuego(id);
    }
    
    
    public void crearHorario(Horario horario) {
        horarioJPA.create(horario);
    }
  
    public void crearTurno(Turnos turno) {
        turnosJPA.create(turno);
    }   
    
     public List<Turnos> getListaTurnos() {
        List<Turnos> listaTurnos;
        listaTurnos = turnosJPA.findTurnosEntities();
        return listaTurnos;
    }
    
     //////////////////////////////////  EMPLEADO  /////////////////////////////////  
    
     public void crearEmpleado(Empleado empleado) {
         empleadoJPA.create(empleado);
    }

    public List<Empleado> getListaEmpleados() {
        List<Empleado> listaEmpleados;
        listaEmpleados = empleadoJPA.findEmpleadoEntities();
        return listaEmpleados;
    }

    public void eliminarEmpleado(int id) throws NonexistentEntityException {
        empleadoJPA.destroy(id);
    }

    public void editarEmpleado(Empleado empleado) throws Exception {
        empleadoJPA.edit(empleado);
    }

    public Empleado getEmpleado(int id) {
        return empleadoJPA.findEmpleado(id);
    }
    
    //////////////////////////////////  CLIENTE  /////////////////////////////////  
    
     public void crearCliente(Cliente cliente) {
         clienteJPA.create(cliente);
    }

    public List<Cliente> getListaClientes() {
        List<Cliente> listaClientes;
        listaClientes = clienteJPA.findClienteEntities();
        return listaClientes;
    }

    public void eliminarCliente(int id) throws NonexistentEntityException {
        clienteJPA.destroy(id);
    }

    public void editarClietne(Cliente cliente) throws Exception {
        clienteJPA.edit(cliente);
    }

    public Cliente getCliente(int id) {
        return clienteJPA.findCliente(id);
    }
    
    ////tarjeta
    
    public void crearTarjeta(Tarjeta tarjeta) {
        tarjetaJPA.create(tarjeta);
    }
    
    
    //////////////////////////////////  ENTRADA  /////////////////////////////////  
    
     public void crearEntrada(Entrada entrada) {
         entradaJPA.create(entrada);
    }

    public List<Entrada> getListaEntradas() {
        List<Entrada> listaEntradas;
        listaEntradas = entradaJPA.findEntradaEntities();
        return listaEntradas;
    }

    public void eliminarEntradas(int id) throws NonexistentEntityException {
        entradaJPA.destroy(id);
    }

    public void editarEntrada(Entrada entrada) throws Exception{
        entradaJPA.edit(entrada);
    
    }
    
}
