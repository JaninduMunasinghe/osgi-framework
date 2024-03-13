package com.mtit.reviewproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ReviewActivator implements BundleActivator {

	ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Review Producer Started!");
		ReviewProducer reviewProducer = new ReviewProducerImpl();
		serviceRegistration = context.registerService(ReviewProducer.class.getName(), reviewProducer, null);

	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Review Producer Stopped!");
		serviceRegistration.unregister();
	}

}
