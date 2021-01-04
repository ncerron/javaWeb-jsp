package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Cliente;
import logica.Juego;
import logica.Turnos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-01T17:03:16")
@StaticMetamodel(Entrada.class)
public class Entrada_ { 

    public static volatile SingularAttribute<Entrada, Date> fecha;
    public static volatile SingularAttribute<Entrada, Cliente> cliente;
    public static volatile SingularAttribute<Entrada, Turnos> turno;
    public static volatile SingularAttribute<Entrada, Integer> idEntrada;
    public static volatile SingularAttribute<Entrada, Juego> juego;

}