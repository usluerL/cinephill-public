package com.byusluer.movierest.controller;


import com.byusluer.movierest.model.dto.request.TVShowRequestDto;
import com.byusluer.movierest.model.dto.response.TvShowResult;
import com.byusluer.movierest.service.tv.TVShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/private/tv")
@RequiredArgsConstructor
public class TVShowController {

    private final TVShowService tvShowService;


    @PostMapping("/search")
    public ResponseEntity<TvShowResult> getTvShowsByCategories(@RequestBody TVShowRequestDto requestDto) {
        TvShowResult tvShowResult = tvShowService.searchTVShows(requestDto);

      return   ResponseEntity.ok(tvShowResult);

    }

}
