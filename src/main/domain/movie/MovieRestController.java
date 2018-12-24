package main.domain.movie;

import main.web.server.http.HttpHeader;
import main.web.server.http.HttpHeaders;
import main.web.server.http.HttpStatus;
import main.web.server.request.Request;
import main.web.server.response.Response;

public class MovieRestController {

    public Response getMovies(Request request){
        Movie movie = new Movie("TITLE");
        HttpHeaders headers = new HttpHeaders();
        headers.add(new HttpHeader("Content-Type", "application/json"));
        Response response = new Response();
        response.setProtocol(request.getProtocol());
        response.setStatus(HttpStatus.OK);
        response.setHeaders(headers);
        response.setBody(movie);
        return response;
    }
}
