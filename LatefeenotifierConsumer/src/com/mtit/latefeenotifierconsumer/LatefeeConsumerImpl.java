package com.mtit.latefeenotifierconsumer;


import java.util.List;
import java.util.Scanner;

import com.mtit.latefeenotifierproducer.Latefee;
import com.mtit.latefeenotifierproducer.LatefeeProducer;

public class LatefeeConsumerImpl implements LatefeeConsumer {

	
	LatefeeProducer latefeeProducer;
	
	public LatefeeConsumerImpl(LatefeeProducer latefeeServicePublish) {
		this.latefeeProducer = latefeeServicePublish;
	}
	
	@Override
	public void start() {
		interactWithUser();
		
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

	            System.out.print("\nEnter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();
	            
	            switch(choice) {
	            case 1:
	            	System.out.println("\n=== Create Record ===");
                	System.out.print("Enter book name: ");
                	String bookName = scanner.nextLine();
                	
                	System.out.print("Enter Book Type: ");
                	String bookType = scanner.nextLine();
                	
                	System.out.print("Enter book ISBN: ");
                	String isbn = scanner.nextLine();
                	
                	System.out.print("Enter Borrow Date: ");
                	String borrowDate = scanner.nextLine();
                	
                	System.out.print("Enter Return Date: ");
                	String returnDate = scanner.nextLine();
                	
                	System.out.print("Enter Late Fee: ");
                	float latefeeamount = scanner.nextFloat();
                	
                	Latefee latefee = new Latefee(1,bookName,bookType,isbn,borrowDate,returnDate,latefeeamount);
                	latefeeProducer.addLatefeeRecord(latefee);
                	System.out.println("Record Added Successfully");
                	
                break;
	            case 2:
	            	   System.out.println("\n=== All Records ===");
	            	    List<Latefee> allRecords = latefeeProducer.getAllLatefeeRecords();
	            	    if (allRecords.isEmpty()) {
	            	        System.out.println("No Records Found");
	            	    } else {
	            	        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
	            	        System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s | %-15s |\n",
	            	                "Member ID", "Book Name", "Book Type", "ISBN", "Borrow Date", "Return Date", "Late Fee");
	            	        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
	            	        for (Latefee r : allRecords) {
	            	            System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s |%-16s |\n",
	            	                    r.getMemberID(), r.getBookName(), r.getBookType(),
	            	                    r.getIsbn(), r.getBorrowDate(), r.getReturnDate(),r.getLatefee());
	            	        }
	            	        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
	            	    }
	            	
	            break;
	            case 3:
	            	 System.out.println("\n=== Delete Record ===");
	                    System.out.print("Enter Member ID to delete: ");
	                    int memberIDToDelete = scanner.nextInt();
	                    scanner.nextLine(); // consume the newline character

	                    // Call the method in RecordProducer to delete the record
	                    latefeeProducer.deleteRecord(memberIDToDelete);
	                    System.out.println("Record Deleted Successfully");

	                    break;
	            case 4:
                    System.out.println("Returning to the main menu");
                    break;
	            case 5:
	            	 System.out.println("Exiting...");
	                 break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
	           
	            }
    		

	        }
	    }
	}



