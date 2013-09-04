package com.faceit;

import com.faceit.entity.Face;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/faces")
public class FacesController {
    @PersistenceContext
    private EntityManager em;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<Face> getFaces() {
            Query query = em.createQuery("select f from Face f");
            return new ArrayList<Face>(query.getResultList());
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody Face getFace(@PathVariable long id) {
        Key key = KeyFactory.createKey(Face.class.getSimpleName(), id);
        Query query = em.createQuery("select f from Face f where f.key = :key").setParameter("key", key);
        return (Face)query.getSingleResult();
    }
}
