package com.librarymanager.model;

public class AuthorBook {
    private int bookId;
    private int authorId;

    public AuthorBook() {
    }

    public AuthorBook(int bookId, int authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "AuthorBook{" + "bookId=" + bookId + ", authorId=" + authorId + '}';
    }
}
