package co.com.rafaelblanco.demo.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author rblanco
 */
public class JwtDTO {
    
    private String token;
    private final String autenticacion = "Bearer";
    private String userId;
    private Collection<? extends GrantedAuthority> authorities;
    private String experesInDate;
    private Long experesInLong;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getExperesInDate() {
        return experesInDate;
    }

    public void setExperesInDate(String experesInDate) {
        this.experesInDate = experesInDate;
    }

    public Long getExperesInLong() {
        return experesInLong;
    }

    public void setExperesInLong(Long experesInLong) {
        this.experesInLong = experesInLong;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JwtDTO{");
        sb.append("token='").append(token).append('\'');
        sb.append(", autenticacion='").append(autenticacion).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", authorities=").append(authorities);
        sb.append(", experesInDate='").append(experesInDate).append('\'');
        sb.append(", experesInLong=").append(experesInLong);
        sb.append('}');
        return sb.toString();
    }
}
