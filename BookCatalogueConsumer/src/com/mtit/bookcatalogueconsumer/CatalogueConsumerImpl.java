package com.mtit.bookcatalogueconsumer;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.mtit.bookcatalogueproducer.Catalogue;
import com.mtit.bookcatalogueproducer.CatalogueProducer;


public class CatalogueConsumerImpl implements CatalogueConsumer {

CatalogueProducer catalogueProducer;
private int catalogueID;
	
	
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
	            System.out.println("\n=== Catalogue Dashboard ===");
	            System.out.println("1. Create Catalogue");
	            System.out.println("2. View All Catalogues");
	            System.out.println("3. Update Catalogue");
	            System.out.println("4. Delete A Book");
	            System.out.println("5. Return to Main Menu");
	            System.out.println("6. Exit");

	            System.out.print("\nEnter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();
	            
	            switch(choice) {
	            case 1:
                    System.out.println("\n=== Create Catalogue ===");

                  
                    System.out.print("How many catalogues do you want to add? ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        System.out.print("How many catalogues do you want to add?  ");
                        scanner.next();
                    }
            	    int numCatalogues = scanner.nextInt();
            	    scanner.nextLine();
                    
                    
                    

                    for (int i = 0; i < numCatalogues; i++) {
                    	catalogueID = i+1;
                        System.out.println("\nCatalogue: " + (i + 1));
                        createCatalogue(scanner);
                    }
                    break;

	            case 2:
	            	viewAllCataloguesByGenre();
	                break;

	            case 3:
	            	updateCatalogue();	            	
	            	break;
	            case 4:
	            	 System.out.println("\n=== Delete a Catalogue ===");
	                    System.out.print("Enter Book ID to delete: ");
	                    int bookIDToDelete = scanner.nextInt();
	                    scanner.nextLine(); 

	                    
	                    catalogueProducer.deleteBook(bookIDToDelete);
	                    System.out.println("Book Deleted Successfully");
	                    viewAllCataloguesByGenre();


	                    break;
	            case 5:
                    System.out.println("Returning to the main menu");
                    break;
	            case 6:
	            	 System.out.println("Exiting...");
	                 break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
	           
	            }
    		

	        }
	    }
	
	
	
	
	private void createCatalogue(Scanner scanner) {
	    System.out.print("Enter genre: ");
	    String genre = scanner.nextLine().trim();
	    
	    System.out.print("How many books do you want to add? ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            System.out.print("How many books do you want to add? ");
            scanner.next();
        }
	    int numBooks = scanner.nextInt();
	    scanner.nextLine();

	    for (int i = 0; i < numBooks; i++) {
	        System.out.println("\nBook " + (i + 1) );
	        createBook(scanner, genre);
	    }
	}

    private void createBook(Scanner scanner, String genre) {
        String bookName;
        do {
            System.out.print("Enter book name: ");
            bookName = scanner.nextLine().trim();

            if (bookName.isEmpty()) {
                System.out.println("Book name cannot be empty. Please enter a valid book name.");
            }
        } while (bookName.isEmpty());

        System.out.print("Enter author: ");
        String author = scanner.nextLine().trim();

        String isbn;
        do {
            System.out.print("Enter ISBN: ");
            isbn = scanner.nextLine().trim();

            if (!Pattern.matches("\\d{3}-\\d{10}", isbn)) {
                System.out.println("Invalid ISBN format. Should be in XXX-XXXXXXXXXX format.");
                isbn = null;
            }
        } while (isbn == null);

        int count;
        do {
            System.out.print("Enter book count: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                System.out.print("Enter book count: ");
                scanner.next();
            }
            count = scanner.nextInt();
            scanner.nextLine();
            if (count <= 0) {
                System.out.println("Book count must be greater than 0.");
            }
        } while (count <= 0);

        Catalogue catalogue = new Catalogue(catalogueID,0, bookName, author, genre, isbn, count);
        catalogueProducer.addBook(catalogue);
        System.out.println("Record Added Successfully");
    }

    private void viewAllCataloguesByGenre() {
        Map<String, List<Catalogue>> cataloguesByGenre = new HashMap<>();
        List<Catalogue> allCatalogues = catalogueProducer.getAllCatalogues();

        for (Catalogue catalogue : allCatalogues) {
            String genre = catalogue.getGenre();
            if (!cataloguesByGenre.containsKey(genre)) {
                cataloguesByGenre.put(genre, new ArrayList<>());
            }
            cataloguesByGenre.get(genre).add(catalogue);
        }

        for (Map.Entry<String, List<Catalogue>> entry : cataloguesByGenre.entrySet()) {
            String genre = entry.getKey();
            List<Catalogue> catalogues = entry.getValue();

            System.out.println("\n=== Genre: " + genre + " ===");
           

            if (catalogues.isEmpty()) {
                System.out.println("No Records Found");
            } else {
                System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------+");
                System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s |%-15s |\n", "Book ID", "Book Name", "Author",
                        "Genre", "ISBN", "Count", "Created At");
                System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------+");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                for (Catalogue r : catalogues) {
                	
                    String createdAtDateOnly = r.getCreatedAt().format(dateFormatter);
                    System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s |%-15s |\n", r.getBookID(),
                            r.getBookName(), r.getAuthor(), r.getGenre(), r.getIsbn(), r.getBookCount(),
                            createdAtDateOnly , r.getCatalogueID());
                }
                System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+----------------+");
            }
        }
    }
	 
	 
    private void updateCatalogue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Update Catalogue ===");
        System.out.print("Enter Book ID to update: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid Book ID. Please enter a positive integer.");
            scanner.nextLine();
            return;
        }
        int bookIDToUpdate = scanner.nextInt();
        scanner.nextLine();

        Catalogue existingCatalogue = catalogueProducer.getCatalogue(bookIDToUpdate);
        if (existingCatalogue != null) {
            
            System.out.println("Enter updated information:");

            String bookName = null, author = null, genre = null, isbn = null;
            int count = 0;
            boolean validInput = false;
            do {
                if (bookName == null || bookName.isEmpty()) {
                    System.out.print("Enter book name: ");
                    bookName = scanner.nextLine();

                    if (bookName.isEmpty()) {
                        System.out.println("Book name cannot be empty.");
                        continue;
                    }
                }

                if (author == null || author.isEmpty()) {
                    System.out.print("Enter author: ");
                    author = scanner.nextLine();
                }

                if (genre == null || genre.isEmpty()) {
                    System.out.print("Enter genre: ");
                    genre = scanner.nextLine();
                }

                if (isbn == null || isbn.isEmpty()) {
                    System.out.print("Enter ISBN: ");
                    isbn = scanner.nextLine();

                    if (!Pattern.matches("\\d{3}-\\d{10}", isbn)) {
                        System.out.println("Invalid ISBN format. Should be in XXX-XXXXXXXXXX format.");
                        isbn = null;
                        continue;
                    }
                }

                if (count <= 0) {
                    System.out.print("Enter book count: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        System.out.print("Enter book count: ");
                        scanner.next(); 
                    }
                    count = scanner.nextInt();
                    scanner.nextLine(); 
                    if (count <= 0) {
                        System.out.println("Book count must be greater than 0.");
                    }
                }

                validInput = true;
            } while (!validInput);

            
            Catalogue updatedCatalogue = new Catalogue( existingCatalogue.getCatalogueID(),
                    existingCatalogue.getBookID(), bookName, author, genre, isbn, count);

            
            catalogueProducer.updateBook(bookIDToUpdate, updatedCatalogue);
            viewAllCataloguesByGenre();

        } else {
            System.out.println("Catalogue Record Not Found");
        }
    }


	}

