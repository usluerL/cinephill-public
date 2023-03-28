package com.byusluer.movierest.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class TVShowResponseDto {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("genre_ids")
    private List<Long> genreIds;

    @JsonProperty("vote_average")
    private double voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("popularity")
    private double popularity;

    @JsonProperty("first_air_date")
    private String firstAirDate;

}
