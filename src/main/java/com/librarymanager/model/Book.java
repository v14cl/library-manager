package com.librarymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 64)
    private String title;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Language language;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    @NotNull
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name = "author_book", 
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    @ManyToMany
    @JoinTable(name = "genre_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;
}
