package com.byusluer.movierest.controller;

import com.byusluer.movierest.exception.MovieApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/private")
public class SpecialContentController {


    @GetMapping
    public ResponseEntity<String> testPrivateContent(){
        String content = "SpecialMovieContent";
        return ResponseEntity.ok(content);
    }




}
