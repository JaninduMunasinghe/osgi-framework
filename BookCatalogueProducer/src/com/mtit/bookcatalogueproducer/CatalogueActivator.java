package com.mtit.bookcatalogueproducer;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class CatalogueActivator implements BundleActivator {

	ServiceRegistration catalogueServiceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Started Catalogue Producer");
		CatalogueProducer catalogueProducer = new CatalogueProducerImpl();
		catalogueServiceRegistration = context.registerService(CatalogueProducer.class.getName(), catalogueProducer, null);	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Catalogue Producer Stop");
		catalogueServiceRegistration.unregister();
	}

}
