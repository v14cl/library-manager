package com.librarymanager.repository;

import com.librarymanager.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    
    List<Checkout> findByClientId(int clientId);
    
    List<Checkout> findByBookIdAndDateReturnedIsNull(int bookId);
    
    List<Checkout> findByDeadlineBeforeAndDateReturnedIsNull(LocalDate date);
}
