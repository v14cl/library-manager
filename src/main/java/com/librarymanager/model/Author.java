package com.librarymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 32)
    private String firstName;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 32)
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    @ToString.Exclude
    private Set<Book> books;
}
