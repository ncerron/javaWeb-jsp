
package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Juego implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJuego;
    private String nombre;
    private int capacidad;
    private int duracionTurno;

    @OneToMany
    List<Empleado> ListaEmpleados;   
    @OneToOne
    Horario horario;
    @OneToMany
    List<Turnos> ListaTurnos;

    public Juego() {
    }

    public Juego(int idJuego, String nombre, int capacidad, int duracionTurno, List<Empleado> ListaEmpleados, Horario horario, List<Turnos> ListaTurnos) {
        this.idJuego = idJuego;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.duracionTurno = duracionTurno;
        this.ListaEmpleados = ListaEmpleados;
        
        this.horario = horario;
        this.ListaTurnos = ListaTurnos;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getDuracionTurno() {
        return duracionTurno;
    }

    public void setDuracionTurno(int duracionTurno) {
        this.duracionTurno = duracionTurno;
    }

    public List<Empleado> getListaEmpleados() {
        return ListaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> ListaEmpleados) {
        this.ListaEmpleados = ListaEmpleados;
    }


    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<Turnos> getListaTurnos() {
        return ListaTurnos;
    }

    public void setListaTurnos(List<Turnos> ListaTurnos) {
        this.ListaTurnos = ListaTurnos;
    }

}
