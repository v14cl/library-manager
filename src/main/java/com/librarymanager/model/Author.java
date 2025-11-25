package com.librarymanager.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @Column(nullable = false)
    private String fullName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
