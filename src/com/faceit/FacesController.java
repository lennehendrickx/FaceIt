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

    @RequestMapping(method = RequestMethod.GET, value = "/bootstrap")
    public void bootstrap() {
        Face face1 = new Face();
        face1.setTitle("Ummm.... yeah");
        face1.setDescription("Face 1");
        face1.setUrl("http://i.imgur.com/WVVpPMr.jpg");

        em.persist(face1);

        Face face2 = new Face();
        face2.setTitle("King of Diamonds in the Barringer Crater");
        face2.setDescription("Face 2");
        face2.setUrl("http://i.imgur.com/waMwijR.jpg");

        em.persist(face2);
    }
}
