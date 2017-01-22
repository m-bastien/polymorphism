package com.example.polymorphism.control;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.polymorphism.NetworkControlServiceFactory;
import com.example.polymorphism.model.IpNetwork;

public class IpNetworkControlServicesTest 
{
	private IpNetworkControlServices ipNetworkControlServices = new IpNetworkControlServices();
	private IpNetwork ipNetwork;
	private NetworkControlServiceFactory networkControlServiceFactory = new NetworkControlServiceFactory();
	
	@Before
	public void before(){
		ipNetwork = new IpNetwork();
    	ipNetwork.setDebit(45.6);
    	ipNetwork.setNbRouters(4);
	}
	
    @Test
    public void testComputeMaxNumberOfParticipants()
    {
    	int maxNumberOfParticipants = ipNetworkControlServices.computeMaxNumberOfParticipants(ipNetwork);
        Assert.assertEquals(36, maxNumberOfParticipants);    
    }
    
    @Test
    public void testComputeMaxNumberOfParticipantsWithFactory()
    {
    	int maxNumberOfParticipants = networkControlServiceFactory.retrieveControlService(ipNetwork).computeMaxNumberOfParticipants(ipNetwork);
        Assert.assertEquals(36, maxNumberOfParticipants);    
    }
}
