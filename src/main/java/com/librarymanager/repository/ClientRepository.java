package com.librarymanager.repository;

import com.librarymanager.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByPhoneNumber(String phoneNumber);

    List<Client> findByFirstName(String firstName);

    List<Client> findByLastName(String lastName);
}
