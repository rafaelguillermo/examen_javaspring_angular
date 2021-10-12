package co.com.rafaelblanco.demo.security;

import co.com.rafaelblanco.demo.modelo.UsuarioModelo;
import co.com.rafaelblanco.demo.util.IConstante;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author rblanco
 */
public class UsuarioPrincipal implements UserDetails {

    private final UsuarioModelo usuarioModelo;
    private final Collection<? extends GrantedAuthority> autorities;

    /**
     *
     * @param usuarioModelo
     * @param autorities
     */
    public UsuarioPrincipal(UsuarioModelo usuarioModelo, Collection<? extends GrantedAuthority> autorities) {
        this.usuarioModelo = usuarioModelo;
        this.autorities = autorities;
    }

    /**
     *
     * @param usuarioModel
     * @return
     */
    public static UsuarioPrincipal build(UsuarioModelo usuarioModel){
        List<GrantedAuthority> autorities = usuarioModel.getRoles().stream()
                .map( rol -> new SimpleGrantedAuthority(rol))
                .collect(Collectors.toList());
        return new UsuarioPrincipal(usuarioModel, autorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return autorities;
    }

    @Override
    public String getPassword() {
        return usuarioModelo.getClave();
    }

    @Override
    public String getUsername() {
        return usuarioModelo.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return IConstante.EstadoUsuario.ACTIVO.equals(usuarioModelo.getEstado());
    }

}
