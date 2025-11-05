package com.librarymanager.model;

public class GenreBook {
    private int bookId;
    private int genreId;

    public GenreBook() {
    }

    public GenreBook(int bookId, int genreId) {
        this.bookId = bookId;
        this.genreId = genreId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "GenreBook{" + "bookId=" + bookId + ", genreId=" + genreId + '}';
    }
}
