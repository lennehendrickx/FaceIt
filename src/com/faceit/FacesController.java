package com.faceit;

import com.faceit.entity.Face;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
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
}
