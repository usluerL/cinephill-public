package com.byusluer.movierest.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
@Getter
@Setter
public class UnauthorizedException extends RuntimeException {


    public UnauthorizedException(String message) {
        super(message);

    }
}
