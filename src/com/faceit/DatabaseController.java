package com.faceit;

import com.faceit.entity.Face;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/db")
public class DatabaseController {
    @PersistenceContext
    private EntityManager em;

    @RequestMapping(method = RequestMethod.GET, value = "/bootstrap")
    public @ResponseBody String bootstrap() {
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

        return "OK";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clean")
    public @ResponseBody String clean() {
        em.createQuery("delete from Face f").executeUpdate();
        return "OK";
    }
}
