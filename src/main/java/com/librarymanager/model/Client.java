package com.librarymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 32)
    private String firstName;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 32)
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String phoneNumber;
}
