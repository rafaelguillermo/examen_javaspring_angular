package co.com.rafaelblanco.demo.dtos;

/**
 *
 * @author rblanco
 */
public class PaisInfoDTO {
    
    private String nombrePais;
    private String codigoIsoPais;
    private String monedaLocal;
    private String valorDolares;

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getCodigoIsoPais() {
        return codigoIsoPais;
    }

    public void setCodigoIsoPais(String codigoIsoPais) {
        this.codigoIsoPais = codigoIsoPais;
    }

    public String getMonedaLocal() {
        return monedaLocal;
    }

    public void setMonedaLocal(String monedaLocal) {
        this.monedaLocal = monedaLocal;
    }

    public String getValorDolares() {
        return valorDolares;
    }

    public void setValorDolares(String valorDolares) {
        this.valorDolares = valorDolares;
    }
    
    
}
