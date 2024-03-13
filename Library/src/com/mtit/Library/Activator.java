package com.mtit.Library;

import com.mtit.AvailabilityUpdaterConsumer.RecordConsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	
	private ServiceReference<RecordConsumer> recordConsumerServiceReference;
	private RecordConsumer recordConsumer;
	
	
	public void start(BundleContext context) throws Exception {
		recordConsumerServiceReference = context.getServiceReference(RecordConsumer.class);
		recordConsumer = context.getService(recordConsumerServiceReference);
		Welcome();
		
	}

	public void stop(BundleContext context) throws Exception {
		context.ungetService(recordConsumerServiceReference);
	}
	
	private void Welcome() {
		System.out.println("Welcome to SLIIT New Library");
		System.out.println();
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
            System.out.println("\n=== Welcome to SLIIT New Library ===");
            System.out.println("1. Book Catloger");
            System.out.println("2. Borrowing Tracker");
            System.out.println("3. Late Fee Notifier");
            System.out.println("4. Ratings and Reviews");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            String choice = scanner.next();

            switch (choice) {
                case "1":
                	System.out.println("Invalid Number");
                	
                    break;
                case "2":
                	recordConsumer.start();
                	
                    break;
                case "3":
                	System.out.println("Invalid Number");
                    
                    break;
                case "5":
                    System.out.println("\nExiting SLIIT Library.");
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
	}

}
