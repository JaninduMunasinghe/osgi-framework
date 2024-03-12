package com.mtit.latefeenotifierconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;


import com.mtit.latefeenotifierproducer.LatefeeProducer;

public class LatefeeActivator implements BundleActivator {

	private ServiceReference<LatefeeProducer> serviceReference;
	private LatefeeProducer LatefeeProducer;
	ServiceRegistration libraryRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Borrowing Tracker Consumer Strted");
		
		
		serviceReference = context.getServiceReference(LatefeeProducer.class);
		LatefeeProducer = context.getService(serviceReference);
		
		
		LatefeeConsumer latefeeConsumer = new LatefeeConsumerImpl(LatefeeProducer);
		libraryRegistration = context.registerService(LatefeeConsumer.class.getName(), latefeeConsumer, null);
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Latefee Consumer Stoped");
		context.ungetService(serviceReference);
	}

}
