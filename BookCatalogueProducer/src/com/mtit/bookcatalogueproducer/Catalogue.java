package com.mtit.bookcatalogueproducer;
import java.time.LocalDateTime;

public class Catalogue {
    
    private int bookID;
    private String bookName;
    private String author;
    private String genre;
    private String isbn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Catalogue(int bookID, String bookName, String author, String genre, String isbn
                     ) {
    	this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.createdAt = LocalDateTime.now(); 
        this.updatedAt = LocalDateTime.now();
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
