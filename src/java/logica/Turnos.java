
package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Turnos implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTurno;
    private String turno;
    private int capacidadTurno;
    //se agregpa campo de capacidadOcupada
    private int lugaresOcupados;

    public Turnos(int idTurno, String turno, int capacidadTurno, int lugaresOcupados) {
        this.idTurno = idTurno;
        this.turno = turno;
        this.capacidadTurno = capacidadTurno;
        this.lugaresOcupados = lugaresOcupados;
        
    }

   public Turnos(int idTurno, int lugaresOcupados) {
        this.idTurno = idTurno;
        this.lugaresOcupados = lugaresOcupados;
        
    }
    
   public Turnos() {
      
        
    }
    
    
    public int getLugaresOcupados() {
        return lugaresOcupados;
    }

    public void setLugaresOcupados(int lugaresOcupados) {
        this.lugaresOcupados = lugaresOcupados;
    }

   

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }



    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getCapacidadTurno() {
        return capacidadTurno;
    }

    public void setCapacidadTurno(int capacidadTurno) {
        this.capacidadTurno = capacidadTurno;
    }
    
    
}
