package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Empleado;
import logica.Horario;
import logica.Turnos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-01T17:03:16")
@StaticMetamodel(Juego.class)
public class Juego_ { 

    public static volatile ListAttribute<Juego, Turnos> ListaTurnos;
    public static volatile SingularAttribute<Juego, Integer> duracionTurno;
    public static volatile SingularAttribute<Juego, Horario> horario;
    public static volatile SingularAttribute<Juego, Integer> idJuego;
    public static volatile SingularAttribute<Juego, String> nombre;
    public static volatile ListAttribute<Juego, Empleado> ListaEmpleados;
    public static volatile SingularAttribute<Juego, Integer> capacidad;

}