package phonebookexample.ut;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("phonebookexample.ut Activator is started!");
		
		Bundle mainbundle = Platform.getBundle("PhoneBookExample");
		Bundle bundle = Platform.getBundle("PhoneBookExample-test");
		Bundle swtbundle = Platform.getBundle("org.eclipse.swt"); //org.eclipse.swt
		
		if (bundle == null) {
			System.out.println("Failed to load bundle PhoneBookExample-test!");
		} else {
			System.out.println("bundle class name = " + bundle.getClass().getName());
			System.out.println("bundle info :" + bundle.getState());
			if (bundle.getState() != Bundle.ACTIVE) {
				System.out.println("bundle info :" + bundle.getState());
			}
		}
		
		org.eclipse.osgi.framework.internal.core.BundleHost bundleImlp ;
		org.eclipse.osgi.internal.loader.BundleLoader loader ;
		try {
			
			URL url = bundle.getResource("phonebookexample/ut/Activator.class") ;
			if (url != null) {
				System.out.println("url = " + url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		Class cls ;
		
		
		try {
			
			cls = bundle.loadClass("phonebookexample.ut.AllTests");
		} catch (Exception e) {
			System.out.println("Failed to load class!");
		}
		try {
			cls = bundle.loadClass("org.eclipse.swt.widgets.Control") ;
		} catch (Exception e) {
			System.out.println("testbundle failed to load org.eclipse.swt.widgets.Control!");
		}
		
		try {
			cls = mainbundle.loadClass("org.eclipse.swt.widgets.Control") ;
		} catch (Exception e) {
			System.out.println("mainbundle failed to load org.eclipse.swt.widgets.Control!");
		}
		try {
			cls = swtbundle.loadClass("org.eclipse.swt.widgets.Control") ;
		} catch (Exception e) {
			System.out.println("swtbundle failed to load org.eclipse.swt.widgets.Control!");
		}
		
		Activator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("phonebookexample.ut Activator is stopped!");
		Activator.context = null;
	}

}
