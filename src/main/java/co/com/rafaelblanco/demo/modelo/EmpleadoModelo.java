package co.com.rafaelblanco.demo.modelo;

import co.com.rafaelblanco.demo.util.IConstante;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

/**
 *
 * @author rblanco
 */
@Document(collection = "empleado")
public class EmpleadoModelo {

    private ObjectId id;
    private String idUnico;
    private String nombre;
    private String apellido;
    private int edad;
    @Field(targetType = FieldType.STRING)
    private IConstante.EstadoEmpleado estado;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
        if(id != null){
            this.idUnico = id.toString();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public IConstante.EstadoEmpleado getEstado() {
        return estado;
    }

    public void setEstado(IConstante.EstadoEmpleado estado) {
        this.estado = estado;
    }

    public String getIdUnico() {
        if(this.id != null){
            return this.idUnico = id.toString();
        }
        return idUnico;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
