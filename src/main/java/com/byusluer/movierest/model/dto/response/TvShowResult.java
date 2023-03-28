package com.byusluer.movierest.model.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvShowResult {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("total_results")
    private Integer totalResults;

    @JsonProperty("total_pages")
    private Integer totalPages;


    @JsonProperty("results")
    private List<TVShowResponseDto> results;

}
