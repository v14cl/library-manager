package com.librarymanager;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagerApplication {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(LibraryManagerApplication.class, args);
    }
}