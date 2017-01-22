package com.example.polymorphism.control;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.polymorphism.NetworkControlServiceFactory;
import com.example.polymorphism.model.IpNetwork;
import com.example.polymorphism.model.RadioNetwork;

public class RadioNetworkControlServicesTest 
{
	private RadioNetworkControlServices radioNetworkControlServices = new RadioNetworkControlServices();
	private RadioNetwork radioNetwork; 
	private NetworkControlServiceFactory networkControlServiceFactory = new NetworkControlServiceFactory();
	
	@Before
	public void before(){
		radioNetwork = new RadioNetwork();
    	radioNetwork.setDebit(15);
    	radioNetwork.setNbAntennas(6);
	}
	
    @Test
    public void testComputeMaxNumberOfParticipants()
    {
    	int maxNumberOfParticipants = radioNetworkControlServices.computeMaxNumberOfParticipants(radioNetwork);
        Assert.assertEquals(9, maxNumberOfParticipants);      
    }
    
    @Test
    public void testComputeMaxNumberOfParticipantsWithFactory()
    {
    	int maxNumberOfParticipants = networkControlServiceFactory.retrieveControlService(radioNetwork).computeMaxNumberOfParticipants(radioNetwork);
        Assert.assertEquals(9, maxNumberOfParticipants);      
    }
}
