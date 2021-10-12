package co.com.rafaelblanco.demo.dtos;

/**
 *
 * @author rblanco
 */
public class RespuestaIpDTO {
    
    private IpDTO ipDto;
    private PaisDTO paisDTO;
    private MonedaDTO monedaDTO;

    public IpDTO getIpDto() {
        return ipDto;
    }

    public void setIpDto(IpDTO ipDto) {
        this.ipDto = ipDto;
    }

    public PaisDTO getPaisDTO() {
        return paisDTO;
    }

    public void setPaisDTO(PaisDTO paisDTO) {
        this.paisDTO = paisDTO;
    }

    public MonedaDTO getMonedaDTO() {
        return monedaDTO;
    }

    public void setMonedaDTO(MonedaDTO monedaDTO) {
        this.monedaDTO = monedaDTO;
    }
    
}
