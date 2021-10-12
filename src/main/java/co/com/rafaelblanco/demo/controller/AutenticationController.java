package co.com.rafaelblanco.demo.controller;

import co.com.rafaelblanco.demo.dtos.LoginInDTO;
import co.com.rafaelblanco.demo.dtos.RespuestaDTO;
import co.com.rafaelblanco.demo.security.JwtDTO;
import co.com.rafaelblanco.demo.security.JwtProvider;
import co.com.rafaelblanco.demo.util.IConstante;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.rafaelblanco.demo.util.StringUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author rblanco
 */
@RestController
@RequestMapping(IConstante.Request.AUTH_CONTROLLER_API)
@CrossOrigin(origins = "http://localhost:4200")
public class AutenticationController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass().toString());
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    /**
     * 
     * @param loginInDTO
     * @return 
     */
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@RequestBody LoginInDTO loginInDTO) {

        String logTransaccion = StringUtilities.getInstance().getTransaccionLog();
        logger.info(logTransaccion +"=>"+ String.format("%s/login", IConstante.Request.AUTH_CONTROLLER_API) +","+loginInDTO.toString());

        if (loginInDTO.getUserId() == null
                || loginInDTO.getUserId().trim().length() == 0
                || loginInDTO.getClave() == null
                || loginInDTO.getClave().trim().length() == 0) {

            return new ResponseEntity(new RespuestaDTO(HttpStatus.BAD_REQUEST,
                    IConstante.Mensajes.MSG_LOGIN_FORM_ERROR,
                    IConstante.TipoMensaje.ERROR), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginInDTO.getUserId(),
                        loginInDTO.getClave()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtDTO jwtDTO = jwtProvider.generateToken(authentication);
        
        /**
         * Aqui podemos enviar correo para alertar al usuario del nuevo inicio de correo
         */

        return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
        
    }
    
}
