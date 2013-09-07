package com.faceit.fetcher;

import com.faceit.entity.Face;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class DatabaseFacesFetcher implements AsyncFacesFetcher {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Async
    public Future<List<Face>> fetch() {
        Query query = em.createQuery("select f from Face f");
        return new AsyncResult<List<Face>>(new ArrayList<Face>(query.getResultList()));
    }
}
