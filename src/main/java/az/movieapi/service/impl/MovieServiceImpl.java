package az.movieapi.service.impl;

import az.movieapi.dto.request.MovieRequest;
import az.movieapi.dto.response.MovieResponse;
import az.movieapi.entity.Movie;
import az.movieapi.exception.CustomException;
import az.movieapi.repository.MovieRepository;
import az.movieapi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<MovieResponse> getMovies(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(movie -> modelMapper.map(movie, MovieResponse.class));
    }

    @Override
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new CustomException("Movie not found with id" + id));
        return modelMapper.map(movie, MovieResponse.class);
    }


    @Override
    public MovieResponse createMovie(MovieRequest movieRequest) {
        Movie movie = modelMapper.map(movieRequest, Movie.class);
        return modelMapper.map(movieRepository.save(movie), MovieResponse.class);
    }

    @Override
    public MovieResponse updateMovie(Long id, MovieRequest movieRequest) {
        movieRepository.findById(id).orElseThrow(()-> new CustomException("Movie not found with id" + id));
        Movie responseMovie = modelMapper.map(movieRequest, Movie.class);
        responseMovie.setId(id);
        return modelMapper.map(movieRepository.save(responseMovie), MovieResponse.class);
    }

    @Override
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new CustomException("Movie not found with id" + id));
        movieRepository.delete(movie);
    }
}
