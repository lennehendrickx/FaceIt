package com.faceit.fetcher;

import com.faceit.entity.Face;

import java.util.List;
import java.util.concurrent.Future;

public interface AsyncFacesFetcher {
    Future<List<Face>> fetch();
}
