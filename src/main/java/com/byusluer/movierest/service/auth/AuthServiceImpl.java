package com.byusluer.movierest.service.auth;

import com.byusluer.movierest.entity.Role;
import com.byusluer.movierest.entity.User;
import com.byusluer.movierest.exception.MovieApiException;
import com.byusluer.movierest.model.dto.request.LoginDto;
import com.byusluer.movierest.model.dto.request.RegisterDto;
import com.byusluer.movierest.repository.RoleRepository;
import com.byusluer.movierest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;
    private final JwtTokenService jwtTokenService;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenService.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new MovieApiException("Username is already exists", HttpStatus.BAD_REQUEST);
        }

        User user = mapToEntity(registerDto);
        user.setPublicId(UUID.randomUUID().toString());
        user.setPassword(encoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role basic = roleRepository.findByRollName("ROLE_BASIC").get();
        roles.add(basic);
        user.setRoles(roles);

        userRepository.save(user);
        return "Registration Success";

    }


    public User mapToEntity(RegisterDto registerDto) {
        return modelMapper.map(registerDto, User.class);
    }
}
