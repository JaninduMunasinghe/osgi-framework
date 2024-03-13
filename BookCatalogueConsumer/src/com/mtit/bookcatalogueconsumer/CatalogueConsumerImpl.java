package com.mtit.bookcatalogueconsumer;

import java.util.List;
import java.util.Scanner;

import com.mtit.bookcatalogueproducer.Catalogue;
import com.mtit.bookcatalogueproducer.CatalogueProducer;


public class CatalogueConsumerImpl implements CatalogueConsumer {

CatalogueProducer catalogueProducer;
	
	
	public CatalogueConsumerImpl(CatalogueProducer catalogueServicePublish) {
		this.catalogueProducer = catalogueServicePublish;
	}
	
	
	@Override
	public void start() {
		interactWithUser();
		
	}
	
	private void interactWithUser() {
		Scanner scanner = new Scanner(System.in);
		 while (true) {
	            System.out.println("\n=== Borrowing Tracker ===");
	            System.out.println("1. Create Catalogue");
	            System.out.println("2. View All Catalogues");
	            System.out.println("3. Delete Catalogue");
	            System.out.println("4. Return to Main Menu");
	            System.out.println("5. Exit");

	            System.out.print("\nEnter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();
	            
	            switch(choice) {
	            case 1:
	                System.out.println("\n=== Create Catalogue ===");
	                System.out.print("Enter book name: ");
	                String bookName = scanner.nextLine();

	                System.out.print("Enter author: ");
	                String author = scanner.nextLine();

	                System.out.print("Enter genre: ");
	                String genre = scanner.nextLine();

	                System.out.print("Enter ISBN: ");
	                String isbn = scanner.nextLine();

	                Catalogue catalogue = new Catalogue(0, bookName, author, genre, isbn);
	                catalogueProducer.addBook(catalogue);
	                System.out.println("Record Added Successfully");
	                break;
	            case 2:
	                System.out.println("\n=== All Records ===");
	                List<Catalogue> allCatalogues = catalogueProducer.getAllCatalogues();
	                if (allCatalogues.isEmpty()) {
	                    System.out.println("No Records Found");
	                } else {
	                    System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
	                    System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s | %-15s |\n",
	                            "Book ID", "Book Name", "Author", "Genre", "ISBN", "Created At", "Updated At");
	                    System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
	                    for (Catalogue r : allCatalogues) {
	                        System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s | %-15s |\n",
	                                r.getBookID(), r.getBookName(), r.getAuthor(), r.getGenre(),
	                                r.getIsbn(), r.getCreatedAt(), r.getUpdatedAt());
	                    }
	                    System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
	                }
	                break;

	            case 3:
	            	 System.out.println("\n=== Delete a Catalogue ===");
	                    System.out.print("Enter Book ID to delete: ");
	                    int bookIDToDelete = scanner.nextInt();
	                    scanner.nextLine(); // consume the newline character

	                    // Call the method in RecordProducer to delete the record
	                    catalogueProducer.deleteBook(bookIDToDelete);
	                    System.out.println("Catalogue Deleted Successfully");

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

