package com.librarymanager.repository;

import com.librarymanager.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    List<Checkout> findByClientId(Long clientId);

    @Query("SELECT c FROM Checkout c WHERE c.book.id = :bookId AND c.dateReturned IS NULL")
    List<Checkout> findActiveByBookId(Long bookId);

    @Query("SELECT COUNT(c) > 0 FROM Checkout c WHERE c.book.id = :bookId AND c.dateReturned IS NULL")
    boolean existsActiveByBookId(Long bookId);

    @Query("SELECT c FROM Checkout c WHERE c.deadline < :date AND c.dateReturned IS NULL")
    List<Checkout> findOverdue(LocalDate date);
}