package com.mtit.bookcatalogueproducer;
import java.time.LocalDateTime;

public class Catalogue {
    
	private int catalogueID;
    private int bookID;
    private String bookName;
    private String author;
    private String genre;
    private String isbn;
    private int bookCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Catalogue(int catalogueID,int bookID, String bookName, String author, String genre, String isbn, int bookCount
                     ) {
    	this.catalogueID = catalogueID;
    	this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.bookCount= bookCount; 
        this.createdAt = LocalDateTime.now(); 
        this.updatedAt = LocalDateTime.now();
    }

    public int getCatalogueID() {
		return catalogueID;
	}

	public void setCatalogueID(int catalogueID) {
		this.catalogueID = catalogueID;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
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
