package main.domain.movie;

import main.infrastructure.web.dependency.annotation.Component;

import java.util.Map;
import java.util.Set;

@Component // place for sql queries - in future
    // cqs? MovieCommandProvider | MovieQueryProvider
class MovieProvider {

    private final MovieRepositoryInMemory repository;

    MovieProvider(){
        this.repository = MovieRepositoryInMemory.getInstance();
    }

    Set<Map.Entry<Long, Movie>> getAll(){
        return repository.findAll();
    }

    Movie addOne(Movie movie){
        return repository.save(movie);
    }

    Movie getById(Movie movie, Long id){
        return repository.findById(id).orElse(null);
    }
}
