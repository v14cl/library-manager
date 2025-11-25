package com.librarymanager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "language")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long languageId;

    @Column(nullable = false, unique = true)
    private String name;
}
