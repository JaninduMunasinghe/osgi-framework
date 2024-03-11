package library.model;

public class Task {
    private String bookName;
    private long borrowingDate; 
    private long returnDate; 

    public Task(String bookName, long borrowingDate, long returnDate) {
        this.bookName = bookName;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }

    public String getBookName() {
        return bookName;
    }

    public long getBorrowingDate() {
        return borrowingDate;
    }

    public long getReturnDate() {
        return returnDate;
    }
}
