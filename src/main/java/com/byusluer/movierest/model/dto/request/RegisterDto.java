package com.byusluer.movierest.model.dto.request;

import lombok.Data;

@Data
public class RegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    //todo confirm password can be implemented.
    private String password;


}
