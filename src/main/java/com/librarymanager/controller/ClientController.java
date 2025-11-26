package com.librarymanager.controller;

import com.librarymanager.model.Client;
import com.librarymanager.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Client createClient(@Valid @RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @Valid @RequestBody Client client) {
        client.setId(id);
        return clientService.saveClient(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/search/phone")
    public List<Client> searchByPhone(@RequestParam String phone) {
        return clientService.findByPhoneNumber(phone);
    }
}
