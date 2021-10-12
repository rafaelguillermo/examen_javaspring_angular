/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rafaelblanco.demo.dtos;

/**
 *
 * @author rblanco
 */
public class LoginInDTO {
    
    private String userId;
    private String clave;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "LoginInDTO{" + "userId=" + userId + ", clave=" + clave + '}';
    }
    
    
    
}
