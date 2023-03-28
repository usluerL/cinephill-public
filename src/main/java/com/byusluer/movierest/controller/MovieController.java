package com.byusluer.movierest.controller;

import com.byusluer.movierest.model.dto.response.CastResult;
import com.byusluer.movierest.model.dto.response.CharacterResponse;
import com.byusluer.movierest.model.dto.response.MovieDto;
import com.byusluer.movierest.model.dto.response.MovieResult;
import com.byusluer.movierest.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;


    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long movieId) {

        MovieDto movie = movieService.getMovie(movieId);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/popular")
    public ResponseEntity<MovieResult> getPopularMovies() {

        MovieResult popularMovies = movieService.getPopularMovies();
        return ResponseEntity.ok(popularMovies);
    }

    @GetMapping("/search")
    public ResponseEntity<MovieResult> getMovieByQuery(@RequestParam("query") String query) {

        MovieResult movieResult = movieService.searchMovies(query);
        return ResponseEntity.ok(movieResult);
    }

    @GetMapping("/{movie_id}/cast")
    public ResponseEntity<CastResult> getCredits(@PathVariable("movie_id") int movieId) {
        CastResult credits = movieService.getCredits(movieId);
        return ResponseEntity.ok(credits);
    }




    @GetMapping("/character")
    public ResponseEntity<CharacterResponse> getCharacterName(@RequestParam("movieName") String movieName,
                                                              @RequestParam("actName") String actName) {
        String characterName = movieService.getCharacterName(movieName, actName);

       CharacterResponse characterResponse = new CharacterResponse(actName,movieName,characterName);


        return ResponseEntity.ok(characterResponse);
    }

}



