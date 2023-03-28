package com.byusluer.movierest.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MovieApiException extends RuntimeException {

    private HttpStatus status;


    public MovieApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
