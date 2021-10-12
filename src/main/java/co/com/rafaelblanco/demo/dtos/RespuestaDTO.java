package co.com.rafaelblanco.demo.dtos;

import org.springframework.http.HttpStatus;

/**
 *
 * @author rblanco
 */
public class RespuestaDTO {
    
    private HttpStatus httpStatus;
    private String mensaje;
    private String tipoMensaje;
    private String referencia1;

    public RespuestaDTO() {
    }

    
    /**
     * 
     * @param httpStatus
     * @param mensaje
     * @param tipoMensaje
     */
    public RespuestaDTO(HttpStatus httpStatus, String mensaje, String tipoMensaje) {
        this.httpStatus = httpStatus;
        this.mensaje = mensaje;
        this.tipoMensaje = tipoMensaje;
    }

    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getReferencia1() {
        return referencia1;
    }

    public void setReferencia1(String referencia1) {
        this.referencia1 = referencia1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RespuestaDTO{");
        sb.append("httpStatus=").append(httpStatus);
        sb.append(", mensaje='").append(mensaje).append('\'');
        sb.append(", tipoMensaje='").append(tipoMensaje).append('\'');
        sb.append(", referencia1='").append(referencia1).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
