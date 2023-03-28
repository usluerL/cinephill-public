package com.byusluer.movierest.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CastDto {
    @JsonProperty("id")
    private int castId;
    private String name;
    private String character;

}
