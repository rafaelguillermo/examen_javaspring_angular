package co.com.rafaelblanco.demo.modelo;

import co.com.rafaelblanco.demo.util.IConstante;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author rblanco
 */
@Document(collection = "usuario")
public class UsuarioModelo {

    private ObjectId id;
    private String clave;
    private String userId;
    @Field(targetType = FieldType.STRING)
    private IConstante.EstadoUsuario estado;

    private Set<String> roles = new HashSet<>();

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public IConstante.EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(IConstante.EstadoUsuario estado) {
        this.estado = estado;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
