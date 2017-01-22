package com.example.polymorphism;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.example.polymorphism.control.NetworkControlServices;
import com.example.polymorphism.model.Network;
import com.example.polymorphism.model.RadioNetwork;

public class NetworkControlServiceFactoryTest {
	private NetworkControlServiceFactory networkControlServiceFactory = new NetworkControlServiceFactory();
	
	@Test
	public void testRetrieveControlService(){
		Set<Network> networks = networkControlServiceFactory.getRegisteredNetworks();
		Iterator<Network> networkIterator = networks.iterator();
		
		List<NetworkControlServices> lServices = new LinkedList<NetworkControlServices>();
		while(networkIterator.hasNext()){
			NetworkControlServices controlService = networkControlServiceFactory.retrieveControlService(networkIterator.next());
			lServices.add(controlService);
		}
		Assert.assertFalse(lServices.contains(null)); //Each bean has a controller.
		Assert.assertEquals(networks.size(),lServices.size()); //All controllers are different.
	}	
}
