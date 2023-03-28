package com.byusluer.movierest.service.auth;

import com.byusluer.movierest.model.dto.request.LoginDto;
import com.byusluer.movierest.model.dto.request.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);

}
