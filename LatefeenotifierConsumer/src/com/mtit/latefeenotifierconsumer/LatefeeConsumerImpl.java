package com.mtit.latefeenotifierconsumer;

import java.time.LocalDate;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import com.mtit.latefeenotifierproducer.LatefeeCalculateData;
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
            System.out.println("4. Update Record");
            System.out.println("5. Return to Main Menu");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            	case 1:
            		createRecord();
            		break; 
                case 2:
                    System.out.println("\n=== All Records ===");
                    List<LatefeeCalculateData> allRecords = latefeeProducer.getAllLatefeeRecords();
                    if (allRecords.isEmpty()) {
                        System.out.println("No Records Found");
                    } else {
                        displayRecords(allRecords);
                    }
                    break;
                case 3:
                	 deleteRecord();
                	 break;

                case 4:
                	updateRecord();
                    break;   
                case 5:
                    System.out.println("Returning to the main menu");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private void createRecord() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\n=== Create Record ===");
        String bookName = null, bookType = null, isbn = null;
        LocalDate borrowDate = null, returnDate = null;
        float lateFee = 0;
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

            if (bookType == null) {
                System.out.print("Enter Book Type: ");
                bookType = scanner.nextLine();
            }

            if (isbn == null) {
                System.out.print("Enter book ISBN: ");
                isbn = scanner.nextLine();

                if (!Pattern.matches("\\d{3}-\\d{10}", isbn)) {
                    System.out.println("Invalid ISBN format. Should be in XXX-XXXXXXXXXX format.");
                    isbn = null; 
                    continue; 
                }
            }

            if (borrowDate == null) {
                System.out.print("Enter Borrow Date (YYYY-MM-DD): ");
                borrowDate = parseDate(scanner.nextLine());
                if (borrowDate == null) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    continue; 
                }
            }

            if (returnDate == null) {
                System.out.print("Enter Return Date (YYYY-MM-DD): ");
                returnDate = parseDate(scanner.nextLine());
                if (returnDate == null) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    continue; 
                }

                if (returnDate.isBefore(borrowDate)) {
                    System.out.println("Return date cannot be before borrow date.");
                    returnDate = null; 
                    continue; 
                }
            }

            lateFee = calculateLateFee(borrowDate, returnDate);

            validInput = true; 
        } while (!validInput); 

        
        LatefeeCalculateData latefee = new LatefeeCalculateData(1, bookName, bookType, isbn, borrowDate.toString(), returnDate.toString(), lateFee);
        latefeeProducer.addLatefeeRecord(latefee);
        System.out.println("Record Added Successfully");
		
	}

	private LocalDate parseDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return null; 
        }
    }


    private float calculateLateFee(LocalDate borrowDate, LocalDate returnDate) {
        long daysLate = ChronoUnit.DAYS.between(borrowDate, returnDate);
        
        if (daysLate <= 14) { 
            return 0;
        } else if (daysLate <= 30) { 
            return (daysLate - 14) * 2;
        } else { 
            return (30 * 2) + ((daysLate - 30) * 10);
        }
    }

    private void displayRecords(List<LatefeeCalculateData> records) {
        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
        System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s | %-15s |\n",
                "Member ID", "Book Name", "Book Type", "ISBN", "Borrow Date", "Return Date", "Late Fee");
        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
        for (LatefeeCalculateData r : records) {
            System.out.printf("| %-15s | %-20s | %-20s | %-15s | %-15s | %-15s |%-16s |\n",
                    r.getMemberID(), r.getBookName(), r.getBookType(),
                    r.getIsbn(), r.getBorrowDate(), r.getReturnDate(), r.getLatefee());
        }
        System.out.println("+-----------------+----------------------+----------------------+-----------------+-----------------+-----------------+-----------------+");
    }
    
    
    private void deleteRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Delete Record ===");
        System.out.print("Enter Member ID to delete: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid member ID. Please enter a positive integer.");
            scanner.nextLine();
            return;
        }
        int memberIDToDelete = scanner.nextInt();
        scanner.nextLine();

        if (memberIDToDelete <= 0) {
            System.out.println("Invalid member ID. Please enter a positive integer.");
            return;
        } else {
            latefeeProducer.deleteRecord(memberIDToDelete);
            System.out.println("Record deleted successfully.");
        }
    }

    
    private void updateRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Update Record ===");
        System.out.print("Enter Member ID to update: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid member ID. Please enter a positive integer.");
            scanner.nextLine();
            return;
        }
        int memberIDToUpdate = scanner.nextInt();
        scanner.nextLine();

        LatefeeCalculateData existingRecord = latefeeProducer.getLatefeeRecord(memberIDToUpdate);
        if (existingRecord != null) {
            
            System.out.println("Enter updated information:");

            String bookName = null, bookType = null, isbn = null;
            LocalDate borrowDate = null, returnDate = null;
            float lateFee = 0;
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

                if (bookType == null) {
                    System.out.print("Enter Book Type: ");
                    bookType = scanner.nextLine();
                }

                if (isbn == null) {
                    System.out.print("Enter book ISBN: ");
                    isbn = scanner.nextLine();

                    if (!Pattern.matches("\\d{3}-\\d{10}", isbn)) {
                        System.out.println("Invalid ISBN format. Should be in XXX-XXXXXXXXXX format.");
                        isbn = null;
                        continue;
                    }
                }

                if (borrowDate == null) {
                    System.out.print("Enter Borrow Date (YYYY-MM-DD): ");
                    borrowDate = parseDate(scanner.nextLine());
                    if (borrowDate == null) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        continue;
                    }
                }

                if (returnDate == null) {
                    System.out.print("Enter Return Date (YYYY-MM-DD): ");
                    returnDate = parseDate(scanner.nextLine());
                    if (returnDate == null) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        continue;
                    }

                    if (returnDate.isBefore(borrowDate)) {
                        System.out.println("Return date cannot be before borrow date.");
                        returnDate = null;
                        continue;
                    }
                }

                lateFee = calculateLateFee(borrowDate, returnDate);

                validInput = true;
            } while (!validInput);

            
            LatefeeCalculateData updatedLatefeeData = new LatefeeCalculateData(
                    existingRecord.getMemberID(), bookName, bookType, isbn,
                    borrowDate.toString(), returnDate.toString(), lateFee);

            
            latefeeProducer.updateLatefeeRecord(memberIDToUpdate, updatedLatefeeData);
        } else {
            System.out.println("Record Not Found");
        }
    }

}
