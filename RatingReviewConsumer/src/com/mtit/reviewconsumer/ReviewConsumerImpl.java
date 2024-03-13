package com.mtit.reviewconsumer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
            System.out.println("1. Add Review & Rating");
            System.out.println("2. View All Reviews & Ratings");
            System.out.println("3. View a Review by ID");
            System.out.println("4. Update Review");
            System.out.println("5. Delete Review");
            System.out.println("6. View Recommended Books");
            System.out.println("7. Return to Main Menu");
            System.out.println("8. Exit");
            
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
			case 1: 
				System.out.println("\n=== Add Review and Rating ===");
				
            	System.out.print("Enter Member ID: ");
            	int memberId = sc.nextInt();
            	System.out.println();
            	
            	System.out.print("Enter Book ID: ");
            	int bookId = sc.nextInt();
            	System.out.println();
            	
            	System.out.print("Enter Book Name: ");
            	String bookName = sc.next();
            	System.out.println();
            	
            	System.out.print("Enter Rating (0-10): ");
            	int ratingValue = sc.nextInt();
            	System.out.println();
            	
            	while(ratingValue < 0 || ratingValue > 10) {
            		System.out.println("Please provide a rating value between 0 and 10");
            		System.out.print("Enter Rating (0-10): ");
                	ratingValue = sc.nextInt();
                	System.out.println();
            	}
            	
            	System.out.print("Enter Review: ");
            	String reviewText = sc.next();
            	System.out.println();
            	
            	Date createdAt = null;
            	Date modifiedAt = null;
            	
            	Review review = new Review(1, memberId, bookId, bookName, ratingValue, reviewText, createdAt, modifiedAt);
            	reviewProducer.addReview(review);
            	
            	System.out.println("Review Added Successfully");
            	
            	break;	
            case 2:
            	System.out.println("\n=== View All Reviews ===");
            	
            	List<Review> reviewList = reviewProducer.getAllReviews();
            	
        	    if (reviewList.isEmpty()) {
        	        System.out.println("No Reviews Found");
        	    } else {
        	        System.out.println("+-----------------+-----------------+-----------------+----------------------+-----------------+----------------------+-----------------+-----------------+");
        	        System.out.printf("| %-15s | %-15s | %-15s | %-20s | %-15s | %-20s | %-15s | %-15s |\n",
        	                "Review ID", "Member ID", "Book ID", "Book Name", "Rating", "Review", "Created Date", "Modified Date");
        	        System.out.println("+-----------------+-----------------+-----------------+----------------------+-----------------+----------------------+-----------------+-----------------+");
        	        
        	        for (Review r : reviewList) {
        	            System.out.printf("| %-15s | %-15s | %-15s | %-20s | %-15s | %-20s | %-15s | %-15s |\n",
        	                    r.getReviewId(), r.getUserId(), r.getBookId(), r.getBookName(),
        	                    r.getRating(), r.getReviewText(), r.getCreatedAt(), r.getLastModifiedAt());
        	        }
        	        
        	        System.out.println("+-----------------+-----------------+-----------------+----------------------+-----------------+----------------------+-----------------+-----------------+");
        	    }
        	    
        	    break;
            case 3:
            	System.out.println("\n=== View Review by ID ===");
            	
            	System.out.print("Enter Review ID to retrieve: ");
                int reviewIdToRetrieve = sc.nextInt();
                System.out.println();
                
                Review retrievedReview = reviewProducer.retrieveReviewById(reviewIdToRetrieve);
                
                if(retrievedReview != null) {                	
                	System.out.println("+-----------------+-----------------+-----------------+----------------------+-----------------+----------------------+-----------------+");
        	        System.out.printf("| %-15s | %-15s | %-15s | %-20s | %-15s | %-20s | %-15s |\n",
        	                "Review ID", "Member ID", "Book ID", "Book Name", "Rating", "Review", "Created Date", "Modified Date");
        	        System.out.println("+-----------------+-----------------+-----------------+----------------------+-----------------+----------------------+-----------------+");
        	        
        	        System.out.printf("| %-15s | %-15s | %-15s | %-20s | %-15s | %-20s | %-15s |\n",
        	         		retrievedReview.getReviewId(), retrievedReview.getUserId(), retrievedReview.getBookId(), retrievedReview.getBookName(),
        	        		retrievedReview.getRating(), retrievedReview.getReviewText(), retrievedReview.getCreatedAt(), retrievedReview.getLastModifiedAt());
        	        
        	        System.out.println("+-----------------+-----------------+-----------------+----------------------+-----------------+----------------------+-----------------+");                
        	    } else {
                	System.out.println("Review ID " + reviewIdToRetrieve + " Not Found!");
                }
           
        	    break;
            case 4:
            	System.out.println("\n=== Update Review ===");
            	
            	System.out.print("Enter Review ID to update: ");
                int reviewIdToUpdate = sc.nextInt();
                System.out.println();
                
                Review retrievedReviewToUpdate = reviewProducer.retrieveReviewById(reviewIdToUpdate);
                
                if(retrievedReviewToUpdate != null) {    
                	System.out.println("Enter New Rating: ");
                	int newRating = sc.nextInt();
                	
                	System.out.println("Enter New Review: ");
                	String newReview = sc.next();
                	
                	Date updatedAt = null;
                	
                	retrievedReviewToUpdate.setRating(newRating);
                	retrievedReviewToUpdate.setReviewText(newReview);
                	retrievedReviewToUpdate.setLastModifiedAt(updatedAt);
                	
                	Review updatedReview = reviewProducer.updateReview(retrievedReviewToUpdate);
                	
                	System.out.println("+-----------------+-----------------+-----------------+----------------------+-----------------+----------------------+-----------------+");
        	        System.out.printf("| %-15s | %-15s | %-15s | %-20s | %-15s | %-20s | %-15s |\n",
        	                "Review ID", "Member ID", "Book ID", "Book Name", "Rating", "Review", "Created Date", "Modified Date");
        	        System.out.println("+-----------------+-----------------+-----------------+----------------------+-----------------+----------------------+-----------------+");
        	        
        	        System.out.printf("| %-15s | %-15s | %-15s | %-20s | %-15s | %-20s | %-15s |\n",
        	        		updatedReview.getReviewId(), updatedReview.getUserId(), updatedReview.getBookId(), updatedReview.getBookName(),
        	        		updatedReview.getRating(), updatedReview.getReviewText(), updatedReview.getCreatedAt(), updatedReview.getLastModifiedAt());
        	        
        	        System.out.println("+-----------------+-----------------+-----------------+----------------------+-----------------+----------------------+-----------------+");                
        	    } else {
                	System.out.println("Review ID " + retrievedReviewToUpdate + " Not Found!");
                }
           
        	    break;
            case 5:
            	System.out.println("\n=== Delete Review ===");
            	
                System.out.print("Enter Review ID to delete: ");
                int reviewId = sc.nextInt();
                sc.nextLine(); // consume the newline character

                // Call the method in ReviewProducer to delete the review
                reviewProducer.deleteReview(reviewId);
                System.out.println("Review Deleted Successfully");

                break;
            case 6:
            	System.out.println("\n=== View Recommended Books ===");
            	
            	List<Review> reviews = reviewProducer.getAllReviews();
            	
            	List<Map.Entry<Integer, Double>> recommendedList = reviewProducer.getRecommendedBooks();
            	
            	if (recommendedList.isEmpty()) {
        	        System.out.println("No Recommended Books Found!");
        	    } else {
        	    	System.out.println("+-----------------+----------------------+----------------------+");
        	        System.out.printf("| %-15s | %-20s | %-20s |\n", "Book ID", "Book Name", "Overall Rating");
        	        System.out.println("+-----------------+----------------------+----------------------+");
        	        
        	        for (Map.Entry<Integer, Double> entry : recommendedList) {
        	            int book_id = entry.getKey();
        	            
        	            String book_name = null;
        	            for(Review r : reviews) {
        	            	if(r.getBookId() == book_id) {
        	            		book_name = r.getBookName();
        	            	}
        	            }
        	            
        	            double overallRating = entry.getValue();
        	            
        	            System.out.printf("| %-15s | %-20s | %-20s |\n", book_id, book_name, overallRating);
        	        }
        	        System.out.println("+-----------------+----------------------+----------------------+");
        	    }
                break;
            case 7:
            	System.out.println("Navigating to the Main Menu");
                break;
            case 8:
           	 	System.out.println("Exiting...");
           	 	System.exit(0);
                break;
			default:
				System.out.println("Invalid Choice. Please Enter a Valid Option.");
                break;			
            }
		}
	}
}
