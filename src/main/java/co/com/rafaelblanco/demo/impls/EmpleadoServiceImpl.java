package co.com.rafaelblanco.demo.impls;

import co.com.rafaelblanco.demo.modelo.EmpleadoModelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author rblanco
 */
@Service
@Transactional
public class EmpleadoServiceImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     *
     * @param empleadoModelo
     * @return
     * @throws Exception
     */
    public EmpleadoModelo save(EmpleadoModelo empleadoModelo) throws Exception{
        mongoTemplate.save(empleadoModelo);
        return empleadoModelo;
    }

    public void elimina(String id) throws Exception{
        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.findAndRemove(q, EmpleadoModelo.class);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public List<EmpleadoModelo> getAll() throws Exception{

        return mongoTemplate.findAll(EmpleadoModelo.class);

    }

    public EmpleadoModelo getOne(String id) throws Exception{

        return mongoTemplate.findById(id, EmpleadoModelo.class);

    }
    
}
