package com.mtit.latefeenotifierproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class LatefeeActivator implements BundleActivator {

	ServiceRegistration latefeeServiceRegistration;


	public void start(BundleContext context) throws Exception {
		System.out.println("Started Late Fee Producer");
		LatefeeProducer latefeeProducer = new LatefeeProducerImpl();
		latefeeServiceRegistration = context.registerService(LatefeeProducer.class.getName(),latefeeProducer, null);

	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Late Fee Producer Stoped");
		latefeeServiceRegistration.unregister();
	}

}
