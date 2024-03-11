package com.mtit.BorrowingRecordProducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class RecordActivator implements BundleActivator {
	
	ServiceRegistration recordServiceRegistration;
	

	public void start(BundleContext context) throws Exception {
		System.out.println("Started Borrowing Record Producer");
		RecordProducer recordProducer = new RecordProducerImpl();
		recordServiceRegistration = context.registerService(RecordProducer.class.getName(), recordProducer, null);
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Record Producer Stop");
		recordServiceRegistration.unregister();
	}

}
