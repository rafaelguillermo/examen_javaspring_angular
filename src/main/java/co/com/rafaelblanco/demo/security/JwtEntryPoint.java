/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rafaelblanco.demo.security;

import co.com.rafaelblanco.demo.util.StringUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Se encarga de comprobar si hay token valido devolviendo un 401 no autorizado
 * @author rblanco
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Autowired
    private JwtProvider jwtProvider;
    
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        logger.error("Error en el metodo commence: "+e.getMessage(),e);
        String token = StringUtilities.getInstance().getTokenJWT(req);
        String msg = jwtProvider.getMessageStringExceptionFromToken(token);
        //resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,msg != null ? msg : "No autorizado => "+e.getMessage());
        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,msg != null ? msg : "No autorizado => "+e.getMessage());
    }
}
