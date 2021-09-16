package taller.com.co.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taller.com.co.movies.entity.Movie;

public interface MovieRepository  extends JpaRepository<Movie,Long> {}
