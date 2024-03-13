package com.mtit.Library;

import com.mtit.AvailabilityUpdaterConsumer.RecordConsumer;
import com.mtit.bookcatalogueconsumer.CatalogueConsumer;
import com.mtit.latefeenotifierconsumer.LatefeeConsumer;
import com.mtit.reviewconsumer.ReviewConsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	
	private ServiceReference<RecordConsumer> recordConsumerServiceReference;
	private RecordConsumer recordConsumer;
	
	private ServiceReference<LatefeeConsumer> latefeeconsumerServiceReference;
	private LatefeeConsumer latefeeConsumer;
	
	private ServiceReference<ReviewConsumer> reviewConsumerServiceReference;
	private ReviewConsumer reviewConsumer;
	
	private ServiceReference<CatalogueConsumer> catalogueconsumerServiceReference;
	private CatalogueConsumer catalogueConsumer;
	
	public void start(BundleContext context) throws Exception {
		recordConsumerServiceReference = context.getServiceReference(RecordConsumer.class);
		recordConsumer = context.getService(recordConsumerServiceReference);
		
		latefeeconsumerServiceReference = context.getServiceReference(LatefeeConsumer.class);
		latefeeConsumer = context.getService(latefeeconsumerServiceReference);
		

		catalogueconsumerServiceReference = context.getServiceReference(CatalogueConsumer.class);
		catalogueConsumer = context.getService(catalogueconsumerServiceReference);
		

		reviewConsumerServiceReference = context.getServiceReference(ReviewConsumer.class);
		reviewConsumer = context.getService(reviewConsumerServiceReference);
		
		Welcome();
		
	}

	public void stop(BundleContext context) throws Exception {
		context.ungetService(recordConsumerServiceReference);
		context.ungetService(latefeeconsumerServiceReference);
		context.ungetService(reviewConsumerServiceReference);
		context.ungetService(catalogueconsumerServiceReference);

	}
	
	public void Welcome() {
		System.out.println("Welcome to SLIIT New Library");
		System.out.println();
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
            System.out.println("\n=== Welcome to SLIIT New Library ===");
            System.out.println("1. Book Catloger");
            System.out.println("2. Borrowing Tracker");
            System.out.println("3. Late Fee Notifier");
            System.out.println("4. Reviews & Recommendations");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            String choice = scanner.next();

            switch (choice) {
                case "1":
                	catalogueConsumer.start();
                	
                    break;
                case "2":
                	recordConsumer.start();
                	
                    break;
                case "3":
                	latefeeConsumer.start();
                    
                    break;
                case "4":
                	reviewConsumer.start();
                    
                    break;
                case "5":
                    System.out.println("\nExiting Library!");
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
	}

}
