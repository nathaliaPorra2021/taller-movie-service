package taller.com.co.movies.service;

import taller.com.co.movies.entity.Movie;

import java.util.List;

public interface MovieService {

    public Movie createMovie(Movie movie);

    public List<Movie> listAllMovies();

    public Movie getMovieById(Long id);

    public void deleteMovie(Long id);

}
