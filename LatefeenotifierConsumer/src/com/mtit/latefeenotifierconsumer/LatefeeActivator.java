package com.mtit.latefeenotifierconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class LatefeeActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		LatefeeActivator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		LatefeeActivator.context = null;
	}

}
