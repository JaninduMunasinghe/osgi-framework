package com.mtit.bookcatalogueconsumer;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.mtit.bookcatalogueproducer.CatalogueProducer;



public class CatalogueActivator implements BundleActivator {

	private ServiceReference<CatalogueProducer> serviceReference;
	private CatalogueProducer CatalogueProducer;
	ServiceRegistration libraryRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Catalogue Consumer Strted");
		
		
		serviceReference = context.getServiceReference(CatalogueProducer.class);
		CatalogueProducer = context.getService(serviceReference);
		
		
		CatalogueConsumer catalogueConsumer = new CatalogueConsumerImpl(CatalogueProducer);
		libraryRegistration = context.registerService(CatalogueConsumer.class.getName(), catalogueConsumer, null);	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Catalogue Consumer Stoped");
		context.ungetService(serviceReference);	}

}
