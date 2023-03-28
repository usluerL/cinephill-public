package com.byusluer.movierest.exception;

import com.byusluer.movierest.constants.AppConstants;
import com.byusluer.movierest.model.error.ErrorDetails;
import feign.FeignException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.util.Arrays;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorDetails> HandleFeignException(FeignException exception,
                                                                        WebRequest webRequest){
        exception.printStackTrace();
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                AppConstants.FEIGN_CLIENT_MESSAGE,
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorDetails> handleUnauthorizedException(UnauthorizedException exception,
                                                                    WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        log.info("called " + getClass().getSimpleName());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {SignatureException.class})
    public ResponseEntity<Object> handleJwtException(SignatureException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        log.info("called " + getClass().getSimpleName());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(MovieApiException.class)
    public ResponseEntity<ErrorDetails> handleMovieApiException(MovieApiException exception,
                                                               WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }




}
