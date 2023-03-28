package com.byusluer.movierest.service.movie;

import com.byusluer.movierest.model.dto.response.CastResult;
import com.byusluer.movierest.model.dto.response.MovieDto;
import com.byusluer.movierest.model.dto.response.MovieResult;
import com.byusluer.movierest.model.dto.response.TVShowResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {

    MovieDto getMovie(Long movieId);

    MovieResult getPopularMovies();

    MovieResult searchMovies(String query);

    CastResult getCredits(@PathVariable("movie_id") long movieId);

    String getCharacterName(String movieName, String actName);

}
