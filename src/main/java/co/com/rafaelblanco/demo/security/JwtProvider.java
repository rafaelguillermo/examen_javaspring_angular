package co.com.rafaelblanco.demo.security;

import io.jsonwebtoken.*;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Esta clase se encarga se crear el token y validar si esta bien formado el token
 * @author rblanco
 */
@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;
    
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT_LONG = new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss");   
    

    /**
     *
     * @param authentication
     * @return
     */
    public JwtDTO generateToken(Authentication authentication){

        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        Calendar cExpiration = Calendar.getInstance();
        cExpiration.add(Calendar.MINUTE, expiration);

        JwtDTO jwtDTO = new JwtDTO();
        jwtDTO.setUserId(usuarioPrincipal.getUsername());
        jwtDTO.setAuthorities(usuarioPrincipal.getAuthorities());

        String jwt = Jwts.builder()
                .setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(cExpiration.getTime())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        jwtDTO.setToken(jwt);
        jwtDTO.setExperesInLong(cExpiration.getTimeInMillis());
        jwtDTO.setExperesInDate(SIMPLE_DATE_FORMAT_LONG.format(cExpiration.getTime()));
        
        //Aqui podriamos guardar el token generado en base de datos

        return jwtDTO;
    }
    public String getUserIdFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Token mal formado =>"+token, e);
        }catch (UnsupportedJwtException e){
            logger.error("Token no soportado =>"+token, e);
        }catch (ExpiredJwtException e){
            logger.error("Token expirado =>"+token, e);
        }catch (IllegalArgumentException e){
            logger.error("Token vacio =>"+token, e);
        }catch (SignatureException e){
            logger.error("Fallo en la firma =>"+token, e);
        }
        return false;
    }
    /**
     * 
     * @param token
     * @return 
     */
    public String getMessageStringExceptionFromToken(String token){
        
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        }catch (MalformedJwtException | UnsupportedJwtException | ExpiredJwtException | IllegalArgumentException | SignatureException e){
            return e.getMessage();
        }
        return null;
    }
}
