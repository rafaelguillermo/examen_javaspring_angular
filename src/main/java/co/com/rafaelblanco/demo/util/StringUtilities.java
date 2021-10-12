package co.com.rafaelblanco.demo.util;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author rblanco
 */
public class StringUtilities {
    
    private static StringUtilities stringUtilities;

    /**
     *
     */
    private StringUtilities() {
    }

    /**
     * @return
     */
    public static StringUtilities getInstance() {
        if (stringUtilities == null) {
            stringUtilities = new StringUtilities();
        }
        return stringUtilities;
    }
    
    /**
     * 
     * @param req
     * @return 
     */
    public String getTokenJWT(HttpServletRequest req) {
        if (req == null) {
            return null;
        }
        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.replaceFirst("Bearer ", "");
        }
        return null;
    }
    
    public synchronized String getTransaccionLog() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
