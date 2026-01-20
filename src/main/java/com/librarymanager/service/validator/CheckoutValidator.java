package com.librarymanager.service.validator;

import com.librarymanager.model.*;
import com.librarymanager.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CheckoutValidator {

    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private final CheckoutRepository checkoutRepository;

    public void validateForCreate(Checkout checkout) {

        Client client = checkout.getClient();
        if (client == null || client.getId() == null || !clientRepository.existsById(client.getId())) {
            throw new EntityNotFoundException("Client not found");
        }

        Book book = checkout.getBook();
        if (book == null || book.getId() == null || !bookRepository.existsById(book.getId())) {
            throw new EntityNotFoundException("Book not found ");
        }

        boolean activeExists = checkoutRepository.existsActiveByBookId(book.getId());
        if (activeExists) {
            throw new IllegalStateException("Book(id: " + book.getId() + ") already checked out");
        }

        LocalDate dateTaken = checkout.getDateTaken();
        LocalDate deadline = checkout.getDeadline();
        LocalDate dateReturned = checkout.getDateReturned();

        if (dateTaken == null) {
            throw new IllegalArgumentException("dateTaken must be not null");
        }

        if (deadline == null) {
            throw new IllegalArgumentException("deadline must be not null");
        }

        if (dateReturned != null && dateReturned.isBefore(dateTaken)) {
            throw new IllegalArgumentException("dateReturned cant be before dateTaken");
        }

        if (deadline.isBefore(dateTaken)) {
            throw new IllegalArgumentException("deadline cant be before dateTaken");
        }
    }

    public void validateForReturn(Checkout checkout) {
        if (checkout.getDateReturned() != null) {
            throw new IllegalStateException("Book is already returned");
        }
        
        if (checkout.getDateTaken() == null) {
            throw new IllegalArgumentException("Checkout date is missing");
        }
        
        if (checkout.getDeadline() == null) {
            throw new IllegalArgumentException("Deadline is missing");
        }
    }
}
