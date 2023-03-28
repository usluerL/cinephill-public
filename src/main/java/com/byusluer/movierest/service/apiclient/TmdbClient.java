package com.byusluer.movierest.service.apiclient;


import com.byusluer.movierest.model.dto.response.CastResult;
import com.byusluer.movierest.model.dto.response.MovieDto;
import com.byusluer.movierest.model.dto.response.MovieResult;
import com.byusluer.movierest.model.dto.response.TvShowResult;
import feign.FeignException;
import feign.Headers;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "tmdbClient", url = "${tmdb.api.url}")
public interface TmdbClient {
    @GetMapping("/movie/{movieId}")
    MovieDto getMovie(@PathVariable("movieId") Long movieId) throws FeignException;

    @GetMapping("/movie/popular")
    MovieResult getPopularMovies();

    @GetMapping("/search/movie")
    MovieResult searchMovies(@RequestParam("query") String query);

    @GetMapping("/movie/{movie_id}/credits")
    CastResult getCredits(@PathVariable("movie_id") long movieId);

    @GetMapping("/discover/tv")
    TvShowResult discoverTvShows(@SpringQueryMap  Map<String, Object> queryParams);
}


