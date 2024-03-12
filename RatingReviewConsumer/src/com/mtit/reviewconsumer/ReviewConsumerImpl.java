package com.mtit.reviewconsumer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;

import com.mtit.reviewproducer.Review;
import com.mtit.reviewproducer.ReviewProducer;

public class ReviewConsumerImpl implements ReviewConsumer{
	
	ReviewProducer reviewProducer;

	public ReviewConsumerImpl(ReviewProducer reviewProducer) {
		this.reviewProducer = reviewProducer;
	}

	@Override
	public void start() {
		interactWithUser();
	}
	
	private void interactWithUser() {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("\n=== Review Dashboard ===");
            System.out.println("1. Add Review and Rating");
            System.out.println("2. View All Reviews");
            System.out.println("3. Delete Review");
            System.out.println("4. Return to Main Menu");
            System.out.println("5. Exit");
            
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
			case 1: 
				System.out.println("\n=== Add Review and Rating ===");
				
            	System.out.print("Enter Member ID: ");
            	int memberId = sc.nextInt();
            	
            	System.out.print("Enter Book ID: ");
            	int bookId = sc.nextInt();
            	
            	System.out.print("Enter Rating (0-10): ");
            	int ratingValue = sc.nextInt();
            	
            	System.out.print("Enter Review: ");
            	String reviewText = sc.next();
            	
            	long millis = System.currentTimeMillis();  
                java.sql.Date date = new java.sql.Date(millis);
            	Date createdAt = date;
            	
            	Date modifiedAt = null;
            	
            	Review review = new Review(1, memberId, bookId, ratingValue, reviewText, createdAt, modifiedAt);
            	reviewProducer.addReview(review);
            	System.out.println("Review Added Successfully");
            
            	break;
            case 2:
            	System.out.println("\n=== View All Reviews ===");
            	List<Review> reviewList = reviewProducer.getAllReviews();
        	    if (reviewList.isEmpty()) {
        	        System.out.println("No Reviews Found");
        	    } else {
        	        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
        	        System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s | %-15s |\n",
        	                "Review ID", "Member ID", "Book ID", "Rating", "Review", "Created Date", "Modified Date");
        	        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
        	        for (Review r : reviewList) {
        	            System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s | %-15s |\n",
        	                    r.getReviewId(), r.getUserId(), r.getBookId(),
        	                    r.getRating(), r.getReviewText(), r.getCreatedAt(), r.getLastModifiedAt());
        	        }
        	        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
        	    }
           
        	    break;
            case 3:
            	System.out.println("\n=== Delete Review ===");
                System.out.print("Enter Review ID to delete: ");
                int reviewId = sc.nextInt();
                sc.nextLine(); // consume the newline character

                // Call the method in ReviewProducer to delete the review
                reviewProducer.deleteReview(reviewId);
                System.out.println("Review Deleted Successfully");

                break;
            case 4:
            	System.out.println("Navigating to the Main Menu");
                break;
            case 5:
           	 	System.out.println("Exiting...");
                break;
			default:
				System.out.println("Invalid Choice. Please Enter a Valid Option.");
                break;			}
		}
	}

}
