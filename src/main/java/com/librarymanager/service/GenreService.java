package com.librarymanager.service;

import com.librarymanager.model.Genre;
import com.librarymanager.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    public List<Genre> findByNameContaining(String name) {
        return genreRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Genre> findByNameStartingWith(String prefix) {
        return genreRepository.findByNameStartingWith(prefix);
    }

    public List<Genre> findByNameEndingWith(String suffix) {
        return genreRepository.findByNameEndingWith(suffix);
    }
}
