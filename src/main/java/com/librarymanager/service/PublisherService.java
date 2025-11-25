package com.librarymanager.service;

import com.librarymanager.model.Publisher;
import com.librarymanager.repository.PublisherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    public List<Publisher> findByNameContaining(String name) {
        return publisherRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Publisher> findByNameStartingWith(String prefix) {
        return publisherRepository.findByNameStartingWith(prefix);
    }

    public List<Publisher> findByNameEndingWith(String suffix) {
        return publisherRepository.findByNameEndingWith(suffix);
    }
}
