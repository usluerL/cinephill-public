package com.byusluer.movierest.repository;

import com.byusluer.movierest.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}