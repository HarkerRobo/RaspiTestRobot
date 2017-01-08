/**
 * 
 */
package raspi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import net.straylightlabs.hola.dns.Domain;
import net.straylightlabs.hola.sd.Instance;
import net.straylightlabs.hola.sd.Query;
import net.straylightlabs.hola.sd.Service;

/**
 * @author joelmanning
 *
 */
public class HolaNetworking {
	public static void main(String[] args){
		try {
		    Service service = Service.fromName("_hello._tcp");
		    Query query = Query.createFor(service, Domain.LOCAL);
		    Set<Instance> instances = query.runOnce();
		    //instances.stream().forEach(System.out::println);
		    for(Instance instance: instances){
		    	String userVisibleName = instance.getName();
		    	Set<InetAddress> addresses = instance.getAddresses();
		    	int port = instance.getPort();
		    	if (instance.hasAttribute("platform")) {
		    	    String platform = instance.lookupAttribute("platform");
		    	    System.out.println(instance.toString());
		    	}
		    }
		} catch (UnknownHostException e) {
		    System.out.println("Unknown host: " + e);
		} catch (IOException e) {
		    System.out.println("IO error: " + e);
		}
	}
}
