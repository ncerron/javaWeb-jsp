
package logica;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Entrada implements Serializable {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEntrada;
    @Temporal(TemporalType.DATE)
    private Date fecha;
//    @Temporal(TemporalType.TIME)
//    private Date hora;
    @OneToOne
    Turnos turno;
    @OneToOne
    Juego juego; 
    @OneToOne
    Cliente cliente;
    
    //cambiar turno en vez de hora

   
    public Entrada() {
    }

    public Entrada(int idEntrada, Date fecha, Turnos turno, Juego juego, Cliente cliente) {
        this.idEntrada = idEntrada;
        this.fecha = fecha;
        this.turno = turno;
        this.juego = juego;
        this.cliente = cliente;
      
    }

    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    public Turnos getTurno() {
        return turno;
    }

    public void setTurno(Turnos turno) {
        this.turno = turno;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }



    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
   
    
}
