package com.librarymanager.service.validator;

import com.librarymanager.model.*;
import com.librarymanager.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public void validateForSave(Book book) {

        Long publisherId = book.getPublisher().getId();
        if (publisherId == null || !publisherRepository.existsById(publisherId)) {
            throw new EntityNotFoundException("Publisher not found");
        }

        validateAuthors(book.getAuthors());
        validateGenres(book.getGenres());
    }

    private void validateAuthors(Set<Author> authors) {
        if (authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException("Book must have author");
        }

        for (Author author : authors) {
            Long authorId = author.getId();
            if (authorId == null || !authorRepository.existsById(authorId)) {
                throw new EntityNotFoundException("Author(id: " + authorId + ") not found");
            }
        }
    }

    private void validateGenres(Set<Genre> genres) {
        if (genres == null || genres.isEmpty()) {
            throw new IllegalArgumentException("Book must have genre");
        }

        for (Genre genre : genres) {
            Long genreId = genre.getId();
            if (genreId == null || !genreRepository.existsById(genreId)) {
                throw new EntityNotFoundException("Genre(id: " + genreId + ")  not found");
            }
        }
    }
}
