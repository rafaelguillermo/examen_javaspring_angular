package co.com.rafaelblanco.demo.controller;

import co.com.rafaelblanco.demo.impls.EmpleadoServiceImpl;
import co.com.rafaelblanco.demo.modelo.EmpleadoModelo;
import co.com.rafaelblanco.demo.util.IConstante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author rblanco on 11/10/21
 **/
@Controller
@RequestMapping(IConstante.Request.EMPLEADO_CONTROLLER_API)
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().toString());

    @Autowired
    private EmpleadoServiceImpl empleadoService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody EmpleadoModelo empleadoModelo){

        try {

            Assert.hasText(empleadoModelo.getNombre(), "El nombre del empleado no puede ser vacio");
            Assert.hasText(empleadoModelo.getApellido(), "El apellido del empleado no puede ser vacio");

            empleadoModelo.setEstado(IConstante.EstadoEmpleado.ACTIVO);

            empleadoService.save(empleadoModelo);

            return new ResponseEntity<>(
                    empleadoService,
                    HttpStatus.OK);

        }catch (Exception e){

            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getall")
    public ResponseEntity getAll(){
        try {

            return new ResponseEntity<>(
                    empleadoService.getAll(),
                    HttpStatus.OK);

        }catch (Exception e){

            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getone/{id}")
    public ResponseEntity getOne(@PathVariable String id){
        try {

            return new ResponseEntity<>(
                    empleadoService.getOne(id),
                    HttpStatus.OK);

        }catch (Exception e){

            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity eliminar(@PathVariable String id){
        try {

            empleadoService.elimina(id);
            return new ResponseEntity<>(
                    "Registro eliminado correctamente",
                    HttpStatus.OK);

        }catch (Exception e){

            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
