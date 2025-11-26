package com.librarymanager.service;

import com.librarymanager.model.Author;
import com.librarymanager.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public List<Author> findByFirstName(String firstName) {
        return authorRepository.findByFirstName(firstName);
    }

    public List<Author> findByLastName(String lastName) {
        return authorRepository.findByLastName(lastName);
    }

    public List<Author> findByFirstAndLastName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
