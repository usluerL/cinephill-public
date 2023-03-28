package com.byusluer.movierest.service.tv;

import com.byusluer.movierest.model.dto.request.TVShowRequestDto;
import com.byusluer.movierest.model.dto.response.TvShowResult;

public interface TVShowService {
    TvShowResult searchTVShows(TVShowRequestDto tvShowRequest);
}
