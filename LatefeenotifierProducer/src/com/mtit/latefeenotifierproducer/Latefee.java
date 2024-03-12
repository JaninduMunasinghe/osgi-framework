package com.mtit.latefeenotifierproducer;

public class Latefee {
	
	private int memberID;
	private String bookName;
	private String bookType;
	private String isbn;
	private String borrowDate;
	private String returnDate;
	private float latefee;
	
	
	public Latefee(int memberID, String bookName, String bookType, String isbn, String borrowDate, String returnDate,
			float latefee) {
		super();
		this.memberID = memberID;
		this.bookName = bookName;
		this.bookType = bookType;
		this.isbn = isbn;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.latefee = latefee;
	}


	public int getMemberID() {
		return memberID;
	}


	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getBookType() {
		return bookType;
	}


	public void setBookType(String bookType) {
		this.bookType = bookType;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getBorrowDate() {
		return borrowDate;
	}


	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}


	public String getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}


	public float getLatefee() {
		return latefee;
	}


	public void setLatefee(float latefee) {
		this.latefee = latefee;
	}
	
	
	
	
	

}
