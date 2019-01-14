package main.infrastructure.web.endpoint;

import main.domain.movie.MovieRestController;
import main.infrastructure.web.dependency.DependencyLoader;
import main.infrastructure.web.server.http.HttpEndpoint;
import main.infrastructure.web.server.request.Request;
import main.infrastructure.web.server.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// TODO Fixme - with dependency!
public class DeclaredEndpoint {

    private MovieRestController movieRestController = new MovieRestController();

    private static DeclaredEndpoint instance = null;
    private final Map<HttpEndpoint, Function<Request, Response>> endpoints;

    private DeclaredEndpoint() {
        endpoints = new HashMap<>();
        endpoints.put(getMoviesEndpoint(), movieRestController::getMovies);
        endpoints.put(postMovieEndpoint(), movieRestController::addMovie);
        endpoints.put(getMovieEndpoint(), movieRestController::getMovie);
    }

    public static DeclaredEndpoint getInstance() {
        if (instance == null) {
            instance = new DeclaredEndpoint();
        }
        return instance;
    }

    public Map<HttpEndpoint, Function<Request,Response>> getEndpoints(){
        return endpoints;
    }

    private HttpEndpoint getMoviesEndpoint(){
        HttpEndpoint getMovies = new HttpEndpoint();
        getMovies.setPath("/api/movies");
        getMovies.setMethod("GET");
        return getMovies;
    }

    private HttpEndpoint postMovieEndpoint(){
        HttpEndpoint getMovies = new HttpEndpoint();
        getMovies.setPath("/api/movies");
        getMovies.setMethod("POST");
        return getMovies;
    }

    private HttpEndpoint getMovieEndpoint(){
        HttpEndpoint getMovie = new HttpEndpoint();
        getMovie.setPath("/api/movies/\\w");
        getMovie.setMethod("GET");
        return getMovie;
    }
}
