package com.mtit.reviewconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.mtit.reviewproducer.ReviewProducer;

public class ReviewActivator implements BundleActivator {

	private ServiceReference<ReviewProducer> serviceReference;
	private ReviewProducer reviewProducer;
	ServiceRegistration serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Review Consumer Started!");
		
		serviceReference = bundleContext.getServiceReference(ReviewProducer.class);
		reviewProducer = bundleContext.getService(serviceReference);
		
		ReviewConsumer reviewConsumer = new ReviewConsumerImpl(reviewProducer); 
		serviceRegistration = bundleContext.registerService(ReviewConsumer.class.getName(), reviewConsumer, null);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Review Consumer Stopped");
		bundleContext.ungetService(serviceReference);
	}

}
