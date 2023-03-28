package com.byusluer.movierest.service.movie;

import com.byusluer.movierest.entity.Movie;
import com.byusluer.movierest.exception.ResourceNotFoundException;
import com.byusluer.movierest.model.dto.response.*;
import com.byusluer.movierest.repository.MovieRepository;
import com.byusluer.movierest.service.apiclient.TmdbClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TmdbMovieService implements MovieService {


    private final TmdbClient tmdbClient;
    private final MovieRepository movieRepository;
    private final ModelMapper mapper;

    @Override
    public MovieDto getMovie(Long movieId) {
        return tmdbClient.getMovie(movieId);

    }

    @Override
    public MovieResult getPopularMovies() {
        return tmdbClient.getPopularMovies();
    }

    @Override
    public MovieResult searchMovies(String query) {
        return tmdbClient.searchMovies(query);
    }

    @Override
    public CastResult getCredits(long movieId) {
        return tmdbClient.getCredits(movieId);
    }


    public String getCharacterName(String movieName, String actName) {
        MovieResult movieResult = findMovieByName(movieName);



        if (movieResult.getMovies().size() == 0) {
            throw new ResourceNotFoundException("Movie", "with name", movieName);
        }

        if (movieResult.getMovies().size() > 1) {
            String charInMovies = findCharInMovies(movieResult, actName);
            if (charInMovies != null) return charInMovies;
            else throw new ResourceNotFoundException("Character", "actor/actress ", actName);
        }

        MovieDto movieDto = movieResult.getMovies().get(0);
     //   movieRepository.save(mapToEntity(movieDto));

        Long movieId = movieDto.getId();
        CastResult castResult = getCredits(movieId);
        if (castResult == null) {
            throw new ResourceNotFoundException("Cast information", "movieId", movieId);
        }

        List<CastDto> actorList = getActorList(actName, castResult);
        if (actorList.size() == 0)
            throw new ResourceNotFoundException("Character", "actor/actress ", actName);


        return actorList.get(0).getCharacter();

    }


    private List<CastDto> getActorList(String actName, CastResult castResult) {
        return castResult.getCast().stream().filter(cast -> cast.getName()
                .trim().equalsIgnoreCase(actName.trim())).toList();
    }

    private MovieResult findMovieByName(String movieName) {

        List<MovieDto> movieDtos = searchMovies(movieName)
                .getMovies()
                .stream()
                .filter(movie -> movie.getTitle().trim().equalsIgnoreCase(movieName.trim())).toList();
        MovieResult movieResult = new MovieResult();
        movieResult.setMovies(movieDtos);

        System.out.println(movieResult.getMovies().stream().map(MovieDto::getTitle).toList());


        return movieResult;
    }

    private String findCharInMovies(MovieResult movieResult, String actName) {

        List<MovieDto> movies = movieResult.getMovies();
        for (MovieDto movie : movies) {
            CastResult castResult = getCredits(movie.getId());
            List<CastDto> actorList = getActorList(actName, castResult);
            for (CastDto castDto : actorList) {
                if (castDto.getName().trim().equalsIgnoreCase(actName.trim())) {
                    return castDto.getCharacter();
                }
            }
        }

        return null;
    }


    private Movie mapToEntity(MovieDto movieDto){
      return   mapper.map(movieDto,Movie.class);
    }
}
