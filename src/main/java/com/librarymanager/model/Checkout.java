package com.librarymanager.model;

import java.time.LocalDate;

public class Checkout {
    private int checkoutId;
    private int clientId;
    private int bookId;
    private LocalDate dateTaken;
    private LocalDate deadline;
    private LocalDate dateReturned;

    public Checkout() {}

    public Checkout(int checkoutId, int clientId, int bookId, LocalDate dateTaken, LocalDate deadline,
            LocalDate dateReturned) {
        this.checkoutId = checkoutId;
        this.clientId = clientId;
        this.bookId = bookId;
        this.dateTaken = dateTaken;
        this.deadline = deadline;
        this.dateReturned = dateReturned;
    }

    public Checkout(int clientId, int bookId, LocalDate dateTaken, LocalDate deadline) {
        this.clientId = clientId;
        this.bookId = bookId;
        this.dateTaken = dateTaken;
        this.deadline = deadline;
    }

    public int getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(int checkoutId) {
        this.checkoutId = checkoutId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDate dateTaken) {
        this.dateTaken = dateTaken;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    @Override
    public String toString() {
        return "Checkout{" + "checkoutId=" + checkoutId + ", clientId=" + clientId + ", bookId=" + bookId
                + ", dateTaken=" + dateTaken + ", deadline=" + deadline + ", dateReturned=" + dateReturned + '}';
    }
}