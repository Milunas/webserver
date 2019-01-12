package main.domain.movie;

import main.infrastructure.web.dependency.annotation.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
class MovieRepositoryInMemory {

    private ConcurrentHashMap<Long, Movie> concurrentHashMap;
    private static MovieRepositoryInMemory instance;

    private MovieRepositoryInMemory(){
        concurrentHashMap = new ConcurrentHashMap<>();
        init();
    }

    private void init(){
        concurrentHashMap.put(0L, new Movie("TITLE0"));
        concurrentHashMap.put(1L, new Movie("TITLE1"));
        concurrentHashMap.put(2L, new Movie("TITLE2"));
    }

    static MovieRepositoryInMemory getInstance(){
        if (instance == null){
            instance = new MovieRepositoryInMemory();
        }
        return instance;
    }

    Set<Map.Entry<Long, Movie>> findAll(){
        return concurrentHashMap.entrySet();
    }

    Movie save(Movie movie){
        Long id = concurrentHashMap.size() + 1L;
        movie.setId(id);
        concurrentHashMap.put(id, movie);
        return movie;
    }

    Optional<Movie> findById(Long id){
        return Optional.of(concurrentHashMap.get(id));
    }
}
