package com.librarymanager.model;

public class Book {
    private int bookId;
    private String title;
    private Language language;
    private String publisher;

    public Book() {}

    public Book(int bookId, String title, Language language, String publisher) {
        this.bookId = bookId;
        this.title = title;
        this.language = language;
        this.publisher = publisher;
    }

    public Book(String title, Language language, String publisher) {
        this.title = title;
        this.language = language;
        this.publisher = publisher;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", language=" + language +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
