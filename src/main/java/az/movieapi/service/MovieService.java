package az.movieapi.service;

import az.movieapi.dto.request.MovieRequest;
import az.movieapi.dto.response.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {

    Page<MovieResponse> getMovies(Pageable pageable);

    MovieResponse getMovieById(Long id);

    MovieResponse createMovie(MovieRequest movieRequest);

    MovieResponse updateMovie(Long id, MovieRequest movieRequest);

    void deleteMovie(Long id);
}
