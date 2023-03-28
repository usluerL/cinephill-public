package com.byusluer.movierest.security;

import com.byusluer.movierest.entity.User;
import com.byusluer.movierest.exception.ResourceNotFoundException;
import com.byusluer.movierest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Email", email));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getUserRoles(user));

    }

    private Set<SimpleGrantedAuthority> getUserRoles(User user) {
        return user.getRoles().stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getRollName()))
                .collect(Collectors.toSet());
    }
}
