package co.com.rafaelblanco.demo.impls;

import co.com.rafaelblanco.demo.modelo.UsuarioModelo;
import co.com.rafaelblanco.demo.security.UsuarioPrincipal;
import co.com.rafaelblanco.demo.util.IConstante;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rblanco
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UserDetailsService{

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 
     * @param userid
     * @return
     * @throws UsernameNotFoundException 
     */
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        
        UsuarioModelo u = null;
        if("admin1@correo.com".equals(userid)){
            u = new UsuarioModelo();
            u.setClave(new BCryptPasswordEncoder().encode("1234567"));
            u.setEstado(IConstante.EstadoUsuario.ACTIVO);
            u.setUserId(userid);
            Set<String> roles = new HashSet<>();
            roles.add("ROLE_ADMIN");
            u.setRoles(roles);
            
        }else if("usuario1@correo.com".equals(userid)){
            u = new UsuarioModelo();
            u.setClave(new BCryptPasswordEncoder().encode("1234567"));
            u.setEstado(IConstante.EstadoUsuario.ACTIVO);
            u.setUserId(userid);
            Set<String> roles = new HashSet<>();
            roles.add("ROLE_USUARIO");
            u.setRoles(roles);
        }
        
        if(u != null){
            mongoTemplate.save(u);
            return UsuarioPrincipal.build(u);
        }
        
        throw new UsernameNotFoundException( String.format("UserID {%s} no existe...", userid) );
        
    }
    
}
