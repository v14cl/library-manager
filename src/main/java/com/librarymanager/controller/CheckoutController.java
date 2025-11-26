package com.librarymanager.controller;

import com.librarymanager.model.Checkout;
import com.librarymanager.service.CheckoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/checkouts")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping
    public List<Checkout> getAllCheckouts() {
        return checkoutService.getAllCheckouts();
    }

    @GetMapping("/{id}")
    public Checkout getCheckout(@PathVariable Long id) {
        return checkoutService.getCheckoutById(id);
    }

    @PostMapping
    public Checkout createCheckout(@Valid @RequestBody Checkout checkout) {
        return checkoutService.saveCheckout(checkout);
    }

    @PutMapping("/{id}")
    public Checkout updateCheckout(@PathVariable Long id, @Valid @RequestBody Checkout checkout) {
        checkout.setId(id);
        return checkoutService.saveCheckout(checkout);
    }

    @DeleteMapping("/{id}")
    public void deleteCheckout(@PathVariable Long id) {
        checkoutService.deleteCheckout(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Checkout> byClient(@PathVariable Long clientId) {
        return checkoutService.findByClientId(clientId);
    }

    @GetMapping("/book/{bookId}/active")
    public List<Checkout> active(@PathVariable Long bookId) {
        return checkoutService.findActiveByBookId(bookId);
    }

    @GetMapping("/overdue")
    public List<Checkout> overdue(@RequestParam String date) {
        return checkoutService.findOverdue(LocalDate.parse(date));
    }
}
