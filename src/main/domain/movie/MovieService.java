package main.domain.movie;

import main.infrastructure.web.dependency.annotation.Component;
import main.infrastructure.web.server.response.ResponseBody;

@Component
class MovieService {

    private final MovieProvider provider;
    private final static String COMMA = ",";

    MovieService(){
        this.provider = new MovieProvider();
    }

    ResponseBody getAll(){
        ResponseBody body = new ResponseBody();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        provider.getAll()
                .forEach(longMovieEntry -> builder.append(longMovieEntry.getValue().toString()).append(COMMA));
        builder.delete(builder.length()-1, builder.length());
        builder.append("]");
        body.setContent(builder.toString());
        return body;
    }

    //TODO fixme - parser json to object
    ResponseBody add(String content){
        String[] strings = content.split(":");
        String replaced = strings[1].replaceAll("\"","");
        String title = replaced.replaceAll("}","");
        Movie movie = new Movie(title.trim());
        ResponseBody body = new ResponseBody();
        body.setContent(provider.addOne(movie).toString());
        return body;
    }

}
