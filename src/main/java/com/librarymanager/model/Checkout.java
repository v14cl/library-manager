package com.librarymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "checkout")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkoutId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @NotNull
    private Client client;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @NotNull
    private Book book;

    @NotNull
    private LocalDate dateTaken;

    @NotNull
    private LocalDate deadline;

    private LocalDate dateReturned;
}
