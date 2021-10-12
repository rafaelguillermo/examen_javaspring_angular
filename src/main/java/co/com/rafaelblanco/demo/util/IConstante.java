package co.com.rafaelblanco.demo.util;

/**
 *
 * @author rblanco
 */
public interface IConstante {
    
    interface Application {
        String NAME_APP = "Mercado Libre";
    }
    
    interface URLS{
        String IP_INFO = "https://api.ip2country.info/ip?%s";
        String PAIS_INFO = "https://restcountries.eu/rest/v2/alpha/%s";
        String MONEDA_INFO = "http://data.fixer.io/api/latest?access_key=426c4fcaf9802693442f28b3b2eabf89";
    }
    
    enum EstadoUsuario{
        ACTIVO, INACTIVO
    }
    enum EstadoEmpleado{
        ACTIVO, INACTIVO
    }
    
    interface Request {
        String AUTH_CONTROLLER_API = "/auth";
        String PAIS_CONTROLLER_API = "/pais";
        String EMPLEADO_CONTROLLER_API = "/empleado";
    }
    
    interface Mensajes {
        String MSG_LOGIN_NEW_OK = "Nuevo inicio de sesion: ";
        String MSG_LOGIN_FORM_ERROR = "Campos incorrectos.";
        String MSG_SUBJECT_LOGIN_OK = "Autenticacion satisfactoria en " + Application.NAME_APP;
    }
    
    interface TipoMensaje {
        String INFORMATIVO = "INFORMATIVO";
        String ERROR = "ERROR";
        String ADVERTENCIA = "ADVERTENCIA";
    }
}
