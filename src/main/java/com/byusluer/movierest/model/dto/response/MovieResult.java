package com.byusluer.movierest.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResult {
    private String page;
    @JsonProperty("results")
    private List<MovieDto> movies;


    @JsonProperty("movies")
    public List<MovieDto> getMovies() {
        return movies;
    }
}
