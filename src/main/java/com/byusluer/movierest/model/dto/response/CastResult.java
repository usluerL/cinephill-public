package com.byusluer.movierest.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CastResult {
    @JsonProperty("id")
    private Long creditId;
    @JsonProperty("cast")
    List<CastDto> cast;
}
