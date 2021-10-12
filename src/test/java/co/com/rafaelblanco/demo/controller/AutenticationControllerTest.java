package co.com.rafaelblanco.demo.controller;

import co.com.rafaelblanco.demo.util.IConstante;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author rblanco on 20/07/21
 **/
@ExtendWith(SpringExtension.class)
@WebMvcTest(AutenticationController.class)
class AutenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void login() throws Exception {
        AutenticationController autenticationController = new AutenticationController();

        RequestBuilder requestAuth = MockMvcRequestBuilders
                .post(IConstante.Request.AUTH_CONTROLLER_API + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userId\": \"usuario1@correo.com\",\n" +
                        "    \"clave\" : \"1234567\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult resulAuth = mockMvc.perform(requestAuth).andReturn();
        System.out.println( resulAuth.getResponse().getContentAsString());

        /*LoginInDTO loginInDTO = new LoginInDTO();
        loginInDTO.setClave("12345678");
        loginInDTO.setUserId("usuario1@correo.com");
        ResponseEntity responseEntity = autenticationController.login(loginInDTO);
        if(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST)){
            RespuestaDTO respuestaDTO = (RespuestaDTO)responseEntity.getBody();
            System.out.println(respuestaDTO.toString());
            assertEquals("", respuestaDTO);
        }else{
            JwtDTO jwtDTO = (JwtDTO)responseEntity.getBody();
            assertNotNull(jwtDTO);
        }*/

    }
}