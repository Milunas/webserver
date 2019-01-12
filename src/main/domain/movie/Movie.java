package main.domain.movie;

import main.infrastructure.web.dependency.annotation.Model;

@Model
public class Movie {

    private Long id;
    private String title;

    public Movie(String title){
        this.title = title;
    }

    @Override
    public String toString() {
        return "{\"title\":" + "\"" + title + "\"}";
    }

    public void setId(Long id) {
        this.id = id;
    }
}
