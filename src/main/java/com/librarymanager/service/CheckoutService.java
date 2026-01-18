package com.librarymanager.service;

import com.librarymanager.model.Checkout;
import com.librarymanager.repository.CheckoutRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class CheckoutService {
    @Autowired
    private CheckoutRepository checkoutRepository;

    public List<Checkout> getAllCheckouts() {
        return checkoutRepository.findAll();
    }

    public Checkout getCheckoutById(Long id) {
        return checkoutRepository.findById(id).orElse(null);
    }

    @Transactional
    public Checkout saveCheckout(Checkout checkout) {
        if (checkout.getDeadline().isBefore(checkout.getDateTaken())) {
            throw new IllegalArgumentException("Deadline must be after dateTaken");
        }

        if (existsActiveByBookId(checkout.getBook().getId())) {
            throw new IllegalArgumentException("Book is already taken");
        }

        return checkoutRepository.save(checkout);
    }

    public void deleteCheckout(Long id) {
        checkoutRepository.deleteById(id);
    }

    public List<Checkout> findByClientId(Long clientId) {
        return checkoutRepository.findByClientId(clientId.longValue());
    }

    public List<Checkout> findActiveByBookId(Long bookId) {
        return checkoutRepository.findActiveByBookId(bookId.longValue());
    }

    public boolean existsActiveByBookId(Long bookId) {
        return checkoutRepository.existsActiveByBookId(bookId.longValue());
    }

    public List<Checkout> findOverdue(LocalDate date) {
        return checkoutRepository.findOverdue(date);
    }
}
