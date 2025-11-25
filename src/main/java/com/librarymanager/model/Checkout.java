package com.librarymanager.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "checkout")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkoutId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private LocalDate dateTaken;

    private LocalDate deadline;

    private LocalDate dateReturned;
}
