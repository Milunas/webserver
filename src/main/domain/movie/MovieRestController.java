package main.domain.movie;

import main.infrastructure.web.dependency.annotation.Component;
import main.infrastructure.web.dependency.annotation.Endpoint;
import main.infrastructure.web.server.http.HttpHeader;
import main.infrastructure.web.server.http.HttpHeaders;
import main.infrastructure.web.server.http.HttpProtocol;
import main.infrastructure.web.server.http.HttpStatus;
import main.infrastructure.web.server.request.Request;
import main.infrastructure.web.server.response.Response;
import main.infrastructure.web.server.response.ResponseBody;

@Component
public class MovieRestController {

    private final MovieService service;
    private static final String ID = "\\w";

    public MovieRestController(){
        this.service = new MovieService();
    }

    @Endpoint(path = "/api/movies", method = "GET")
    public Response getMovies(Request request){
        String body = service.getAll().getContent();
        Response response = prepareResponse(request.getProtocol());
        response.setBody(body);
        return response;
    }

    @Endpoint(path = "/api/movies", method = "GET")
    public Response addMovie(Request request){
        Response response = prepareResponse(request.getProtocol());
        String body = service.add(request.getRequestBody().getContent()).getContent();
        response.setBody(body);
        return response;
    }

    @Endpoint(path = "/api/movies/"+ID, method = "GET")
    public Response getMovie(Request request) {
        String[] strings = request.getEndpoint().getPath().split("/");
        String id = strings[3];
        String body = service.getById(id).getContent();
        Response response = prepareResponse(request.getProtocol());
        response.setBody(body);
        return response;
    }

    @Endpoint(path = "/api/movies/"+ID, method = "GET")
    public Response putMovie(Request request){
        return prepareResponse(request.getProtocol());
    }

    @Endpoint(path = "/api/movies/"+ID, method = "GET")
    public Response deleteMovie(Request request){
        return prepareResponse(request.getProtocol());
    }

    private Response prepareResponse(HttpProtocol protocol){
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setHeaders(prepareHeaders());
        response.setProtocol(protocol);
        return response;
    }

    private HttpHeaders prepareHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(new HttpHeader("Content-Type", "application/json"));
        return headers;
    }

}
