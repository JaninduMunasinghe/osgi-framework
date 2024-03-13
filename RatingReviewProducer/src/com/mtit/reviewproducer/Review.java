package com.mtit.reviewproducer;

import java.sql.Date;

public class Review {
	
	private int reviewId; 
	private int userId; 
    private int bookId;
    private String bookName;
    private int rating; 
    private String reviewText;
    private Date createdAt;
    private Date lastModifiedAt;
	
	public Review(int reviewId, int userId, int bookId, String bookName, int rating, String reviewText, Date createdAt,
			Date lastModifiedAt) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.bookId = bookId;
		this.bookName = bookName;
		this.rating = rating;
		this.reviewText = reviewText;
		this.createdAt = createdAt;
		this.lastModifiedAt = lastModifiedAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
    
    

}
