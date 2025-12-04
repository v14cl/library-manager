package com.librarymanager.service;

import com.librarymanager.model.Checkout;
import com.librarymanager.repository.CheckoutRepository;

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

    public Checkout saveCheckout(Checkout checkout) {
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

    public List<Checkout> findOverdue(LocalDate date) {
        return checkoutRepository.findOverdue(date);
    }
}
