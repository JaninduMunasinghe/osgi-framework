package com.mtit.AvailabilityUpdaterConsumer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.mtit.BorrowingRecordProducer.Record;
import com.mtit.BorrowingRecordProducer.RecordProducer;

public class RecordConsumerImpl implements RecordConsumer {

	
	RecordProducer recordProducer;
	private String currentUser;
	
	
	public RecordConsumerImpl(RecordProducer recordServicePublish) {
		this.recordProducer = recordServicePublish;
	}
	
	private boolean isAuthenticated = false;
	
	@Override
	public void start() {
		while (!isAuthenticated) {
            isAuthenticated = authenticateUser();
        }

        interactWithUser();
        
		
	}
	
	 private boolean authenticateUser() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter your username: ");
	        String username = scanner.nextLine();

	        System.out.print("Enter your password: ");
	        String password = scanner.nextLine();

	        // Implement a simple authentication logic here
	        // For demonstration purposes, a hardcoded username and password are used
	        if ("admin".equals(username) && "password".equals(password)) {
	            System.out.println("Authentication successful. Welcome, " + username + "!\n");
	            currentUser = username;
	            return true;
	        } else {
	            System.out.println("Authentication failed. Please try again");
	            return false; // Exit the program if authentication fails
	        }
	    }
	
	private void interactWithUser() {
		Scanner scanner = new Scanner(System.in);
		 while (true) {
	            System.out.println("\n=== Borrowing Tracker ===");
	            System.out.println("1. Create Record");
	            System.out.println("2. View All Records");
	            System.out.println("3. Delete Record");
	            System.out.println("4. Return to Main Menu");
	            System.out.println("5. Exit");
	            System.out.println("6. Search Records");

	            System.out.print("\nEnter your choice: ");
	            String input = scanner.nextLine();
	            //scanner.nextLine();
	            
	            if (input.equalsIgnoreCase("exit") || input.equals("5")) {
	                System.out.println("Exiting...");
	                break;
	            }

	            int choice;
	            try {
	                choice = Integer.parseInt(input);
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid input. Please enter a valid option.");
	                continue;
	            }
	            
	            switch(choice) {
	            case 1:
	            	System.out.println("\n=== Create Record ===");
	                String bookName = "";
	                while (bookName.isEmpty()) {
	                    System.out.print("Enter book name: ");
	                    bookName = scanner.nextLine();

	                    if (bookName.isEmpty()) {
	                        System.out.println("Book name cannot be empty. Please try again.");
	                    }
	                }

	                String bookType = "";
	                while (bookType.isEmpty()) {
	                    System.out.print("Enter Book Type: ");
	                    bookType = scanner.nextLine();

	                    if (bookType.isEmpty()) {
	                        System.out.println("Book type cannot be empty. Please try again.");
	                    }
	                }

	                String isbn = "";
	                while (isbn.isEmpty()) {
	                    System.out.print("Enter book ISBN: ");
	                    isbn = scanner.nextLine();

	                    if (isbn.isEmpty()) {
	                        System.out.println("ISBN cannot be empty. Please try again.");
	                    }
	                }

	                /*String borrowDate = "";
	                while (borrowDate.isEmpty()) {
	                    System.out.print("Enter Borrow Date: ");
	                    borrowDate = scanner.nextLine();

	                    if (borrowDate.isEmpty()) {
	                        System.out.println("Borrow date cannot be empty. Please try again.");
	                    }
	                }
	                */
	                long millis = System.currentTimeMillis();  
	                java.sql.Date borrowDate = new java.sql.Date(millis);
	            	Date createdAt = borrowDate;
	                
	                
	               /* String returnDate = "";
	                while (returnDate.isEmpty()) {
	                    System.out.print("Enter Return Date: ");
	                    returnDate = scanner.nextLine();

	                    if (returnDate.isEmpty()) {
	                        System.out.println("Return date cannot be empty. Please try again.");
	                    }
	                }
	                */
	            	
	            	// Calculate return date after 10 days from the current date
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DATE, 10);
                    Date returnDate = calendar.getTime();

                    // Convert the return date to a string in the required format
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String returnDateString = dateFormat.format(returnDate);
                	
                	Record record = new Record(1,bookName,bookType,isbn,borrowDate, returnDateString);
                	recordProducer.addRecord(record);
                	System.out.println("Record Added Successfully");
                	
                break;
	            case 2:
	            	   System.out.println("\n=== All Records ===");
	            	    List<Record> allRecords = recordProducer.getAllRecords();
	            	    if (allRecords.isEmpty()) {
	            	        System.out.println("No Records Found");
	            	    } else {
	            	        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+");
	            	        System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s |\n",
	            	                "Member ID", "Book Name", "Book Type", "ISBN", "Borrow Date", "Return Date");
	            	        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+");
	            	        for (Record r : allRecords) {
	            	            System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s |\n",
	            	                    r.getMemberID(), r.getBookName(), r.getBookType(),
	            	                    r.getIsbn(), r.getBorrowDate(), r.getReturnDate());
	            	        }
	            	        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+");
	            	    }
	            	
	            break;
	            case 3:
	            	 System.out.println("\n=== Delete Record ===");
	                    System.out.print("Enter Member ID to delete: ");
	                    int memberIDToDelete = scanner.nextInt();
	                    scanner.nextLine(); // consume the newline character

	                    // Call the method in RecordProducer to delete the record
	                    recordProducer.deleteRecord(memberIDToDelete);
	                    System.out.println("Record Deleted Successfully");

	                    break;
	            case 4:
                    System.out.println("Returning to the main menu");
                    break;
	            case 5:
	            	 System.out.println("Exiting...");
	                 break;
	            case 6:
	            	 searchRecords();
	                 break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
	           
	            }
    		

	        }
	    }
	private void searchRecords() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Record> searchResults = performSearch(keyword);
        if (searchResults.isEmpty()) {
            System.out.println("No matching records found.");
        } else {
            System.out.println("\n=== Search Results ===");
            displayRecords(searchResults);
        }
    }
	
	private void displayRecords(List<Record> records) {
	    System.out.println("+------------+----------------------+----------------------+---------------+---------------+---------------+");
	    System.out.println("| MemberID   | BookName             | BookType             | ISBN          | BorrowDate    | ReturnDate    |");
	    System.out.println("+------------+----------------------+----------------------+---------------+---------------+---------------+");

	    for (Record record : records) {
	        System.out.printf("| %-10d | %-20s | %-20s | %-13s | %-13s | %-13s |\n",
	                record.getMemberID(), record.getBookName(), record.getBookType(),
	                record.getIsbn(), record.getBorrowDate(), record.getReturnDate());
	    }

	    System.out.println("+------------+----------------------+----------------------+---------------+---------------+---------------+");
	}

	
    private List<Record> performSearch(String keyword) {
        // Implement your search logic here
        // For demonstration purposes, a basic search that matches the keyword with book names is used
        List<Record> allRecords = recordProducer.getAllRecords();
        List<Record> searchResults = new ArrayList<>();

        for (Record record : allRecords) {
            if (record.getBookName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(record);
            }
        }

        return searchResults;
    }

	}
