package com.faceit.fetcher;

import com.faceit.entity.Face;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AllFacesFetcher {

    private List<AsyncFacesFetcher> fetchers;

    public AllFacesFetcher(List<AsyncFacesFetcher> fetchers) {
        this.fetchers = fetchers;
    }

    public List<Face> fetch() {
        List<Future<List<Face>>> futures = new ArrayList<Future<List<Face>>>();
        for(AsyncFacesFetcher fetcher : fetchers) {
            futures.add(fetcher.fetch());
        }

        List<Face> faces = new ArrayList<Face>();
        for (Future<List<Face>> future : futures) {
            try {
                faces.addAll(future.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return faces;
    }
}
