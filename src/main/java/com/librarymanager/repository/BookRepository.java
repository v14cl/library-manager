package com.librarymanager.repository;

import com.librarymanager.model.Book;
import com.librarymanager.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByTitleContainingIgnoreCase(String title);
   
    List<Book> findByPublisher(String publisher);
    
    List<Book> findByLanguage(Language language);
}
