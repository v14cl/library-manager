package com.librarymanager.service;

import com.librarymanager.model.Checkout;
import com.librarymanager.repository.CheckoutRepository;
import com.librarymanager.service.validator.CheckoutValidator;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutService {
    
    private final CheckoutRepository checkoutRepository;
    private final CheckoutValidator checkoutValidator;

    public List<Checkout> getAllCheckouts() {
        return checkoutRepository.findAll();
    }

    public Checkout getCheckoutById(Long id) {
        return checkoutRepository.findById(id).orElse(null);
    }

    @Transactional
    public Checkout saveCheckout(Checkout checkout) {
        checkoutValidator.validateForCreate(checkout);
        return checkoutRepository.save(checkout);
    }

    public Checkout returnBook(Long checkoutId) {
        Checkout checkout = checkoutRepository.findById(checkoutId)
        .orElseThrow(() -> new EntityNotFoundException("Checkout not found"));

        checkoutValidator.validateForReturn(checkout);
        
        checkout.setDateReturned(LocalDate.now());
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
