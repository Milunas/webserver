package main.web.endpoint;

import main.domain.movie.MovieRestController;
import main.web.server.request.Request;
import main.web.server.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Endpoint {

    private MovieRestController movieRestController = new MovieRestController();

    private static Endpoint instance = null;
    private final Map<String, Function<Request, Response>> endpoints;

    private Endpoint() {
        endpoints = new HashMap<>();
        endpoints.put("/api/movies", movieRestController::getMovies);
    }

    public static Endpoint getInstance() {
        if (instance == null) {
            instance = new Endpoint();
        }
        return instance;
    }

    public Map<String, Function<Request,Response>> getEndpoints(){
        return endpoints;
    }

}
