package com.librarymanager.model;

public class Author {
    private int authorId;
    private String fullName;

    public Author() {}

    public Author(int authorId, String fullName) {
        this.authorId = authorId;
        this.fullName = fullName;
    }

    public Author(String fullName) {
        this.fullName = fullName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", fullName='" + fullName + '\'' + '}';
    }
}
