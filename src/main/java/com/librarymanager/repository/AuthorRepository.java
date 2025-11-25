package com.librarymanager.repository;

import com.librarymanager.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByFullNameContainingIgnoreCase(String fullName);

    List<Author> findByFullNameStartingWith(String start);

    List<Author> findByFullNameEndingWith(String end);
}
