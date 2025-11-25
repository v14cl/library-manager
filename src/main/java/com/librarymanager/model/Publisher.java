package com.librarymanager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "publisher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherId;

    @Column(nullable = false, unique = true)
    private String name;
}
