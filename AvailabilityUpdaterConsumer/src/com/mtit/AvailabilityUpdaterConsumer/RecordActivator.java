package com.mtit.AvailabilityUpdaterConsumer;

import com.mtit.BorrowingRecordProducer.RecordProducer;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class RecordActivator implements BundleActivator {
	
	private ServiceReference<RecordProducer> serviceReference;
	private RecordProducer RecordProducer;
	ServiceRegistration libraryRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Borrowing Tracker Consumer Strted");
		
		
		serviceReference = context.getServiceReference(RecordProducer.class);
		RecordProducer = context.getService(serviceReference);
		
		
		RecordConsumer recordConsumer = new RecordConsumerImpl(RecordProducer);
		libraryRegistration = context.registerService(RecordConsumer.class.getName(), recordConsumer, null);
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Record Consumer Stoped");
		context.ungetService(serviceReference);
	}

}
