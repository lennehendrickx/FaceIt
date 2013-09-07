package com.faceit.fetcher;

import com.faceit.entity.Face;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class RedditFacesFetcher implements AsyncFacesFetcher {

    @Override
    @Async
    public Future<List<Face>> fetch() {
        URLFetchService fetcher = URLFetchServiceFactory.getURLFetchService();
        try {
            HTTPResponse response = fetcher.fetch(new URL("http://www.reddit.com/r/pareidolia.json"));
            JsonElement root = new JsonParser().parse(new String(response.getContent()));

            List<Face> faces = new ArrayList<Face>();
            JsonArray facesData = root.getAsJsonObject().get("data").getAsJsonObject().get("children").getAsJsonArray();
            for (JsonElement jsonElement : facesData) {
                JsonObject faceData = jsonElement.getAsJsonObject().get("data").getAsJsonObject();
                String url = faceData.get("url").getAsString();
                if(url.endsWith(".jpg") || url.endsWith(".png")) {
                    Face face = new Face();
                    face.setTitle(faceData.get("title").getAsString());
                    face.setUrl(url);
                    faces.add(face);
                }
            }
            return new AsyncResult<List<Face>>(faces);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
