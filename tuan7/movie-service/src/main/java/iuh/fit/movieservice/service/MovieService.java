package iuh.fit.movieservice.service;
import iuh.fit.movieservice.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie addMovie(Movie movie);
    void deleteMovie(Long id);
}