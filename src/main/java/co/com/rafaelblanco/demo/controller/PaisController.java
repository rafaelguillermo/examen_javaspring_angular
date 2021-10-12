package co.com.rafaelblanco.demo.controller;

import co.com.rafaelblanco.demo.dtos.IpDTO;
import co.com.rafaelblanco.demo.dtos.ListaNegraDTO;
import co.com.rafaelblanco.demo.dtos.MonedaDTO;
import co.com.rafaelblanco.demo.dtos.PaisDTO;
import co.com.rafaelblanco.demo.dtos.RespuestaIpDTO;
import co.com.rafaelblanco.demo.util.IConstante;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rblanco
 */
@RestController
@RequestMapping(IConstante.Request.PAIS_CONTROLLER_API)
@CrossOrigin(origins = "http://localhost:4200")
public class PaisController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().toString());
    private Map<String, RespuestaIpDTO> mapaRespuestaIpDTO = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/enviarListaNegra")
    public ResponseEntity enviarAlistaNegra(@RequestBody ListaNegraDTO listaNegra) {

        try {
            String dirHome = System.getProperty("user.home");
            File f = new File(dirHome, "listaNegra.txt");
            logger.info(f.toString());

            if (!f.exists()) {
                f.createNewFile();
            }

            Set<String> listaNegraActual = new HashSet<>();
            //Leemos el archivo
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                listaNegraActual.add(sc.nextLine());
            }
            for (String ip : listaNegra.getIps()) {
                listaNegraActual.add(ip);
            }
            sc.close();
            

            FileWriter fichero = null;
            PrintWriter pw;
            try {
                fichero = new FileWriter(f);
                pw = new PrintWriter(fichero);

                for (String ip : listaNegraActual) {
                    pw.println(ip);
                }

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            } finally {
                try {
                    // Nuevamente aprovechamos el finally para 
                    // asegurarnos que se cierra el fichero.
                    if (null != fichero) {
                        fichero.close();
                    }
                } catch (Exception e2) {
                    logger.error(e2.getMessage(), e2);
                    return new ResponseEntity<>(e2.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }

            return new ResponseEntity<>(listaNegra.getIps().size() + " registradas en lista en negra", HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @GetMapping(value = "/getInfoPais/{ip}")
    public ResponseEntity getInformacionPais(@PathVariable String ip) {

        if (ip == null || ip.trim().length() == 0) {
            return new ResponseEntity("IP no puede ser NULL", HttpStatus.BAD_REQUEST);
        }

        try {

            ip = ip.trim();
            
            RespuestaIpDTO respuestaIpDTO = new RespuestaIpDTO();
            
            
            String dirHome = System.getProperty("user.home");
            File f = new File(dirHome, "listaNegra.txt");
            logger.info(f.toString());

            boolean ipEnListaNegra = false;
            if (f.exists()) {
                //Leemos el archivo
                Scanner sc = new Scanner(f);

                while (sc.hasNextLine()) {
                    if(ip.equals(sc.nextLine())){
                        ipEnListaNegra = true;
                        break;
                    }
                }
                sc.close();
            }
            
            if(ipEnListaNegra){
                return new ResponseEntity<>("IP SE ENCUENTA EN LISTA NEGRA", HttpStatus.BAD_REQUEST);
            }

            //
            if(mapaRespuestaIpDTO.containsKey(ip)){
                return new ResponseEntity<>(mapaRespuestaIpDTO.get(ip), HttpStatus.OK);
            }
            
            
            //Llamado para consultar la informacion de la IP
            RequestEntity<Void> requestEntity = RequestEntity
                    .get(new URI(String.format(IConstante.URLS.IP_INFO, ip)))
                    .build();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<IpDTO> ipDo = restTemplate.exchange(requestEntity, IpDTO.class);
            respuestaIpDTO.setIpDto(ipDo.getBody());

            if (ipDo.getBody().getCountryCode3() != null && ipDo.getBody().getCountryCode3().trim().length() > 0) {
                //Llamado para consultar informacion del Pais
                requestEntity = RequestEntity
                        .get(new URI(String.format(IConstante.URLS.PAIS_INFO, ipDo.getBody().getCountryCode3())))
                        .build();
                restTemplate = new RestTemplate();
                ResponseEntity<PaisDTO> paisDto = restTemplate.exchange(requestEntity, PaisDTO.class);
                respuestaIpDTO.setPaisDTO(paisDto.getBody());

                //Llamado para consultar informacion de la mondeda
                requestEntity = RequestEntity
                        .get(new URI(IConstante.URLS.MONEDA_INFO))
                        .build();
                restTemplate = new RestTemplate();
                ResponseEntity<MonedaDTO> monedaDto = restTemplate.exchange(requestEntity, MonedaDTO.class);
                respuestaIpDTO.setMonedaDTO(monedaDto.getBody());

            }

            mapaRespuestaIpDTO.put(ip, respuestaIpDTO);
            return new ResponseEntity<>(respuestaIpDTO, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
