
package logica;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Empleado extends Persona implements Serializable {
   
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idEmpleado;
   @OneToOne
   Usuario usuario; 

    public Empleado(int idEmpleado, String nombre, String dni, Usuario usuario) {
        super(nombre, dni);
        this.idEmpleado = idEmpleado;
        this.usuario = usuario;
    }

    public Empleado() {
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
