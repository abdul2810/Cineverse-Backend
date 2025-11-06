package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Movie;

@Repository
public interface MovieBo extends JpaRepository<Movie, Integer> {

    // Get distinct languages
    @Query(value = "SELECT DISTINCT(language) FROM movie", nativeQuery = true)
    List<String> getLanguages();   // ✅ fixed name

    // Get movies by language
    @Query(value = "SELECT * FROM movie WHERE language = :lang", nativeQuery = true)
    List<Movie> getMoviesByLanguage(@Param("lang") String lang);
}
