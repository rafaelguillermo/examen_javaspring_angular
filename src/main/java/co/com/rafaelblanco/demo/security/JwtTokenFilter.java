package co.com.rafaelblanco.demo.security;

import co.com.rafaelblanco.demo.impls.UsuarioServiceImpl;
import co.com.rafaelblanco.demo.util.StringUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author rblanco
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain fc) throws ServletException, IOException {
        try{
            String token = StringUtilities.getInstance().getTokenJWT(req);
            if(token != null && jwtProvider.validateToken(token)){
                String userId = jwtProvider.getUserIdFromToken(token);
                UserDetails userDetails = usuarioService.loadUserByUsername(userId);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch(Exception e ){
            logger.error("Error en el metodo doFilterInternal",e);
        }
        fc.doFilter(req,resp);
    }
    
}
